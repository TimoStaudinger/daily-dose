/**
 * This class is generated by jOOQ
 */
package com.timostaudinger.dailydose.model.generated;


import com.timostaudinger.dailydose.model.generated.tables.Token;
import com.timostaudinger.dailydose.model.generated.tables.User;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in dailydose
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.6.1"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

    /**
     * The table dailydose.token
     */
    public static final Token TOKEN = com.timostaudinger.dailydose.model.generated.tables.Token.TOKEN;

    /**
     * The table dailydose.user
     */
    public static final User USER = com.timostaudinger.dailydose.model.generated.tables.User.USER;
}
