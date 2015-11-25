package org.terrier.matching.models;

public class BXX extends WeightingModel {

    private static final long serialVersionUID = 1L;
    private static final String name = "BXX";

    public BXX() {super();}
    public final String getInfo() {return name;}

    public final double log(double n, double b){
	return Math.log(n)/Math.log(b);
    }

    public final double score(double tf, double dl) {
	double N    = numberOfDocuments;
	double n    = documentFrequency;
	double w;
	w = 1.0 * 1.0 / 1.0;
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
