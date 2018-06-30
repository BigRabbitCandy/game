package cn.bluewhale.core.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author z77z
 * @since 2017-02-16
 */
@TableName("sys_permission_init")
public class SysPermissionInit extends Model<SysPermissionInit> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String url;
	@TableField("permission_init")
	private String permissionInit;
	private Integer sort;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermissionInit() {
		return permissionInit;
	}

	public void setPermissionInit(String permissionInit) {
		this.permissionInit = permissionInit;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public SysPermissionInit() {
	}

	public SysPermissionInit(String id, String url, String permissionInit, Integer sort) {
		this.id = id;
		this.url = url;
		this.permissionInit = permissionInit;
		this.sort = sort;
	}
}
