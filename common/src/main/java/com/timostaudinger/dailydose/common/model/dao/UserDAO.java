package com.timostaudinger.dailydose.common.model.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.timostaudinger.dailydose.common.model.dto.User;

import java.util.List;

public final class UserDAO {
    public boolean subscribe(String email) {
        return true;
    }

    public boolean unsubscribe(String email) {
        return true;
    }

    public List<User> findAllActive() {
        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        final DynamoDBMapper mapper = new DynamoDBMapper(client);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> users = mapper.scan(User.class, scanExpression);

        return users;
    }
}
