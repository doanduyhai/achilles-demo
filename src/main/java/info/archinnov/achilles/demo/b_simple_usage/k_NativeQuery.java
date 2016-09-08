package info.archinnov.achilles.demo.b_simple_usage;


import javax.inject.Inject;

import com.datastax.driver.core.*;

import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.TypedMap;

public class k_NativeQuery {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        /**
         * Create your own bound statement
         */
        final BoundStatement bs = null;
        final RegularStatement regularStatement = null;

        emailManager.raw().nativeQuery(bs).execute();


        /**
         * TypedMap
         *
         * BoundStatement = SELECT subject,body,sender FROM emails WHERE ...
         */
        BoundStatement select = null;

        final TypedMap returnedRow = emailManager
                .raw()
                .nativeQuery(select)
                .getTypedMap();

        /**
         * With a classic Map<String, Object>:
         *
         *  - (String)returnedRow.get("subject);
         *  - (String)returnedRow.get("body);
         *  - (UDTValue)returnedRow.get("sender);
         */
        final String subject = returnedRow.<String>getTyped("subject");
        final String optionalBody = returnedRow.getTypedOr("body", "No Content");
        final UDTValue senderUDT = returnedRow.<UDTValue>getTyped("sender");
    }

}
