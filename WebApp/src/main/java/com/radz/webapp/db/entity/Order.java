package com.radz.webapp.db.entity;


import java.util.Date;

public class Order extends Module {
    private long userId;
    private long statusId;
    private Date createdAt;
    private String statusName;

    public Order() {
    }

    public Order(long statusId) {
        this.statusId = statusId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
