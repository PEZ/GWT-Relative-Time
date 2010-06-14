Human readable relative time stamps
-----------------------------------

If you need "social", relative time references in your GWT application and can't be bothered with rolling your own, you can try this library. It can can be used for reporting both times in the past and in the future, from milliseconds to centuries. You can think of it as the GWT equivalent to the [jQuery "Time Ago" plugin][2].

Language support
================

Currently:

 - English
 - Swedish

I'll add a few more soon. If you want to contribute, use the TimeMessages_default.properties as a start.

Usage
=====

Add the `gwt-reltime.jar` file (from the Downloads section) to your GWT project and inherit it in your application's `.gwt.xml` file like so:

    <inherits name="com.projectplace.gwt.reltime.RelativeTime"/>

Then, wherever you have a Date instance to present and want relative time stamps like *"2 hours ago"* or *"a century from now"* do something like:

      RelativeTime relativeFormatter = new RelativeTime();
      String formattedTime = relativeFormatter.format(date);

Where `date` is a Java `Date` instance.

Known issues
============

Some units are large and need more attention. For instance is -2900 years reported as "2 millennia ago".

Future
======

 - I'll try make a widget that encapsulates the time formatting and that also keeps the time stamps updated on the page. (Like Time Ago does.)
 - More languages (at least those from the PrettyTime project)

Origins
=======

This started out as port of [PrettyTime (http://ocpsoft.com/prettytime/)][1] to GWT.


  [1]: http://ocpsoft.com/prettytime/
  [2]: http://timeago.yarp.com/