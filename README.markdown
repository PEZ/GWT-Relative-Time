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

      String formattedTime = RelativeTime.getInstance().format(date);

Where `date` is a Java `Date` instance.

You can also let RelativeTime "tend" your widgets as long as they implement the HasText interface. Like so:

      RelativeTime.getInstance().tendWidget(someWidget, date);

That will cause the formatting to be re-applied to your widgets text every thirty seconds, so that your page always present freshly formatted relative time stamps. 

Known issues
============

Some units are large and need more attention. For instance is -2900 years reported as "2 millennia ago".

Future
======

 - I'll try to make the module automatically track DOM elements that has a relativetime attribute.
 - More languages (at least those from the PrettyTime project)

Origins
=======

This started out as port of [PrettyTime (http://ocpsoft.com/prettytime/)][1] to GWT.


  [1]: http://ocpsoft.com/prettytime/
  [2]: http://timeago.yarp.com/