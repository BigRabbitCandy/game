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
@TableName("game_prop")
public class GameProp extends Model<GameProp> {

    private static final long serialVersionUID = 1L;

    /**
     * 道具id
     */
	private Integer id;
    /**
     * 道具名称
     */
	private String name;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
