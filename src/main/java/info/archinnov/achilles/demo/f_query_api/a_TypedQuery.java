package info.archinnov.achilles.demo.f_query_api;


import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.RegularStatement;

import info.archinnov.achilles.demo.c_data_types_and_codecs.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;

public class a_TypedQuery {

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

        final EmailMessage emailMessage = emailManager.query().typedQueryForSelect(bs).getOne();

        final EmailMessage one = emailManager.query().typedQueryForSelect(regularStatement, encodedBoundValues).getOne();

        final List<EmailMessage> list = emailManager.query().typedQueryForSelect(preparedStatement, encodedBoundValues).getList();

    }

}
