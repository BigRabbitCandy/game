package cn.bluewhale.core.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@TableName("user_info")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@TableId
	private Integer uid;
    /**
     * 角色Id
     */
	private Integer roleId;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 性别
     */
	private Integer sex;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 用户积分
     */
	private Integer score;
    /**
     * 登录次数
     */
	private Integer login;
    /**
     * 注册IP
     */
	private Long regIp;
    /**
     * 注册时间
     */
	private Integer regTime;
    /**
     * 最后登录IP
     */
	private Long lastLoginIp;
    /**
     * 最后登录时间
     */
	private Integer updateTime;
    /**
     * 最后发表时间
     */
	private Integer lastPost;
    /**
     * 发帖数
     */
	private Integer posts;
    /**
     * 精华数
     */
	private Integer digestPosts;
    /**
     * 同意接收通知(Email或短消息)
     */
	private Integer newsLetter;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		this.login = login;
	}

	public Long getRegIp() {
		return regIp;
	}

	public void setRegIp(Long regIp) {
		this.regIp = regIp;
	}

	public Integer getRegTime() {
		return regTime;
	}

	public void setRegTime(Integer regTime) {
		this.regTime = regTime;
	}

	public Long getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(Long lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getLastPost() {
		return lastPost;
	}

	public void setLastPost(Integer lastPost) {
		this.lastPost = lastPost;
	}

	public Integer getPosts() {
		return posts;
	}

	public void setPosts(Integer posts) {
		this.posts = posts;
	}

	public Integer getDigestPosts() {
		return digestPosts;
	}

	public void setDigestPosts(Integer digestPosts) {
		this.digestPosts = digestPosts;
	}

	public Integer getNewsLetter() {
		return newsLetter;
	}

	public void setNewsLetter(Integer newsLetter) {
		this.newsLetter = newsLetter;
	}

	@Override
	protected Serializable pkVal() {
		return this.uid;
	}

}
