package cn.bluewhale.core.controller;

import cn.bluewhale.core.entity.GameContent;
import cn.bluewhale.core.entity.Result;
import cn.bluewhale.core.service.IGameContentService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@Controller
@RequestMapping("/gameContent")
public class GameContentController {
    @Autowired
    IGameContentService iGameContentService;

    @RequestMapping("/getById") //参数：current 要获取当前页数  ；size  获取的条数
    @ResponseBody
    @JsonProperty("id")
    public Result getById(String id){
        System.out.println("123");
        List<GameContent> listGameContent = iGameContentService.selectList(new EntityWrapper<GameContent>().eq("gid", id));
        //Page<Game> pageList= iGameService.selectPage(page);
        return  new Result(Result.Status.OK,listGameContent);
    }
	
}
