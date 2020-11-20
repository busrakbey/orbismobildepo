package com.konumsal.orbisozetmobil.OrtakUI.Bluetooth;

/**
 * Created by isahin on 17.2.2017.
 */

import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by bostjant on 5.2.2018.
 */

public class Measurements {

    public static final int MEASUREMENT_TYPE_LENGTH = 1;
    public static final int MEASUREMENT_TYPE_DIAMETER = 2;

    private List<Double> measurements;
    public int measurementType;
    public int numberOfAllMeasurements;
    public int numberOfMeasurements;

    public Measurements() {
        measurements = new ArrayList<>();
    }

    public void setType(int type) {
        measurementType = type;
    }

    private double getAverage(int numberOfMaxLastMeasurements) {
        double diameter = 0;
        double sum = 0;
        if (numberOfMaxLastMeasurements == 0 && measurements.size() > 0) {
            // if numberOfMaxLastMeasurements = 0 --> return average of all measurements
            for (Double measurement : measurements) {
                sum += measurement;
            }
            //avg
            diameter = sum / measurements.size();
        } else if (numberOfMaxLastMeasurements > 0) {
            // Average from last numberOfMaxLastMeasurements measurements
            for (int i = measurements.size()-1; i > measurements.size()-1-numberOfMaxLastMeasurements; i--) {
                sum += measurements.get(i);
            }
            //avg
            diameter = sum / numberOfMaxLastMeasurements;
        }
        return diameter;
    }

    private double getSum() {
        //calculate sum
        double length = 0;
        for (Double measurement : measurements) {
            length += measurement;
        }
        return length;
    }

    public List<Double> getMeasurements() {
        return measurements;
    }

    public void add(double measurement) {
        //add new measurement to list
        measurements.add(measurement);
    }

    /*public double getLength(Measurements measurement) {
        //get length
        double length;
        if (measurement.measurementType == Measurements.MEASUREMENT_TYPE_LENGTH) {
            length = measurement.getSum();
            numberOfMeasurements = measurement.getMeasurements().size();
        } else {
            length = -1;
        }
        return length;
    }*/

    public double getLength(Measurements measurement) {
        //get length
        double length;
        length = measurement.getSum();
        numberOfMeasurements = measurement.getMeasurements().size();
        return length;
    }

    public int getDirectDiameter()
    {
        if(getMeasurements().size() > 0) {
            String d_str = String.valueOf(getMeasurements().get(0));
            d_str = d_str.replaceAll("(\\r|\\n)", "");
            int res = getMeasurements().get(getMeasurements().size()-1).intValue();
            Log.v("return value","=>"+res);
            return res;
        }
        else
            return 0;
    }

    public double getDiameter(Measurements measurement, int numberOfMaxLastMeasurements) {
        //get diameter for specified number of measurements
        double diameter;
        if (measurement.measurementType == measurement.MEASUREMENT_TYPE_DIAMETER) {
            numberOfAllMeasurements = measurement.getMeasurements().size();
            if (numberOfMaxLastMeasurements < numberOfAllMeasurements)
                numberOfMeasurements = numberOfMaxLastMeasurements;
            else {
                numberOfMeasurements = numberOfAllMeasurements;
            }
            diameter = measurement.getAverage(numberOfMeasurements);
        } else {
            diameter = -1;
        }
        return diameter;
    }

    public String formatValue(String stringFormat, double value){
        DecimalFormat formatter = new DecimalFormat(stringFormat, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return formatter.format(value);
    }

}