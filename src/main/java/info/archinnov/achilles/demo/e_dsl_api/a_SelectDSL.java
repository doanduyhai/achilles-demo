package info.archinnov.achilles.demo.e_dsl_api;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PagingState;

import info.archinnov.achilles.demo.c_data_types_and_codecs.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;

public class a_SelectDSL {

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
                .emailId()
                .sender()
                .recipients()
                .subject()
                .body()
                //.allColumns_From(schemaNameProvider)
                //.allColumns_FromBaseTable()
                .fromBaseTable()
                //.from(schemaNameProvider)
                .where()
                //.without_WHERE_Clause()
                .userLogin_Eq("johndoe")
                .inboxType_Eq("MAIN")
                .emailId_Gt_And_Lte(fromEmail, toEmail)
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
                .userLogin_Eq("johndoe")
                .inboxType_Eq("MAIN")
                .withFetchSize(200)
                .iterator();
    }
}
