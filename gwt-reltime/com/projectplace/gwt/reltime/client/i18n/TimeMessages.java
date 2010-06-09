package com.projectplace.gwt.reltime.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface TimeMessages extends Messages {
    @DefaultMessage("")
    String pastPrefix();

    @DefaultMessage(" ago")
    String pastSuffix();

    @DefaultMessage("")
    String futurePrefix();

    @DefaultMessage(" from now")
    String futureSuffix();

    @DefaultMessage("{0} moments {2}")
    @PluralText({"one", "just now"})
    String moments(@Optional String prefix, @Optional @PluralCount int count, @Optional String suffix);
    
    @DefaultMessage("{0} {1,number} milliseconds {2}")
    @PluralText({"one", "one millisecond"})
    String milliseconds(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} seconds {2}")
    @PluralText({"one", "one second"})
    String seconds(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} minutes {2}")
    @PluralText({"one", "one minute"})
    String minutes(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} hours {2}")
    @PluralText({"one", "an hour"})
    String hours(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} days {2}")
    @PluralText({"one", "one day"})
    String days(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} weeks {2}")
    @PluralText({"one", "one week"})
    String weeks(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} months {2}")
    @PluralText({"one", "one month"})
    String months(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} years {2}")
    @PluralText({"one", "a year"})
    String years(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} decades {2}")
    @PluralText({"one", "a decade"})
    String decades(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} centuries {2}")
    @PluralText({"one", "a century"})
    String centuries(String prefix, @PluralCount int count, String suffix);
    
    @DefaultMessage("{0} {1,number} millennia {2}")
    @PluralText({"one", "a millennium"})
    String millennia(String prefix, @PluralCount int count, String suffix);
}