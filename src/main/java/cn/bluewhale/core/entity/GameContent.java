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
@TableName("game_content")
public class GameContent extends Model<GameContent> {

    private static final long serialVersionUID = 1L;

    /**
     * 题目id
     */
	private Integer id;
    /**
     * 游戏id
     */
	private Integer gid;
    /**
     * 描述
     */
	private String detail;
    /**
     * 选项
     */
	private Integer choiceid;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	public Integer getChoiceid() {
		return choiceid;
	}

	public void setChoiceid(Integer choiceid) {
		this.choiceid = choiceid;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
