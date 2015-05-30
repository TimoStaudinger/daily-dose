package com.timostaudinger.dailydose.model.dto;

import java.util.Date;

public class Token {
    private String uuid;
    private boolean used;
    private Date createdOn;
    private Date changedOn;


    public Token() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    private void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    private void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }
}
