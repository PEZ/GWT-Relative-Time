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
package com.projectplace.gwt.reltime.client.units;

import com.projectplace.gwt.reltime.client.AbstractTimeUnit;
import com.projectplace.gwt.reltime.client.TimeUnit;

public class Second extends AbstractTimeUnit implements TimeUnit {

	public Second() {
		super();
		millisPerUnit = 1000L;
	}

    @Override
    public String getTimeStampFuture(long quantity) {
        return timeMessages.secondsFuture((int) quantity);
    }

    @Override
    public String getTimeStampPast(long quantity) {
        return timeMessages.secondsPast((int) quantity);
    }
}