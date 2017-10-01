/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 V2G Limited 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *******************************************************************************
 * TimedIntensity.java
 * 
 * Created 2017-09-30 by Jim Hunt
 *******************************************************************************/
package com.v2g.CarbonIntensity;

import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.google.gson.annotations.SerializedName;

public class TimedIntensity {
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH);

	@SerializedName("from")
	private String fromTime;
	@SerializedName("to")
	private String toTime;
	@SerializedName("intensity")
	private Intensity intensity;

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("From " + getFromTime() + " to " + getToTime()+"\n");
		sb.append("Forecast = " + getForecastIntensity() +
				  ", Actual = " + getActualIntensity() +
				  ", Index = " + getIndex()+"\n");
		sb.append("*****************************");
		
		return sb.toString();
		
	}

	private Date parseDate(String str)
	{
		Date date = null;
		
		try {
			date = format.parse(str);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public Date getFromTime()
	{
		return parseDate(fromTime);
	}
	
	public Date getToTime()
	{
		return parseDate(toTime);
	}

	public int getActualIntensity()
	{
		return intensity.getActualIntensity();
	}
	
	public int getForecastIntensity()
	{
		return intensity.getForecastIntensity();
	}
	
	public String getIndex()
	{
		return intensity.getIndex();
	}

	public void setFromTime(Date time)
	{
		fromTime = format.format(time);
	}
	
	public void setToTime(Date time)
	{
		toTime = format.format(time);
	}
	
}
