package cn.bluewhale.core.service;

import cn.bluewhale.core.dao.SysPermissionInitMapper;
import cn.bluewhale.core.entity.SysPermissionInit;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author z77z
 * @since 2017-02-16
 */
@Service
public class SysPermissionInitService extends ServiceImpl<SysPermissionInitMapper, SysPermissionInit>{
	
	@Autowired
	SysPermissionInitMapper sysPermissionInitMapper;
	
	public List<SysPermissionInit> selectAll() {
		return sysPermissionInitMapper.selectAll();
	}
}
