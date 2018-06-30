package cn.bluewhale.core.controller;

import cn.bluewhale.core.dto.UserAuthsDto;
import cn.bluewhale.core.entity.Result;
import cn.bluewhale.core.entity.UserAuths;
import cn.bluewhale.core.entity.UserInfo;
import cn.bluewhale.core.service.IUserAuthsService;
import cn.bluewhale.core.service.MailService;
import cn.bluewhale.core.util.*;
import cn.bluewhale.core.vcode.Randoms;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/4.
 */
@Controller
@RequestMapping(value="/register")
public class RegisterController {
    static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private IUserAuthsService userAuthsService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping(value = "sendMail")
    @ResponseBody
    public Result sendMail(@RequestBody String identifier, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("identifier", identifier);
        List<UserAuths> userList = userAuthsService.selectByMap(map);
        if (DataUtil.isNotEmpty(userList) && userList.size() > 0) {
            return new Result(Result.Status.ERROR,"该邮箱已注册");
        }
        String code = Randoms.num(1001,10000)+"";
        logger.info("注册信息:----------",code+"\t"+identifier);
        request.getSession().setAttribute("code",code);
        try {
            mailService.sendSimpleMail(identifier,"bluewhale欢迎你的加入~",code);
        }catch (Exception e) {
            logger.warn("发送邮件失败：邮箱------->",identifier);
            return new Result(Result.Status.ERROR,"邮件服务器异常，您的验证码为"+code+"请您在3分钟之内完成注册!");
        }
        return new Result(Result.Status.OK,null);
    }

    @PostMapping(value="register")
    @ResponseBody
    public Result register(@RequestBody UserAuthsDto user, HttpServletRequest request) {
        if (DataUtil.isEmpty(user)) {
            return new Result(Result.Status.ERROR,"参数为空！");
        }

        if (DataUtil.isEmpty(user.getCredential())) {
            return new Result(Result.Status.ERROR,"密码为空！");
        }

        if (DataUtil.isEmpty(user.getIdentifier())) {
            return new Result(Result.Status.ERROR,"邮箱为空！");
        }

        if (DataUtil.isEmpty(user.getVcode())) {
            return new Result(Result.Status.ERROR,"验证码为空！");
        }

        UserAuths query = new UserAuths();
        Map<String,Object> map1 = new HashMap<String,Object>();

        Session session = SecurityUtils.getSubject().getSession();
        //转化成小写字母
        String vcode = user.getVcode().toLowerCase();
        String v = (String) session.getAttribute("code");
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        //session.removeAttribute("_come");
        if(!vcode.equals(v)){
            return new Result(Result.Status.ERROR,"验证码错误！");
        }

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("identifier", user.getIdentifier());
            List<UserAuths> userList = userAuthsService.selectByMap(map);
            if (DataUtil.isNotEmpty(userList) && userList.size() > 0) {
                return new Result(Result.Status.ERROR,"该邮箱已注册");
            }
            String password = user.getCredential();
            String pawDES  = MyDES.encryptBasedDes(password+user.getIdentifier());
            user.setCredential(pawDES);
            user.setStatus(1);

            UserInfo userInfo = new UserInfo();
            Long ipAddress = IPUtil.getIpAddressToLong(request);
            userInfo.setLogin(1);
            userInfo.setLastLoginIp(ipAddress);
            userInfo.setRoleId(0);
            userInfo.setUpdateTime(Integer.valueOf(Long.valueOf(DateUtils.getNowTimeStamp()).toString()));
            userAuthsService.insert(user,userInfo);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getIdentifier(), password,false);
            SecurityUtils.getSubject().login(token);
            request.getSession().setAttribute("userid",userInfo.getUid());
            String id = request.getSession().getId();
            //request.getSession().setAttribute("sessionid",id);
            redisUtil.set(id,userInfo,600000L);
            map1.put("userInfo",userInfo);
            map1.put("sessionid",id);
            map1.put("uid", userInfo.getUid());
            return new Result(Result.Status.OK,map1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Result(Result.Status.ERROR,"注册失败！");
        }
    }

}
