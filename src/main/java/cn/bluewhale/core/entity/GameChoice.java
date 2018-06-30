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
 * @since 2017-07-12
 */
@TableName("game_choice")
public class GameChoice extends Model<GameChoice> {

    private static final long serialVersionUID = 1L;

    /**
     * 选项id
     */
	private Integer id;
    /**
     * 选项
     */
	private String choice;
    /**
     * 结果id
     */
	private Integer resultid;
    /**
     * 前置道具
     */
	private String frontProps;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Integer getResultid() {
		return resultid;
	}

	public void setResultid(Integer resultid) {
		this.resultid = resultid;
	}

	public String getFrontProps() {
		return frontProps;
	}

	public void setFrontProps(String frontProps) {
		this.frontProps = frontProps;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
