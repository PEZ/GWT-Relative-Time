/*
 * RelativeTime is an OpenSource GWT time comparison library for creating human
 * readable time. It's straight port of Lincoln Baxter's PrettyTime library.
 * 
 * Copyright (C) 2009 - Lincoln Baxter, III <lincoln@ocpsoft.com>
 * Copyright (C) 2010 - PEZ <pez@pezius.com>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see the file COPYING.LESSER3 or visit the
 * GNU website at <http://www.gnu.org/licenses/>.
 */
package com.projectplace.gwt.reltime.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;
import com.projectplace.gwt.reltime.client.units.Century;
import com.projectplace.gwt.reltime.client.units.Day;
import com.projectplace.gwt.reltime.client.units.Decade;
import com.projectplace.gwt.reltime.client.units.Hour;
import com.projectplace.gwt.reltime.client.units.JustNow;
import com.projectplace.gwt.reltime.client.units.Millennium;
import com.projectplace.gwt.reltime.client.units.Millisecond;
import com.projectplace.gwt.reltime.client.units.Minute;
import com.projectplace.gwt.reltime.client.units.Month;
import com.projectplace.gwt.reltime.client.units.Second;
import com.projectplace.gwt.reltime.client.units.Week;
import com.projectplace.gwt.reltime.client.units.Year;

/**
 * A utility for creating social-networking style timestamps. (e.g. "just now",
 * "moments ago", "3 days ago", "within 2 months")
 * <p>
 * <b>Usage:</b>
 * <p>
 * <code>
 * RelativeTime t = new RelativeTime();<br/>
 * String timestamp = t.format(new Date());<br/>
 * //result: moments from now
 * <p>
 * </code>
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
 * @author cobpez
 */
/**
 * @author cobpez
 * 
 */
public class RelativeTime {
    private volatile Date reference;
    private volatile List<TimeUnit> timeUnits;

    private static Timer timer;
    private static final int TIMER_INTERVAL = 60 * 1000;

    private static HashMap<HasText, Tended> tendedWidgets = new HashMap<HasText, Tended>();

    static {
        timer = new Timer() {
            public void run() {
                updateAllTended();
            }
        };
        timer.scheduleRepeating(TIMER_INTERVAL);        
    }
    
    /**
     * Default constructor
     */
    public RelativeTime() {
        initTimeUnits();
    }

    /**
     * Constructor accepting a Date timestamp to represent the point of
     * reference for comparison. This may be changed by the user, after
     * construction.
     * <p>
     * See {@code RelativeTime.setReference(Date timestamp)}.
     * 
     * @param reference
     */
    public RelativeTime(final Date reference) {
        this();
        setReference(reference);
    }

    /**
     * Calculate the approximate duration between the referenceDate and date
     * 
     * @param date
     * @return
     */
    public Duration approximateDuration(final Date then) {
        Date ref = reference;
        if (null == ref) {
            ref = new Date();
        }

        long difference = then.getTime() - ref.getTime();
        return calculateDuration(difference);
    }

    private void initTimeUnits() {
        timeUnits = new ArrayList<TimeUnit>();
        timeUnits.add(new JustNow());
        timeUnits.add(new Millisecond());
        timeUnits.add(new Second());
        timeUnits.add(new Minute());
        timeUnits.add(new Hour());
        timeUnits.add(new Day());
        timeUnits.add(new Week());
        timeUnits.add(new Month());
        timeUnits.add(new Year());
        timeUnits.add(new Decade());
        timeUnits.add(new Century());
        timeUnits.add(new Millennium());
    }

    private Duration calculateDuration(final long difference) {
        long absoluteDifference = Math.abs(difference);

        // Required for thread-safety
        List<TimeUnit> units = new ArrayList<TimeUnit>(timeUnits.size());
        units.addAll(timeUnits);

        Duration result = new Duration();

        for (int i = 0; i < units.size(); i++) {
            TimeUnit unit = units.get(i);
            long millisPerUnit = Math.abs(unit.getMillisPerUnit());
            long quantity = Math.abs(unit.getMaxQuantity());

            boolean isLastUnit = (i == units.size() - 1);

            if ((0 == quantity) && !isLastUnit) {
                quantity = units.get(i + 1).getMillisPerUnit()
                        / unit.getMillisPerUnit();
            }

            // does our unit encompass the time duration?
            if ((millisPerUnit * quantity > absoluteDifference) || isLastUnit) {
                result.setUnit(unit);
                if (millisPerUnit > absoluteDifference) {
                    // we are rounding up: get 1 or -1 for past or future
                    result.setQuantity(getSign(difference, absoluteDifference));
                } else {
                    result.setQuantity(difference / millisPerUnit);
                }
                result.setDelta(difference - result.getQuantity()
                        * millisPerUnit);
                break;
            }

        }
        return result;
    }

    private long getSign(final long difference, final long absoluteDifference) {
        if (0 > difference) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Calculate to the precision of the smallest provided {@link TimeUnit}, the
     * exact duration represented by the difference between the reference
     * timestamp, and {@code then}
     * <p>
     * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is
     * granular enough to represent one millisecond
     * 
     * @param then
     *            The date to be compared against the reference timestamp, or
     *            <i>now</i> if no reference timestamp was provided
     * @return A sorted {@link List} of {@link Duration} objects, from largest
     *         to smallest. Each element in the list represents the approximate
     *         duration (number of times) that {@link TimeUnit} to fit into the
     *         previous element's delta. The first element is the largest
     *         {@link TimeUnit} to fit within the total difference between
     *         compared dates.
     */
    public List<Duration> calculatePreciseDuration(final Date then) {
        if (null == reference) {
            reference = new Date();
        }

        List<Duration> result = new ArrayList<Duration>();
        long difference = then.getTime() - reference.getTime();
        Duration duration = calculateDuration(difference);
        result.add(duration);
        while (0 < duration.getDelta()) {
            duration = calculateDuration(duration.getDelta());
            result.add(duration);
        }
        return result;
    }

    /**
     * Format the given {@link Duration} object, using the {@link TimeFormat}
     * specified by the {@link TimeUnit} contained within
     * 
     * @param duration
     *            the {@link Duration} to be formatted
     * @return A formatted string representing {@code duration}
     */
    public String format(final Duration duration) {
        TimeFormat format = duration.getUnit().getFormat();
        return format.format(duration);
    }

    /**
     * Format the given {@link Date} object. This method applies the {@code
     * RelativeTime.approximateDuration(date)} method to perform its
     * calculation. If {@code then} is null, it will default to {@code new
     * Date()}
     * 
     * @param duration
     *            the {@link Date} to be formatted
     * @return A formatted string representing {@code then}
     */
    public String format(Date then) {
        if (then == null) {
            then = new Date();
        }
        Duration d = approximateDuration(then);
        return format(d);
    }

    /**
     * Get the current reference timestamp.
     * <p>
     * See {@code RelativeTime.setReference(Date timestamp)}
     * 
     * @return
     */
    public Date getReference() {
        return reference;
    }

    /**
     * Set the reference timestamp.
     * <p>
     * If the Date formatted is before the reference timestamp, the format
     * command will produce a String that is in the past tense. If the Date
     * formatted is after the reference timestamp, the format command will
     * produce a string that is in the future tense.
     * 
     * @param timestamp
     */
    public void setReference(final Date timestamp) {
        reference = timestamp;
    }

    /**
     * Get a {@link List} of the current configured {@link TimeUnit}s in this
     * instance.
     * 
     * @return
     */
    public List<TimeUnit> getUnits() {
        return Collections.unmodifiableList(timeUnits);
    }

    /**
     * Set the current configured {@link TimeUnit}s to be used in calculations
     * 
     * @return
     */
    public void setUnits(final List<TimeUnit> units) {
        this.timeUnits = units;
    }

    public void setUnits(final TimeUnit... units) {
        this.timeUnits = Arrays.asList(units);
    }

    /**
     * A RelativeTime instance can be used to keep a widget's text updated with
     * the relative time stamp as time goes.
     * 
     * @param widget
     *            A widget implementing HasText
     * @param date
     *            The Date to keep formatting
     * @return A track object for use with @link untendWidget();
     */
    public void tend(HasText widget, Date date) {
        Tended tended = tendedWidgets.get(widget);
        if (tended == null) {
            tended = new Tended(widget, date);
            tendedWidgets.put(widget, tended);
        }
        tended.update();
    }

    /**
     * Stop updating a widget's text.
     * 
     * @param tended a HasText widget to stop tending
     */
    public static void untend(HasText widget) {
        if (tendedWidgets.containsKey(widget)) {
            tendedWidgets.remove(widget);
        }
    }

    /**
     * Updates the relative timestamp of all tended widgets.
     */
    public static void updateAllTended() {
        for (Iterator<Tended> iterator = tendedWidgets.values().iterator(); iterator.hasNext();) {
            iterator.next().update();
        }
    }

    private class Tended {
        private HasText textWidget = null;
        private Date date = null;

        public Tended(HasText widget, Date date) {
            this.textWidget = widget;
            this.date = date;
        }

        public void update() {
            textWidget.setText(format(date));
        }
    }
}
