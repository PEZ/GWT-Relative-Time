Human readable relative time stamps
===================================

If you need "social", relative time references (like *2 hours ago* or *a century from now*) in your GWT application and can't be bothered with rolling your own, you can try this module. It can can be used for reporting both times in the past and in the future, from milliseconds to millenia. It aims to be the GWT "equivalent" to [the jQuery "Time Ago" plugin][2].

Language support
----------------

Currently:

 - English
 - Swedish (locale `sv`)
 - German (locale `de`)

I'll add a few more soon. If you want to contribute, use the TimeMessages_default.properties as a start.

Usage
-----

Add the latest build of `gwt-reltime.jar` (from the [Downloads section][3]) to your GWT project and inherit it in your application's `.gwt.xml` file like so:

    <inherits name="com.projectplace.gwt.reltime.RelativeTime"/>

If you want localized messages, say so:

    <extend-property name="locale" values="sv"/>

With that in place, you can use RelativeTime to keep widgets in the GWT client updated with time stamps for humans:

    relativeFormatter = new RelativeTime();
    relativeFormatter.tend(someWidget, date);

Where `someWidget` is an object implementing `HasText` and `date` is a Java `Date` instance. The tended widget will get its text updated every minute. You can use the same formatter instance to format several widgets. It's even recommended. But if you need different formatter settings please go ahead and create new instances.

It's your responsibility to tell RelativeTime to stop updating widgets that are no longer in use:

    RelativeTime.untend(someWidget);

Sometimes (like when a new posting  arrives) you want to update all relative time stamps immediately. You do that like so:

    RelativeTime.updateAllTended(); 

When you don't need the auto-updating you can simply convert a date to a relative timestamp `String`. Like so:

    String formattedTime = new RelativeTime().format(date);

Known issues
------------

Some units are large and need more attention. For instance is -2900 years reported as "2 millennia ago".

Future
------

 - More languages (at least those from the PrettyTime project)

Origins
-------

This started out as port of [PrettyTime (http://ocpsoft.com/prettytime/)][1] to GWT.


  [1]: http://ocpsoft.com/prettytime/
  [2]: http://timeago.yarp.com/
  [3]: http://github.com/PEZ/GWT-Relative-Time/downloads
