package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.model.mapping.UserMapper;
import com.timostaudinger.dailydose.util.Frequency;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.List;

import static com.timostaudinger.dailydose.model.generated.Tables.USER;

public final class UserDAO {
    private DSLContext dslContext;

    public UserDAO() {
        dslContext = DSL.using(Database.getConnection(), SQLDialect.MYSQL);
    }

    public List<User> findAll(Frequency frequency) {
        Result<Record> result = dslContext.select().from(USER).where(USER.FREQUENCY.eq(frequency.ordinal())).fetch();

        return UserMapper.map(result);
    }
}
