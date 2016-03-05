package info.archinnov.achilles.demo.e_dsl_api;

import java.util.UUID;
import javax.inject.Inject;

import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;

public class b_DeleteDSL {

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
                .userLogin_Eq("johndoe")
                .inboxType_Eq("MAIN")
                .emailId_Eq(emailId)
                .execute();


        /**
         * Delete the whole partition
         */
        emailManager
                .crud()
                .deleteByPartitionKeys("userLogin", "SPAM")
                .execute();
    }
}
