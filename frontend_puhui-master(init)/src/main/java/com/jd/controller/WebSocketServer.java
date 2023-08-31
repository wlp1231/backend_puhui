package com.jd.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *这里的cuttingMergeId可以理解为房间Id,
 */
@ServerEndpoint("/webSocket/chat/{cuttingMergeId}/{permId}")
@Component
public class WebSocketServer {

    // 使用map来收集session，key为cuttingMergeId，value为同一个房间的用户集合
    // concurrentMap的key不存在时报错，不是返回null

    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    private static final Map<String, String> permIdList = new ConcurrentHashMap();
    @OnOpen
    public void connect(@PathParam("cuttingMergeId") String cuttingMergeId,
                        @PathParam("permId") String permId, Session session) throws Exception {
        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(cuttingMergeId)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<Session>();
            // 添加用户
            room.add(session);

            rooms.put(cuttingMergeId, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(cuttingMergeId).add(session);
        }
        System.err.println("permId"+permId);
        System.out.println("a client has connected!");
    }

    @OnClose
    public void disConnect(@PathParam("cuttingMergeId") String cuttingMergeId,@PathParam("permId") String permId, Session session) {
        rooms.get(cuttingMergeId).remove(session);
        System.out.println("a client has disconnected"+permId);
    }

    @OnMessage
    public void receiveMsg(@PathParam("cuttingMergeId") String cuttingMergeId,@PathParam("permId") String permId,
                           String msg, Session session) throws Exception {
        // 此处应该有html过滤
        msg = permId + ":" + msg;
//        System.out.println(msg);
        // 接收到信息后进行广播
        broadcast(cuttingMergeId, msg);
    }

    // 按照房间名进行广播
    public static void broadcast(String cuttingMergeId, String msg) throws Exception {
        for (Session session : rooms.get(cuttingMergeId)) {
            session.getBasicRemote().sendText(msg);
        }
    }
}
