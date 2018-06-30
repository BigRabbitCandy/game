package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.entity.UserInfo;
import cn.bluewhale.core.dao.UserInfoMapper;
import cn.bluewhale.core.service.IUserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
	
}
