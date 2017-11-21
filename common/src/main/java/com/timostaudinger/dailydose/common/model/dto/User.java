package com.timostaudinger.dailydose.common.model.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "dailydose-subscribers")
public class User {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public User() {}

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
