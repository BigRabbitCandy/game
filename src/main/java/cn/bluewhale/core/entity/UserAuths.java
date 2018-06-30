package cn.bluewhale.core.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@TableName("user_auths")
public class UserAuths extends Model<UserAuths> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户授权信息ID
     */
	private Integer id;
    /**
     * 用户ID
     */
	private Integer uid;
    /**
     * 登录类型
     */
	private String identityType;
    /**
     * 标识
     */
	private String identifier;
    /**
     * 密码凭证
     */
	private String credential;
	/**
	 * 用户状态
	 */
	private Integer status;




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
