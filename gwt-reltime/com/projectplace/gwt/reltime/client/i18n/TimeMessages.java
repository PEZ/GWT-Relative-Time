package com.projectplace.gwt.reltime.client.i18n;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface TimeMessages extends Messages {
    @DefaultMessage("moments ago")
    @PluralText({"one", "just now"})
    String momentsPast(@Optional @PluralCount int count);
    
    @DefaultMessage("moments from now")
    @PluralText({"one", "in a few moments"})
    String momentsFuture(@Optional @PluralCount int count);

    @DefaultMessage("{0,number} milliseconds ago")
    @PluralText({"one", "a millisecond ago"})
    String millisecondsPast(@PluralCount int count);
    
    @DefaultMessage("{0,number} milliseconds from now")
    @PluralText({"one", "one millisecond from now"})
    String millisecondsFuture(@PluralCount int count); 
    
    @DefaultMessage("{0,number} seconds ago")
    @PluralText({"one", "a second ago"})
    String secondsPast(@PluralCount int count);
    
    @DefaultMessage("{0,number} seconds from now")
    @PluralText({"one", "one second from now"})
    String secondsFuture(@PluralCount int count);

    @DefaultMessage("{0,number} minutes ago")
    @PluralText({"one", "a minute ago"})
    String minutesPast(@PluralCount int count);
    
    @DefaultMessage("{0,number} minutes from now")
    @PluralText({"one", "one minute from now"})
    String minutesFuture(@PluralCount int count);

    @DefaultMessage("{0,number} hours ago")
    @PluralText({"one", "an hour ago"})
    String hoursPast(@PluralCount int count);

    @DefaultMessage("{0,number} hours from now")
    @PluralText({"one", "an hour from now"})
    String hoursFuture(@PluralCount int count);

    @DefaultMessage("{0,number} days ago")
    @PluralText({"one", "yesterday"})
    String daysPast(@PluralCount int count);

    @DefaultMessage("{0,number} days from now")
    @PluralText({"one", "tomorrow"})
    String daysFuture(@PluralCount int count);

    @DefaultMessage("{0,number} weeks ago")
    @PluralText({"one", "a week ago", "two", "a fortnight ago"})
    String weeksPast(@PluralCount int count);

    @DefaultMessage("{0,number} weeks from now")
    @PluralText({"one", "one week from now", "two", "a fortnight from now"})
    String weeksFuture(@PluralCount int count);

    @DefaultMessage("{0,number} months ago")
    @PluralText({"one", "a month ago"})
    String monthsPast(@PluralCount int count);

    @DefaultMessage("{0,number} months from now")
    @PluralText({"one", "one month from now"})
    String monthsFuture(@PluralCount int count);

    @DefaultMessage("{0,number} years ago")
    @PluralText({"one", "a year ago"})
    String yearsPast(@PluralCount int count);

    @DefaultMessage("{0,number} years from now")
    @PluralText({"one", "one year from now"})
    String yearsFuture(@PluralCount int count);

    @DefaultMessage("{0,number} decades ago")
    @PluralText({"one", "a decade ago"})
    String decadesPast(@PluralCount int count);

    @DefaultMessage("{0,number} decades from now")
    @PluralText({"one", "one decade from now"})
    String decadesFuture(@PluralCount int count);

    @DefaultMessage("{0,number} centuries ago")
    @PluralText({"one", "a century ago"})
    String centuriesPast(@PluralCount int count);

    @DefaultMessage("{0,number} centuries from now")
    @PluralText({"one", "one century from now"})
    String centuriesFuture(@PluralCount int count);

    @DefaultMessage("{0,number} millennia ago")
    @PluralText({"one", "a thousand years ago"})
    String millenniaPast(@PluralCount int count);

    @DefaultMessage("{0,number} millennias from now")
    @PluralText({"one", "a thousand years from now"})
    String millenniaFuture(@PluralCount int count);
}