package com.timostaudinger.dailydose.common.model.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.timostaudinger.dailydose.common.model.dto.User;

import java.util.List;

public final class UserDAO {
    private static final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
    private static final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);

    public boolean subscribe(String email) {
        User user = new User(email);
        dynamoDBMapper.save(user);

        return true;
    }

    public boolean unsubscribe(String email) {
        User user = new User(email);
        dynamoDBMapper.delete(user);

        return true;
    }

    public List<User> findAllActive() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> users = this.dynamoDBMapper.scan(User.class, scanExpression);

        return users;
    }
}
