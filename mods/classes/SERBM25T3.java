package org.terrier.matching.models;

public class SERBM25T3 extends WeightingModel
{
    private static final long serialVersionUID = 1L;
    private static final String name = "SERBM25T3";
    private double k1;
    private double b;

    public SERBM25T3()
    {
	super();
	k1 = 2.0;
	b  = 0.75;
    }
    public final String getInfo() {return name;}

    public final double log(double n)
    {
	return Math.log(n)/Math.log(2.0);
    }

    public final double score(double tf, double dl)
    {
	double N    = numberOfDocuments;
	double n    = documentFrequency;
	double adl  = averageDocumentLength;
	double tfq  = keyFrequency;
	double w    = tf / (k1 * (1.0 - b + b * (dl / adl)) + tf) * log((N - n + 0.5) / (n + 0.5));
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
