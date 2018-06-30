package cn.bluewhale.core.service;

import cn.bluewhale.core.entity.UserAuths;
import cn.bluewhale.core.entity.UserInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
public interface IUserAuthsService extends IService<UserAuths> {

    boolean insert(UserAuths userAuths, UserInfo userInfo);


}
