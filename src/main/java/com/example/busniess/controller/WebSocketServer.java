package com.example.busniess.controller;

import com.example.busniess.entity.InformEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@Component
//访问服务端的url地址
@ServerEndpoint(value = "/websocket/{userName}")
public class WebSocketServer {
    //连接的数量
    private static int onlineCount = 0;
    //用来存放每一个session和指定的用户名
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();
    //用来存储用户的名和要推送的消息
    private static ConcurrentHashMap<String, Vector> webUser = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //    private static Logger log = LogManager.getLogger(WebSocketServer.class);
//    用户名
    private String userName;
    //存储用户离线消息
    private Vector list;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userName") String userName, Session session) throws IOException, EncodeException {
        this.session = session;
        this.userName = userName;//接收到发送消息的人员编号
        webSocketSet.put(userName, this);     //加入用户名和通讯的当前websocket对象
        addOnlineCount();           //在线数加1
        System.out.println("用户" + userName + "上线！ 当前用户为" + getOnlineCount());
//            sendMessage("连接成功");
        //1.告诉用户连接成功；
        //2.把保存的用用户信息发送给指定的用户
        //3.发送后把数据清空
        for (String user : webUser.keySet()) {
            if (userName.equals(user)) {
                sendListMessage(webUser.get(user));
                webUser.remove(userName);
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this.userName);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("用户" + this.userName + "下线" + "当前用户量" + onlineCount);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到消息" + message);


    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送单个消息
     *
     * @param informEntity
     * @throws IOException
     * @throws EncodeException
     */
    public void sendMessage(InformEntity informEntity) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(informEntity);
    }

    /**
     * 离线推送多个消息
     *
     * @param list
     * @throws IOException
     * @throws EncodeException
     */
    public void sendListMessage(List<InformEntity> list) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(list);
    }

    /**
     * 发送信息给指定用户，如果用户不在线则保存信息
     *
     * @param informEntity
     * @throws IOException
     */
    @RabbitListener(queues = "user")
    public void sendtoUser(InformEntity informEntity) throws IOException, EncodeException {
//用户在线就把信息推送给用户
        if (webSocketSet.get(informEntity.getUserName()) != null) {
            webSocketSet.get(informEntity.getUserName()).sendMessage(informEntity);
//            if(!id.equals(sendUserId)) {
//                webSocketSet.get(sendUserId).sendMessage( message);
//            } else {
//                webSocketSet.get(sendUserId).sendMessage(message);
//            }
        } else {
            //如果用户不在线把用户信息储存
            //用户以前的消息队列中有消息就继续增加
            //没有消息对列就创建
            if (webUser.get(informEntity.getUserName()) != null) {
                list = webUser.get(informEntity.getUserName());
                list.add(informEntity);
            } else {
                list = new Vector();
                list.add(informEntity);
            }
            //放入map
            webUser.put(informEntity.getUserName(), list);

        }
    }

    /**
     * 监听的管理员的通知
     *
     * @param informEntity
     * @throws IOException
     * @throws EncodeException
     */
    @RabbitListener(queues = "Administrator")
    public void sendtoAdmin(InformEntity informEntity) throws IOException, EncodeException {
        if (webSocketSet.get(informEntity.getUserName()) != null) {
            webSocketSet.get(informEntity.getUserName()).sendMessage(informEntity);
//            if(!id.equals(sendUserId)) {
//                webSocketSet.get(sendUserId).sendMessage( message);
//            } else {
//                webSocketSet.get(sendUserId).sendMessage(message);
//            }
        } else {
            //如果用户不在线把用户信息储存
            //用户以前的消息队列中有消息就继续增加
            //没有消息对列就创建
            if (webUser.get(informEntity.getUserName()) != null) {
           list=webUser.get(informEntity.getUserName());
           list.add(informEntity);
            } else {
                list = new Vector();
                list.add(informEntity);
            }
            //放入map
            webUser.put(informEntity.getUserName(), list);

        }
    }


    /**
     * 发送信息给所有人
     *
     * @param informEntity
     * @throws IOException
     */

    public void sendtoAll(InformEntity informEntity) throws IOException {
        for (String key : webSocketSet.keySet()) {
            try {
                webSocketSet.get(key).sendMessage(informEntity);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        //用户session对象加一
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
