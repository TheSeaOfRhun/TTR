package org.terrier.matching.models;

public class CHECK extends WeightingModel {

    private static final long serialVersionUID = 1L;
    private static final String name = "CHECK";

    public CHECK() {super();}
    public final String getInfo() {return name;}

    public final double log(double n, double b){
	return Math.log(n)/Math.log(b);
    }

    public final double score(double tf, double dl) {
	double N    = numberOfDocuments;
	double n    = documentFrequency;
	double avdl = averageDocumentLength;
	double k1   = 1.2d;
	double b    = 0.75d;
	double qtf  = keyFrequency;
	double w;
	w = qtf * k1 * tf / (tf + k1*(1-b + b*dl/avdl)) * log(N/n+1,2);
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
