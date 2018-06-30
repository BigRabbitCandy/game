package cn.bluewhale.core.service.impl;

import cn.bluewhale.core.entity.MyTasks;
import cn.bluewhale.core.dao.MyTasksMapper;
import cn.bluewhale.core.service.IMyTasksService;
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
public class MyTasksServiceImpl extends ServiceImpl<MyTasksMapper, MyTasks> implements IMyTasksService {
	
}
