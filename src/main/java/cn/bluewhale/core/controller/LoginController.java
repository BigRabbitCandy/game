package cn.bluewhale.core.controller;

import cn.bluewhale.core.entity.Result;
import cn.bluewhale.core.entity.UserAuths;
import cn.bluewhale.core.entity.UserInfo;
import cn.bluewhale.core.service.IUserInfoService;
import cn.bluewhale.core.shiro.ShiroService;
import cn.bluewhale.core.util.DataUtil;
import cn.bluewhale.core.util.DateUtils;
import cn.bluewhale.core.util.IPUtil;
import cn.bluewhale.core.util.RedisUtil;
import cn.bluewhale.core.vcode.Captcha;
import cn.bluewhale.core.vcode.GifCaptcha;
import cn.bluewhale.core.websocket.MyWebSocket;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro权限控制登录Controller
 *
 * @author 作者: z77z
 * @date 创建时间：2017年2月10日 下午1:32:02
 */
@Controller
public class LoginController {

	@Autowired
	ShiroService shiroService;

	@Autowired
	IUserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;

	//首页
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}

	@PostMapping(value = "sendMessage")
	public void sendMessage(@RequestBody String message,HttpServletRequest request) throws IOException {
		Integer userid = (Integer)request.getSession().getAttribute("userid");
		/*if (DataUtil.isEmpty(userid)) {
			return null;
		}*/

		message = "";

//		if (" ".equals(message)) {
		String nickname = userInfoService.selectById(userid).getNickname();
		if (DataUtil.isNotEmpty(nickname) && nickname != "") {
			message +=nickname +",";
		}
		message += "欢迎您登录。当前在线人数"+MyWebSocket.getCount();
//		}
		try {
			MyWebSocket.sendInfo(Integer.valueOf(userid),message);
		}catch (Exception e) {
//			return new Result(Result.Status.ERROR,null);
		}
//		return new Result(Result.Status.OK,null);
	}

	//登录
	@RequestMapping(value="login")
	public String login() {
		return "login";
	}
	//注册
	@RequestMapping(value="register")
	public String register() {
		return "register";
	}

	//权限测试用
	@RequestMapping(value="add")
	public String add() {
		return "add";
	}

	//未授权跳转的页面
	@RequestMapping(value="403")
	public String noPermissions() {
		return "403";
	}

	//更新权限
	@RequestMapping(value="updatePermission")
	@ResponseBody
	public String updatePermission() {
		shiroService.updatePermission();
		return "true";
	}

	//踢出用户
	@RequestMapping(value="kickouting")
	@ResponseBody
	public String kickouting() {

		return "kickout";
	}

	//被踢出后跳转的页面
	@RequestMapping(value="kickout")
	public String kickout() {
		return "kickout";
	}

	/**
	 * ajax登录请求
	 * @return
	 */
	@RequestMapping(value="ajaxLogin",method=RequestMethod.POST)
	@ResponseBody
	public Result submitLogin(@RequestBody Map<String,Object> map, String vcode, Boolean rememberMe, Model model, HttpServletRequest request) {

		Long ipAddress = IPUtil.getIpAddressToLong(request);
		System.out.println(ipAddress);

		String username = (String) map.get("username");
		String password = (String) map.get("password");
		vcode = (String) map.get("vcode");
		rememberMe = (Boolean) map.get("rememberMe");
//		rememberMe = Boolean.parseBoolean(rm);

		if(vcode==null||vcode==""){
			return new Result(Result.Status.ERROR,"验证码不能为空！");
		}

		Session session = SecurityUtils.getSubject().getSession();
		//转化成小写字母
		vcode = vcode.toLowerCase();
		String v = (String) session.getAttribute("_code");
		//还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
		//session.removeAttribute("_come");
		if(!vcode.equals(v)){
			return new Result(Result.Status.ERROR,"验证码错误！");
		}

		UserInfo userInfo = null;
        Map<String,Object> map1 = new HashMap<String,Object>();

		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
			SecurityUtils.getSubject().login(token);
            String id = null;
            UserAuths user = (UserAuths)SecurityUtils.getSubject().getPrincipal();
			if (DataUtil.isNotEmpty(user) && DataUtil.isNotEmpty(user.getId())) {
				request.getSession().setAttribute("userid",user.getId());
				userInfo = userInfoService.selectById(user.getId());
				if (DataUtil.isNotEmpty(userInfo)) {
					userInfo.setUpdateTime(Integer.valueOf(Long.valueOf(DateUtils.getNowTimeStamp()).toString()));
					userInfo.setLastLoginIp(ipAddress);
					userInfoService.updateById(userInfo);
				}
                id = request.getSession().getId();
                //request.getSession().setAttribute("sessionid",id);
                redisUtil.set(id,userInfo,600000L);
			}
			map1.put("userInfo",userInfo);
			map1.put("sessionid",id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Result(Result.Status.ERROR,e.getMessage());
		}
		return new Result(Result.Status.OK,map1);
	}

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(){
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			//退出
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码
			 * 宽，高，位数。
			 */
			Captcha captcha = new GifCaptcha(146,33,4);
			//输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);
			//存入Session
			session.setAttribute("_code",captcha.text().toLowerCase());
		} catch (Exception e) {
			System.err.println("获取验证码异常："+e.getMessage());
		}
	}

	@RequestMapping(value = "/upload", produces = { "application/json" }, method =RequestMethod.POST )
	public Result upload(MultipartFile file){
		if(!file.isEmpty()){
			try {
                /*
                * 这段代码执行完毕之后，图片上传到了工程的跟路径；
                * 大家自己扩散下思维，如果我们想把图片上传到 d:/files大家是否能实现呢？
                * 等等;
                * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如：
                * 1、文件路径；
                * 2、文件名；
                * 3、文件格式;
                * 4、文件大小的限制;
                */
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
				return new Result(Result.Status.ERROR,"上传失败,"+e.getMessage());
			}catch (IOException e) {
				e.printStackTrace();
				return new Result(Result.Status.ERROR,"上传失败,"+e.getMessage());
			}
			return new Result(Result.Status.OK, "上传成功");
		}else{
			return new Result(Result.Status.ERROR,"上传失败，因为文件是空的.");
		}
//		return new Result(Result.Status.ERROR,null);
	}

	/**
	 * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
	 * @param request
	 * @return
	 */

	@RequestMapping(value="/batch/upload", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(HttpServletRequest request){
		List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i =0; i< files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					stream =  null;
					return "You failed to upload " + i + " =>" + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " becausethe file was empty.";
			}
		}
		return "upload successful";
	}
}
