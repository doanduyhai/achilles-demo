package info.archinnov.achilles.demo.b_simple_usage;

import java.util.UUID;
import javax.inject.Inject;

import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;

public class g_DeleteDSL {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        SchemaNameProvider schemaNameProvider = null;
        UUID emailId = null;

        /**
         * Simple delete
         */
        emailManager
                .dsl()
                .delete()
                .body()
                //.allColumns_From(schemaNameProvider)
                //.allColumns_FromBaseTable()
                .fromBaseTable()
                //.from(schemaNameProvider)
                .where()
                //.without_WHERE_Clause()
                .userLogin().Eq("johndoe")
                .inboxType().Eq("MAIN")
                .emailId().Eq(emailId)
                .execute();

    }
}
