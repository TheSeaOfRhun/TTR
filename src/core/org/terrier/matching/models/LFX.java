package org.terrier.matching.models;

public class LFX extends WeightingModel {

    private static final long serialVersionUID = 1L;
    private static final String name = "LFX";

    public LFX() {super();}
    public final String getInfo() {return name;}

    public final double log(double n){
	return Math.log(n)/Math.log(2.0);
    }

    public final double score(double tf, double dl) {
	double N    = numberOfDocuments;
	double n    = documentFrequency;
	double adl  = averageDocumentLength;
	double w    = (log(tf)) * (log(N / n)) / (1.0);
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
