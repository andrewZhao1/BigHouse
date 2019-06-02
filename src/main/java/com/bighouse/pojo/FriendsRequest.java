package com.bighouse.pojo;

public class FriendsRequest {
    private String id;
    private String sendUserId;
    private String acceptUserId;
    private Integer requestDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Integer getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Integer requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}