package info.archinnov.achilles.demo.f_query_api;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;

import info.archinnov.achilles.demo.c_data_types_and_codecs.EmailMessage;
import info.archinnov.achilles.demo.c_data_types_and_codecs.Interlocutor;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.generated.meta.entity.EmailMessage_AchillesMeta;
import info.archinnov.achilles.internals.metamodel.ListProperty;
import info.archinnov.achilles.internals.metamodel.UDTProperty;
import info.archinnov.achilles.type.TypedMap;

public class b_NativeQuery {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        /**
         * Create your own
         * 1. bound statement
         * 2. regular statement with ENCODED bound values
         * 3. prepared statement with ENCODED bound values
         */
        final BoundStatement bs = null;
        final RegularStatement regularStatement = null;
        final PreparedStatement preparedStatement = null;
        final List<Object> encodedBoundValues = new ArrayList<>();

        emailManager.query().nativeQuery(bs).execute();
        emailManager.query().nativeQuery(regularStatement, encodedBoundValues).execute();
        emailManager.query().nativeQuery(preparedStatement, encodedBoundValues).execute();


        /**
         * How to encode yourself manually each bound value ?
         */
        String query = "INSERT INTO emails(userlogin, inboxtype, emailid, subject, body, sender, " +
                "recipients) VALUES (?, ?, ?, ?, ?, ?, ?)";

        RegularStatement insert = new SimpleStatement(query);

        /**
         * Leverage meta data for ENCODING/DECODING
         */
        final UDTProperty<EmailMessage, Interlocutor> senderMeta = EmailMessage_AchillesMeta.sender;
        final ListProperty<EmailMessage, Interlocutor, UDTValue> recipientsMeta = EmailMessage_AchillesMeta.recipients;

        encodedBoundValues.add("johnDoe"); // userLogin
        encodedBoundValues.add("MAIN");  // inboxType
        encodedBoundValues.add(UUIDs.timeBased()); // emailId
        encodedBoundValues.add("Double check the last commit"); // subject
        encodedBoundValues.add("Can you please check the last commit ? It looks suspicious"); // body
        encodedBoundValues.add(senderMeta.encodeFromJava(new Interlocutor()));  // sender
        encodedBoundValues.add(recipientsMeta.encodeFromJava(Arrays.asList(new Interlocutor()))); // recipients

        emailManager.query().nativeQuery(insert, encodedBoundValues);


        /**
         * TypedMap
         *
         * BoundStatement = SELECT subject,body,sender FROM emails WHERE ...
         */
        BoundStatement select = null;

        final TypedMap returnedRow = emailManager
                .query()
                .nativeQuery(select)
                .getOne();

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
