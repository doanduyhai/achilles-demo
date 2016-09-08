package info.archinnov.achilles.demo.c_advanced_usage;

import javax.inject.Inject;

import info.archinnov.achilles.generated.function.FunctionsRegistry;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.generated.meta.entity.EmailMessage_AchillesMeta;

public class c_UDF_FunctionCalls {

    @Inject
    EmailMessage_Manager manager;

    public void udf_TYPE_SAFE_function_call() {

        final EmailMessage_AchillesMeta.ColumnsForFunctions COLUMNS = EmailMessage_AchillesMeta.COLUMNS;
        manager
                .dsl()
                .select()
                .function(FunctionsRegistry.udtAsString(COLUMNS.RECIPIENTS), "udtAsString")
                .function(FunctionsRegistry.longToString(FunctionsRegistry.stringToLong(COLUMNS.BODY)), "");
    }
}
