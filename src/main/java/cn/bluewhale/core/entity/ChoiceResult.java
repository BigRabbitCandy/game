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
 * @since 2017-07-09
 */
@TableName("choice_result")
public class ChoiceResult extends Model<ChoiceResult> {

    private static final long serialVersionUID = 1L;

    /**
     * 结果id
     */
	private Integer id;
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private String F;
	private String G;
    /**
     * 道具获取
     */
	private String propsIn;
    /**
     * 道具使用
     */
	private String propsOut;
    /**
     * 答案
     */
	private String answer;
    /**
     * 跳过
     */
	private String jump;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getA() {
		return A;
	}

	public void setA(String A) {
		this.A = A;
	}

	public String getB() {
		return B;
	}

	public void setB(String B) {
		this.B = B;
	}

	public String getC() {
		return C;
	}

	public void setC(String C) {
		this.C = C;
	}

	public String getD() {
		return D;
	}

	public void setD(String D) {
		this.D = D;
	}

	public String getE() {
		return E;
	}

	public void setE(String E) {
		this.E = E;
	}

	public String getF() {
		return F;
	}

	public void setF(String F) {
		this.F = F;
	}

	public String getG() {
		return G;
	}

	public void setG(String G) {
		this.G = G;
	}

	public String getPropsIn() {
		return propsIn;
	}

	public void setPropsIn(String propsIn) {
		this.propsIn = propsIn;
	}

	public String getPropsOut() {
		return propsOut;
	}

	public void setPropsOut(String propsOut) {
		this.propsOut = propsOut;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getJump() {
		return jump;
	}

	public void setJump(String jump) {
		this.jump = jump;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
