package cn.bluewhale.core.dto;

import cn.bluewhale.core.entity.UserAuths;

/**
 * Created by Administrator on 2017/8/11.
 */
public class UserAuthsDto extends UserAuths {
    /**
     * 登录验证码
     */
    private String vcode;

    private Boolean rememberMe;

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
