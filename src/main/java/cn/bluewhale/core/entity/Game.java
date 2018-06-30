package cn.bluewhale.core.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author 作者: bluewhale
 * @since 2017-06-24
 */
public class Game extends Model<Game> {

    private static final long serialVersionUID = 1L;

    /**
     * 游戏
     */
	private Integer id;
    /**
     * 副标题
     */
	private String subhead;
    /**
     * 游戏标题
     */
	private String title;
    /**
     * 游戏描述
     */
	private String description;
    /**
     * 游戏创建时间
     */
	private Integer dateLine;
    /**
     * 点击次数
     */
	private Integer click;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDateLine() {
		return dateLine;
	}

	public void setDateLine(Integer dateLine) {
		this.dateLine = dateLine;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
