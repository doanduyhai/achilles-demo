package info.archinnov.achilles.demo.interceptors;

import info.archinnov.achilles.demo.entity.Message;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.interceptor.Interceptor;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class LoginToLowerCase implements Interceptor<Message> {
    @Override
    public void onEvent(Message message) {
        final String interlocutor = message.getInterlocutor();
        if (isNotBlank(interlocutor)) {
            message.setInterlocutor(interlocutor.toLowerCase());
        }
    }

    @Override
    public List<Event> events() {
        return Arrays.asList(Event.PRE_PERSIST, Event.PRE_UPDATE);
    }
}
