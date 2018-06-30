package cn.bluewhale.core.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

//指定WebSocket连接的地址，和前端的new WebSocket(".../websocket")相对应
@ServerEndpoint(value = "/admin/websocket/{userid}")
//部署时删除
//@Component
//由于要保存会话，spring boot默认为单例，难以操作，声明为protoType避免错误
@Scope("Prototype")
public class MyWebSocket {
    static Logger logger = LoggerFactory.getLogger(MyWebSocket.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private static int Count = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象，用于群发消息
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    private static ConcurrentHashMap<Integer,MyWebSocket> webSocketMap = new ConcurrentHashMap<Integer,MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private Integer userId;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("userid")Integer userid, Session session) {
       this.session = session;
        webSocketSet.add(this);     //加入set中
        if (userid != null) {
            this.userId = userid;
            //若不为空 则表示已经在线  替换当前的最新的session
            if(webSocketMap.get(userid) == null){
                addOnlineCount();           //在线数加1
            }
        }
        webSocketMap.put(userid,this);
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        if(webSocketMap.get(this.userId) != null){
            webSocketMap.remove(this.userId);
            subOnlineCount();           //在线数减1
        }
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * 客户端发送过来的消息
     * @param message
     * */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }


    /**
     * 发生异常时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        onClose();
        //error.printStackTrace();
    }


    /**
     * 用于主动推送信息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 用于群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
        for(Integer key : webSocketMap.keySet()){
            MyWebSocket item = webSocketMap.get(key);
            try {
                if(item!=null){
                    item.sendMessage(message);
                }else{
                    webSocketMap.remove(item);
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("websocket服务器主动群发自定义消息:"+e.getMessage());
                continue;
            }
        }
    }

    /**
     * 服务器根据某个用户id发自定义消息
     * @param userId
     * @param message
     */
    public static void sendInfo(Integer userId, String message) {

        MyWebSocket item = webSocketMap.get(userId);

        try {
            if(item != null){
                item.sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("websocket服务器根据某个用户id发自定义消息:"+e.getMessage());
        }

    }

    public static synchronized int getOnlineCount() {
        return Count;
    }
    public static synchronized int getCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        if (MyWebSocket.onlineCount<100) {
            java.util.Random random=new java.util.Random();
            int result=random.nextInt(10) + 1;
            System.out.println(result);
            MyWebSocket.onlineCount+=result;
        }else{
            java.util.Random random=new java.util.Random();
            int result=random.nextInt(2) + 1;
            System.out.println(result);
            MyWebSocket.onlineCount+=result;
        }
        MyWebSocket.Count++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
        MyWebSocket.Count--;
    }
}