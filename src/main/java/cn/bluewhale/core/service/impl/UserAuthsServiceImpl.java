package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.dao.UserAuthsMapper;
import cn.bluewhale.core.dao.UserInfoMapper;
import cn.bluewhale.core.entity.UserAuths;
import cn.bluewhale.core.entity.UserInfo;
import cn.bluewhale.core.service.IUserAuthsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
@Service
public class UserAuthsServiceImpl extends ServiceImpl<UserAuthsMapper, UserAuths> implements IUserAuthsService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public boolean insert(UserAuths userAuths,UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
        Integer uid = userInfo.getUid();
        userAuths.setUid(uid);
        return super.insert(userAuths);
    }
}
