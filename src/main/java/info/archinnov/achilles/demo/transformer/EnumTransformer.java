package info.archinnov.achilles.demo.transformer;

import info.archinnov.achilles.codec.Codec;
import info.archinnov.achilles.exception.AchillesTranscodingException;

import static info.archinnov.achilles.demo.entity.UserTracking.Action;

public class EnumTransformer implements Codec<Action, String> {

    @Override
    public Class<Action> sourceType() {
        return Action.class;
    }

    @Override
    public Class<String> targetType() {
        return String.class;
    }

    @Override
    public String encode(Action action) throws AchillesTranscodingException {
        return action.name();
    }

    @Override
    public Action decode(String actionName) throws AchillesTranscodingException {
        return Action.valueOf(actionName);
    }
}
