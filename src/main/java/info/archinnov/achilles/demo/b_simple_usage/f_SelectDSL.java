package info.archinnov.achilles.demo.b_simple_usage;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PagingState;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;

public class f_SelectDSL {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        SchemaNameProvider schemaNameProvider = null;
        UUID fromEmail = null;
        UUID toEmail = null;
        PagingState pagingState = PagingState.fromString("xxx");

        final List<EmailMessage> emails = emailManager
                .dsl()
                .select()
                .userLogin()
                .emailId()
                .sender().firstname()
                .sender().lastname()
                .recipients()
                .subject()
                .body()
                //.allColumns_From(schemaNameProvider)
                //.allColumns_FromBaseTable()
                .fromBaseTable()
                //.from(schemaNameProvider)
                .where()
                //.without_WHERE_Clause()
                .userLogin().Eq("johndoe")
                .inboxType().Eq("MAIN")
                .emailId().Gt_And_Lte(fromEmail, toEmail)
                .limit(20)
                .withConsistencyLevel(ConsistencyLevel.LOCAL_ONE)
                .withPagingState(pagingState)
                .getList();
                //.getListAsync()


        final Iterator<EmailMessage> emailsIter = emailManager
                .dsl()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .userLogin().Eq("johndoe")
                .inboxType().Eq("MAIN")
                .withFetchSize(200)
                .iterator();
    }
}
