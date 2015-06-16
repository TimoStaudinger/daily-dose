package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.model.mapping.UserMapper;
import com.timostaudinger.dailydose.util.Frequency;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.timostaudinger.dailydose.model.generated.Tables.USER;

public final class UserDAO {
    private DSLContext dslContext;

    public UserDAO() {
        dslContext = DSL.using(Database.getConnection(), SQLDialect.MYSQL);
    }

    public boolean create(User user) {
        return dslContext.insertInto(USER, USER.EMAIL, USER.NAME, USER.ACTIVE, USER.FREQUENCY, USER.CHANGED_ON, USER.CREATED_ON)
                .values(user.getEmail(), user.getName(), user.isActive(), user.getFrequency().ordinal(), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()))
                .returning(USER.ID).fetchOne().getId() != null;
    }

    public List<User> findAllActive(Frequency frequency) {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(USER.FREQUENCY.eq(frequency.ordinal()));
        conditions.add(USER.ACTIVE.eq(true));
        Result<Record> result = dslContext.select().from(USER).where(conditions).fetch();

        return UserMapper.map(result);
    }
}
