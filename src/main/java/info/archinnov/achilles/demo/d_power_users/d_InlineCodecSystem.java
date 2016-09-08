package info.archinnov.achilles.demo.d_power_users;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import info.archinnov.achilles.annotations.Codec;
import info.archinnov.achilles.annotations.Column;

/**
 * Examples of INLINE codec declaration
 */
public class d_InlineCodecSystem {

    @Column
    @Codec(JodaTimeToLong.class)
    private DateTime dateTime;

    @Column
    private List<@Codec(JodaTimeToLong.class) DateTime> dateTimeList;

    @Column
    private Map<@Codec(JodaTimeToLong.class) DateTime, String> mapWithDateTime;

}
