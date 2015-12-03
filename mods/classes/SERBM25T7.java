package org.terrier.matching.models;

public class SERBM25T7 extends WeightingModel
{
    private static final long serialVersionUID = 1L;
    private static final String name = "SERBM25T7";
    private double k1;
    private double k3;
    private double b;

    public SERBM25T7()
    {
	super();
	k1 = 2.0;
	k3 = 1000.0;
	b  = 0.8;
    }
    
    public final String getInfo() {return name;}

    public final double log(double n)
    {
	return Math.log(n) / Math.log(2.0);
    }

    public final double score(double tf, double dl)
    {
	double N    = numberOfDocuments;
	double n    = documentFrequency;
	double adl  = averageDocumentLength;
	double tfq  = keyFrequency;
	double w    = ((k1 + 1.0) * tf) / (k1 * (1.0 - b + b * (dl / adl)) + 2.0 * tf) * log((N - n + 0.5) / (n + 0.5))  * (((k3 + 1.0) * tfq) / (k3 + tfq));
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
