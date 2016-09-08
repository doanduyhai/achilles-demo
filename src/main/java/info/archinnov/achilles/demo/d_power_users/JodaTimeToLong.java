package info.archinnov.achilles.demo.d_power_users;

import java.util.Date;

import org.joda.time.DateTime;

import info.archinnov.achilles.exception.AchillesTranscodingException;
import info.archinnov.achilles.type.codec.Codec;

public class JodaTimeToLong implements Codec<DateTime, Long> {

    @Override
    public Class<DateTime> sourceType() {
        return DateTime.class;
    }

    @Override
    public Class<Long> targetType() {
        return Long.class;
    }

    @Override
    public Long encode(DateTime fromJava) throws AchillesTranscodingException {
        return fromJava.getMillis();
    }

    @Override
    public DateTime decode(Long fromCassandra) throws AchillesTranscodingException {
        return new DateTime(new Date(fromCassandra));
    }
}
