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
 * LineChart.java
 * 
 * Based on examples from http://www.jfree.org/jfreechart
 * 
 * Created 2017-09-30 by Jim Hunt
 *******************************************************************************/
package com.v2g.CarbonIntensity;


import java.awt.Color;
import java.awt.BasicStroke;
import java.util.Collection;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {
	private static final long serialVersionUID = -4380452603944140716L;

	public LineChart(final String title, Collection<TimedIntensity> intensities) {

        super(title);

        final XYDataset dataset = createDataset(intensities);
        final JFreeChart chart = createChart(title, dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

    }

    private XYDataset createDataset( Collection<TimedIntensity> intensities) {
        
        final TimeSeries series1 = new TimeSeries("Forecast");
        final TimeSeries series2 = new TimeSeries("Actual");

	    for (TimedIntensity intensity : intensities) {
	    	RegularTimePeriod rtp = new Minute(intensity.getFromTime());
	    	series1.add(rtp, new Double(intensity.getForecastIntensity()));
	    	if (intensity.getActualIntensity() > 0) 
		    	series2.add(rtp, new Double(intensity.getActualIntensity()));
	    }	

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
                
        return dataset;
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(String title, final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
            title,       // chart title
            "Time",                   // x axis label
            "Intensity",              // y axis label
            dataset,                  // data
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        final BasicStroke stroke = new BasicStroke(2.0f);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, stroke);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesStroke(1, stroke);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
}
