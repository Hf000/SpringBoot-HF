package com.hufei.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:hufei
 * @CreateTime:2020-09-30
 * @Description:连接、消息管理
 */
@ServerEndpoint("/ws/{userId}")     //开启端点的方式进行访问注解
@Component                          //将该实例实例化到spring容器中
public class WebSocketServer {
    //日志
    static Log log = LogFactory.getLog(WebSocketServer.class);
    //在线数量
    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    //处理客户端连接socket
    private static Map<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    //会话信息
    private Session session;
    //用户信息
    private String userId = "";

    /**
    *@params: [userId, session]
    *@return: void
    *@description: 打开WebSokcet连接
    *@author: hufei
    *@time: 2020/10/10 10:32
    */
    @OnOpen
    public void onOPen(@PathParam("userId") String userId, Session session) {
        try {
            //处理session和用户信息
            this.session = session;
            this.userId = userId;
            if (webSocketMap.containsKey(userId)) {
                webSocketMap.remove(userId);
                webSocketMap.put(userId, this);
            } else {
                webSocketMap.put(userId, this);
                //增加在线人数
                addOnlineCount();
            }
            //处理连接成功消息的发送
            sendMessage("Server>>>>远程WebSoket连接成功");
            log.info("用户" + userId + "成功连接，当前的在线人数为" + getOnlineCount());
        } catch (Exception e) {
            log.error("打开websocket连接异常！");
        }
    }

    /**
    *@params: [message]
    *@return: void
    *@description: 服务端向客户端发送数据
    *@author: hufei
    *@time: 2020/10/10 10:35
    */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message == null ? "" : message);
            log.info("websocket连接成功，服务端向客户端发送连接成功消息");
        } catch (IOException e) {
            log.error("websocket服务端向客户端发送消息异常！");
        }
    }

    /**
    *@params: []
    *@return: void
    *@description: 增加在线人数
    *@author: hufei
    *@time: 2020/10/10 10:34
    */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
        log.info("websocket连接成功，当前在线人数加1，总的在线人数：" + getOnlineCount());
    }

    /**
    *@params: []
    *@return: AtomicInteger
    *@description: 获取在线人数的数量
    *@author: hufei
    *@time: 2020/10/10 10:36
    */
    public static synchronized AtomicInteger getOnlineCount() {
        return onlineCount;
    }

    /**
    *@params: []
    *@return: void
    *@description: 关闭连接
    *@author: hufei
    *@time: 2020/10/10 10:37
    */
    @OnClose
    public void onClose() {
        try {
            if (webSocketMap.containsKey(userId)) {
                webSocketMap.remove(userId);
                subOnlineCount();
            }
            log.info("用户退出....");
        } catch (Exception e) {
            log.error("客户端关闭连接异常！");
        }
    }

    /**
    *@params: []
    *@return: void
    *@description: 用户下线，当前在线人数减1
    *@author: hufei
    *@time: 2020/10/10 10:37
    */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
        log.info("websocket连接关闭成功，当前在线人数减1，总的在线人数：" + getOnlineCount());
    }

    /**
    *@params: [message, session]
    *@return: void
    *@description: 消息中转
    *@author: hufei
    *@time: 2020/10/10 10:38
    */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            if (StringUtils.isNotEmpty(message)) {
                try {
                    //解析消息
                    JSONObject jsonObject = JSON.parseObject(message);
                    String toUserId = jsonObject.getString("toUserId");
                    String msg = jsonObject.getString("msg");
                    if (StringUtils.isNotEmpty(toUserId) && webSocketMap.containsKey(toUserId)) {
                        webSocketMap.get(toUserId).sendMessage(msg);
                        sendMessage("发送消息成功！");
                    } else {
                        sendMessage("您推送消息的目标用户当前不在线，消息推送失败！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            log.error("消息中转发送异常！");
        }
    }

    /**
    *@params: [message, userId]
    *@return: boolean
    *@description: 服务器消息推送
    *@author: hufei
    *@time: 2020/10/10 10:38
    */
    public static boolean sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        boolean flag = false;
        try {
            if (StringUtils.isNotEmpty(userId) && webSocketMap.containsKey(userId)) {
                webSocketMap.get(userId).sendMessage(message);
                flag = true;
            } else {
                log.info("用户" + userId + "不在线");
                flag = false;
            }
        } catch (Exception e) {
            log.error("消息推送异常！");
        }
        return flag;
    }

}
