package com.timostaudinger.dailydose.model.dto;

import com.timostaudinger.dailydose.util.Frequency;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String email;
    private String name;
    private Frequency frequency;
    private boolean active;
    private Date createdOn;
    private Date changedOn;
    private Set<Token> tokens = new HashSet<>();

    public User(int id, String email, String name, Frequency frequency, boolean active, Date createdOn, Date changedOn, Set<Token> tokens) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.frequency = frequency;
        this.active = active;
        this.createdOn = createdOn;
        this.changedOn = changedOn;
        this.tokens = tokens;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    private void setFrequency(int frequency) {
        this.frequency = Frequency.values()[frequency];
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

    private void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    private void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }

    public Set<Token> getTokens() {
        return Collections.unmodifiableSet(this.tokens);
    }

    private void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getToken(String uuid) {
        for (Token token : tokens) {
            if (token.getUuid().equals(uuid)) {
                return token;
            }
        }

        return null;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }
}
