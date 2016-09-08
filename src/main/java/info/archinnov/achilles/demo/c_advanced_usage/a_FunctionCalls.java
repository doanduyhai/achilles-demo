package info.archinnov.achilles.demo.c_advanced_usage;

import javax.inject.Inject;

import info.archinnov.achilles.generated.function.SystemFunctions;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.generated.meta.entity.EmailMessage_AchillesMeta;
import info.archinnov.achilles.type.TypedMap;

public class a_FunctionCalls {

    @Inject
    EmailMessage_Manager manager;


    public void function_calls() throws Exception {

        final EmailMessage_AchillesMeta.ColumnsForFunctions COLUMNS = EmailMessage_AchillesMeta.COLUMNS;

        final TypedMap columns = manager
                .dsl()
                .select()
                .function(SystemFunctions.writetime(COLUMNS.SUBJECT), "writetime_subject")
                .function(SystemFunctions.ttl(COLUMNS.BODY), "ttl_body")
                .fromBaseTable()
                .where()
                .userLogin().Eq(null)
                .inboxType().Eq(null)
                .emailId().Eq(null)
                .getTypedMap();

    }
}
