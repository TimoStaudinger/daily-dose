package com.timostaudinger.dailydose.model.mapping;

import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.util.Frequency;
import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.timostaudinger.dailydose.model.generated.Tables.USER;

public final class UserMapper {
    private UserMapper() {

    }

    public static List<User> map(Result<Record> input) {
        List<User> output = new ArrayList<>();

        for (Record r : input) {
            Integer id = r.getValue(USER.ID);
            String name = r.getValue(USER.NAME);
            String email = r.getValue(USER.EMAIL);
            Frequency frequency = Frequency.values()[r.getValue(USER.FREQUENCY)];
            Boolean active = r.getValue(USER.ACTIVE);
            Date createdOn = r.getValue(USER.CREATED_ON);
            Date changedOn = r.getValue(USER.CHANGED_ON);

            User user = new User(id, email, name, frequency, active, createdOn, changedOn, null);
            output.add(user);
        }

        return output;
    }
}
