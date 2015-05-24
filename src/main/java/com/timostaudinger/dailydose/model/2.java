package com.timostaudinger.dailydose.model;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private int id;
    private String email;
    private short frequency;
    private boolean active;
    private Date createdOn;
    private Date changedOn;

    private ArrayList<Token> tokens;

    public User(int id, String email, short frequency, boolean active) {
        this.id = id;
        this.email = email;
        this.frequency = frequency;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getFrequency() {
        return frequency;
    }

    public void setFrequency(short frequency) {
        this.frequency = frequency;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getChangedOn() {
        return changedOn;
    }
}
