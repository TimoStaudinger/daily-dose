package com.timostaudinger.dailydose.model;

import java.util.Date;

public class Token {
    private String uuid;
    private boolean used;
    private Date createdOn;
    private Date changedOn;

    private User associatedUser;

    public Token(String uuid, boolean used, User associatedUser) {
        this.uuid = uuid;
        this.used = used;
        this.associatedUser = associatedUser;
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

    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getChangedOn() {
        return changedOn;
    }
}
