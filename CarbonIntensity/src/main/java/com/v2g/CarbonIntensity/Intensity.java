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
 * Intensity.java
 * 
 * Created 2017-09-30 by Jim Hunt
 *******************************************************************************/
package com.v2g.CarbonIntensity;

import com.google.gson.annotations.SerializedName;

public class Intensity {
	@SerializedName("actual")
	private int actualIntensity;
	@SerializedName("forecast")
	private int forecastIntensity;
	@SerializedName("index")
	private String index;
	
	public int getActualIntensity()
	{
		return actualIntensity;
	}
	
	public int getForecastIntensity()
	{
		return forecastIntensity;
	}
	
	public String getIndex()
	{
		return index;
	}

	public void setActualIntensity(int intensity)
	{
		actualIntensity = intensity;
	}
	
	public void setForecastIntensity(int intensity)
	{
		forecastIntensity = intensity;
	}
	
	public void setIndex(String newIndex)
	{
		index = newIndex;
	}
}
