package com.timostaudinger.dailydose.common.model.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.timostaudinger.dailydose.common.model.dto.User;

import java.util.List;

public final class UserDAO {
//    public boolean create(User user) {
//        try {
//            return dslContext.insertInto(USER, USER.EMAIL, USER.NAME, USER.ACTIVE, USER.FREQUENCY, USER.CHANGED_ON, USER.CREATED_ON)
//                    .values(user.getEmail(), user.getName(), user.isActive(), user.getFrequency().ordinal(), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()))
//                    .returning(USER.ID).fetchOne().getId() != null;
//        } catch (DataAccessException e) {
//            return false;
//        }
//    }

    public List<User> findAllActive() {
        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        final DynamoDBMapper mapper = new DynamoDBMapper(client);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> users = mapper.scan(User.class, scanExpression);

        return users;
    }

//    public User find(String email) {
//        Record record = dslContext.select().from(USER).where(USER.EMAIL.eq(email)).fetchOne();
//        return record != null ? UserMapper.map(record) : null;
//    }
}
