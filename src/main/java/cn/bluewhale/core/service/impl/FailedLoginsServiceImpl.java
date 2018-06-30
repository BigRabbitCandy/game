package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.entity.FailedLogins;
import cn.bluewhale.core.dao.FailedLoginsMapper;
import cn.bluewhale.core.service.IFailedLoginsService;
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
public class FailedLoginsServiceImpl extends ServiceImpl<FailedLoginsMapper, FailedLogins> implements IFailedLoginsService {
	
}
