package com.hufei.websocket.controller;

import com.hufei.websocket.server.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Description: 核心控制器
 */
@RestController
public class WebSocketController {
    @RequestMapping("im")
    public ModelAndView page() {
        return new ModelAndView("ws");
    }

    /*
     * @Description: 消息推送
     */
    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws Exception {

        boolean flag = WebSocketServer.sendInfo(message, toUserId);
        return flag == true ? ResponseEntity.ok("消息推送成功...") : ResponseEntity.ok("消息推送失败，用户不在线...");
    }


}
