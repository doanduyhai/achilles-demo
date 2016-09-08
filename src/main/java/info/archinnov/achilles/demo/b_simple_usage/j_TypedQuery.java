package info.archinnov.achilles.demo.b_simple_usage;


import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;

public class j_TypedQuery {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        /**
         * Create your own bound statement
         */
        final BoundStatement bs = null;

        final EmailMessage emailMessage = emailManager.raw().typedQueryForSelect(bs).getOne();

    }

}
