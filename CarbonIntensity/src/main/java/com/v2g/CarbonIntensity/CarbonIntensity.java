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
 * CarbonIntensity.java
 * 
 * Created 2017-09-30 by Jim Hunt
 *******************************************************************************/
package com.v2g.CarbonIntensity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.awt.Window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.ui.RefineryUtilities;

public class CarbonIntensity
{
    private void Log(final String str) {
        System.out.println(str);
    }
    
    public void GetIntensity(final String suffix, final boolean log) {
        try {
            final URL uri = new URL("https://k1nehbcl85.execute-api.eu-west-2.amazonaws.com/v1/" + suffix);
            final HttpURLConnection con = (HttpURLConnection)uri.openConnection();
            con.setRequestMethod("GET");
            final int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                this.Log("Error " + responseCode + " GETting " + uri);
            }
            
            final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            final StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            if (log) {
                this.Log("\n**** UK Carbon Intensity ****");
            }
            
            final Gson gson = new Gson();
            final JsonParser parser = new JsonParser();
            final JsonObject json = parser.parse(response.toString()).getAsJsonObject();
            final JsonArray array = parser.parse(json.get("data").toString()).getAsJsonArray();
            final ArrayList<TimedIntensity> list = new ArrayList<TimedIntensity>();
            for (final JsonElement element : array) {
                final TimedIntensity event = gson.fromJson(element, TimedIntensity.class);
                list.add(event);
                if (log) {
                    this.Log(event.toString());
                }
            }
            
            final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            final Date date = new Date();
            final LineChart chart = new LineChart("UK Carbon Intensity " + dateFormat.format(date), list);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen((Window)chart);
            chart.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

