package info.archinnov.achilles.demo.c_advanced_usage;

import java.util.List;

import info.archinnov.achilles.annotations.Frozen;
import info.archinnov.achilles.annotations.FunctionRegistry;
import info.archinnov.achilles.demo.d_power_users.Interlocutor;

@FunctionRegistry
public interface b_FunctionRegistry {

    Long stringToLong(String input);

    String longToString(Long input);

    String udtAsString(List<@Frozen Interlocutor> interlocutors);
}
