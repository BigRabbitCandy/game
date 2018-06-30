package cn.bluewhale.core.dao;

import cn.bluewhale.core.entity.SysPermissionInit;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author z77z
 * @since 2017-02-16
 */
public interface SysPermissionInitMapper extends BaseMapper<SysPermissionInit> {
	List<SysPermissionInit> selectAll();
}