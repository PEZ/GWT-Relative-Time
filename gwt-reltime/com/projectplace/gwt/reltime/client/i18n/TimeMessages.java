package com.projectplace.gwt.reltime.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface TimeMessages extends Messages {
    @DefaultMessage("{1}{0}{2}")
    String time(String prefix, String timeStamp, String suffix);

    @DefaultMessage("")
    String pastPrefix();

    @DefaultMessage(" ago")
    String pastSuffix();

    @DefaultMessage("")
    String futurePrefix();

    @DefaultMessage(" from now")
    String futureSuffix();

    @DefaultMessage("moments")
    @PluralText({"one", "a moment"})
    String moments(@PluralCount @Optional int count);
    
    @DefaultMessage("{0,number} milliseconds")
    @PluralText({"one", "one millisecond"})
    String milliseconds(@PluralCount int count);
    
    @DefaultMessage("{0,number} seconds")
    @PluralText({"one", "one second"})
    String seconds(@PluralCount int count);
    
    @DefaultMessage("{0,number} minutes")
    @PluralText({"one", "one minute"})
    String minutes(@PluralCount int count);
    
    @DefaultMessage("{0,number} hours")
    @PluralText({"one", "an hour"})
    String hours(@PluralCount int count);
    
    @DefaultMessage("{0,number} days")
    @PluralText({"one", "one day"})
    String days(@PluralCount int count);
    
    @DefaultMessage("{0,number} weeks")
    @PluralText({"one", "one week"})
    String weeks(@PluralCount int count);
    
    @DefaultMessage("{0,number} months")
    @PluralText({"one", "one month"})
    String months(@PluralCount int count);
    
    @DefaultMessage("{0,number} years")
    @PluralText({"one", "a year"})
    String years(@PluralCount int count);
    
    @DefaultMessage("{0,number} decades")
    @PluralText({"one", "a decade"})
    String decades(@PluralCount int count);
    
    @DefaultMessage("{0,number} centuries")
    @PluralText({"one", "a century"})
    String centuries(@PluralCount int count);
    
    @DefaultMessage("{0,number} millennia")
    @PluralText({"one", "a millennium"})
    String millennia(@PluralCount int count);
}