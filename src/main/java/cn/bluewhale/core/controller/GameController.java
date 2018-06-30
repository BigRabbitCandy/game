package cn.bluewhale.core.controller;

import cn.bluewhale.core.entity.*;
import cn.bluewhale.core.service.*;
import cn.bluewhale.core.util.DataUtil;
import cn.bluewhale.core.util.DateUtils;
import cn.bluewhale.core.util.RedisUtil;
import com.baomidou.mybatisplus.plugins.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private IGameService gameService;
    @Autowired
    private IGameContentService gameContentService;
    @Autowired
    private IGameChoiceService gameChoiceService;
    @Autowired
    private IChoiceResultService choiceResultService;
    @Autowired
    private IGamePropService gamePropService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/list") //参数：current 要获取当前页数  ；size  获取的条数
    @ResponseBody
    public Result list(@RequestBody Page<Game> page){
        page.setOrderByField("click");
        page.setAsc(false);
        page.setSize(1);
        Page<Game> pageList= gameService.selectPage(page);
        return  new Result(Result.Status.OK,pageList);
    }

    @RequestMapping("")
    public String list(){
        return "gamelist";
    }

    /**
     * 获取游戏内容
     * @param map
     * @return
     */
    @PostMapping(value = "/getById")
    @ResponseBody
    public Result getById(@RequestBody Map map, HttpServletRequest request){
        String sessionid = (String) map.get("sessionid");
        if (DataUtil.isEmpty(sessionid) || !redisUtil.exists(sessionid)) {
            return new Result(Result.Status.ERROR,null);
        }
        String id = (String) map.get("id");
        Game game = gameService.selectById(new Integer(id));
        Integer gameCountId = (Integer) map.get("gameCountId");
        List<GameContent> gameContents = gameContentService.selectListByGid(new Integer(id));
        if (gameCountId.equals(0)) {
            game.setClick(game.getClick()+1);
            gameService.updateById(game);
            int i = gamePropService.selectCount();
            for (;i>0;i--) {
                if (redisUtil.exists("gameProp_"+sessionid+ "_" + i)) {
                    redisUtil.remove("gameProp_"+sessionid+ "_" + i);
                }
            }
            gameCountId = gameContents.get(0).getId();
        }
        GameContent gameContent = null;
        for (GameContent gameContent_ : gameContents) {
            if (gameContent_.getId()==gameCountId) {
                gameContent = gameContent_;
                break;
            }
        }
        return  new Result(Result.Status.OK,gameContent);
    }

    /**
     * 获取游戏
     * @param
     * @return
     */
    @PostMapping(value = "/getGameChoice")
    @ResponseBody
    public Result getGameChoice(@RequestBody Map map,HttpServletRequest request){
        String sessionid = (String) map.get("sessionid");
        if (DataUtil.isEmpty(sessionid) || !redisUtil.exists(sessionid)) {
            return new Result(Result.Status.ERROR,null);
        }
        Integer id = (Integer) map.get("id");
        GameChoice gameChoice = gameChoiceService.selectById(id);
        if (null == gameChoice) {
            return new Result(Result.Status.OK,null);
        }
        JSONArray gameChoices = JSONArray.fromObject(gameChoice.getChoice());
        if (null!=gameChoice.getFrontProps()) {
            JSONArray jsonArray = JSONArray.fromObject(gameChoice.getFrontProps());
            for (int i =0;i<jsonArray.size();i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                Integer idStr = (Integer) json.get("id");
                String choiceStr = (String) json.get("choice");
                if (!redisUtil.exists("gameProp_" + sessionid+ "_" + idStr)) {
                    switch (choiceStr){
                        case "A":
                            gameChoices.remove(0);
                            break;
                        case "B":
                            gameChoices.remove(1);
                            break;
                        case "C":
                            gameChoices.remove(2);
                            break;
                        case "D":
                            gameChoices.remove(3);
                            break;
                        case "E":
                            gameChoices.remove(4);
                            break;
                        case "F":
                            gameChoices.remove(5);
                            break;
                        case "G":
                            gameChoices.remove(6);
                            break;
                    }
                }

            }
        }
        gameChoice.setChoice(gameChoices.toString());
        return new Result(Result.Status.OK,gameChoice);
    }

    /**
     * 获取结果
     * @param map
     * @return
     */
    @PostMapping("/getRightChoice")
    @ResponseBody
    public Result getRightChoice(@RequestBody Map map,HttpServletRequest request){
        //Integer userid = (Integer) request.getSession().getAttribute("userid");
        String sessionid = (String) map.get("sessionid");
        if (DataUtil.isEmpty(sessionid) || !redisUtil.exists(sessionid)) {
            return new Result(Result.Status.ERROR,null);
        }
        Integer id = (Integer) map.get("id");
        String choice = (String) map.get("choice");
        ChoiceResult choiceResult = choiceResultService.selectById(id);
        Integer idStr = null;
        //0安全1跳关2错误
        int jump = 2;
        if (null != choiceResult) {
            if (null!=choiceResult.getPropsIn()) {
                JSONArray jsonArray = JSONArray.fromObject(choiceResult.getPropsIn());
                for (int i =0;i<jsonArray.size();i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    String choiceStr = (String) json.get("choice");
                    if (choiceStr.equals(choice)) {
                        idStr = (Integer) json.get("id");
                        redisUtil.set("gameProp_" + sessionid + "_" + idStr,idStr,3600000L);
                    }
                }
            }
            if (null!=choiceResult.getPropsOut()) {
                JSONArray jsonArray = JSONArray.fromObject(choiceResult.getPropsOut());
                for (int i =0;i<jsonArray.size();i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    String choiceStr = (String) json.get("choice");
                    if (choiceStr.equals(choice)) {
                        idStr = (Integer) json.get("id");
                        redisUtil.remove("gameProp_" + sessionid + "_" + idStr);
                    }

                }
            }
        }


        String answerStr = choiceResult.getAnswer();
        String[] splits = answerStr.split(",");
        for (String split : splits) {
            if (choice.equals(split)){
                jump = 0;
                break;
            }
        }
        if (null != choiceResult.getJump()) {
            String choiceStr = choiceResult.getJump();
            String[] choices = choiceStr.split(",");
            for (String choice_ : choices) {
                if (choice.equals(choice_)){
                    jump = 1;
                    break;
                }
            }
            /*if (choice.equals(choiceResult.getJump())) {
                jump = 1;
            }*/
        }
        String result = null;
        switch (choice){
            case "A":
                result = choiceResult.getA();
                break;
            case "B":
                result = choiceResult.getB();
                break;
            case "C":
                result = choiceResult.getC();
                break;
            case "D":
                result = choiceResult.getD();
                break;
            case "E":
                result = choiceResult.getE();
                break;
            case "F":
                result = choiceResult.getF();
                break;
            case "G":
                result = choiceResult.getG();
                break;
        }
        if (jump == 1) {
            return new Result(Result.Status.OK,result,jump);
        }else if (jump == 0) {
            return new Result(Result.Status.OK,result,null);
        }

        int i = gamePropService.selectCount();
        for (;i>0;i--) {
            if (redisUtil.exists("gameProp_" + sessionid + "_" + i)) {
                redisUtil.remove("gameProp_" + sessionid + "_" + i);
            }
        }
        return new Result(Result.Status.ERROR,result,null);
    }

    @PostMapping("addGame")
    @ResponseBody
    public Result addGame(@RequestBody Game game,HttpServletRequest request) {
        if (DataUtil.isEmpty(game) || DataUtil.isEmpty(game.getSubhead())) {
            return new Result(Result.Status.ERROR,"副标题不能为空");
        }

        if (DataUtil.isEmpty(game.getTitle())) {
            return new Result(Result.Status.ERROR,"标题不能为空");
        }

        if (DataUtil.isEmpty(game.getDescription())) {
            return new Result(Result.Status.ERROR,"描述不能为空");
        }

        try {

            game.setDateLine(Integer.valueOf(Long.valueOf(DateUtils.getNowTimeStamp()).toString()));
            boolean result = gameService.insert(game);
            if (result) {
                return new Result(Result.Status.OK,game.getId());
            }
        }catch (Exception e) {
            return new Result(Result.Status.ERROR,"系统错误,请重试");
        }
        return new Result(Result.Status.ERROR,"未知错误,请重试");
    }
}
