package cn.bluewhale.core.websocket;

import java.util.Date;

/**
 * @author : Crab2Died</br>
 * @DESC : <p>WebSocket消息模型</p></br>
 * @date : 2017/5/25  9:43</br>
 */
public class Msg {


    // 消息体
    private String data;

    // 推送时间
    private Date createDate = new Date();

    // 标题
    private String title;

    public Msg() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "data='" + data + '\'' +
                ", createDate=" + createDate +
                ", title='" + title + '\'' +
                '}';
    }
}
