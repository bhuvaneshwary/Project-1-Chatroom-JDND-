package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket message model
 */
public class MessageModel {
    // TODO: add message model.
    // TODO: Implement all the required fields of the message model
    //What actually happens here is that we define the type of the field required as well as how its set
    private String username;
    private String msg;
    private int onlineCount;


    private String type;


//This is the constructor
    public MessageModel(String msg, String username, int onlineCount, String type) {
        this.msg = msg;
        this.username = username;
        this.onlineCount = onlineCount;

        this.type = type;
    }



//This function converts a javascript object to a JSON object
    public static String strToJson(String msg, String username, int onlineCount, String type) {
        return JSON.toJSONString(new MessageModel(msg, username, onlineCount, type));
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOnlineCount() {

        return this.onlineCount;
    }

    public void setOnlineCount(int onlineCount) {

        this.onlineCount = onlineCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
