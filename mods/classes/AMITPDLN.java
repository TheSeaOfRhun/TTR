package org.terrier.matching.models;

public class AMITPDLN extends WeightingModel
{
    private static final long serialVersionUID = 1L;
    private static final String name = "AMITPDLN";
    private double s;

    public AMITPDLN()
    {
	super();
	s = 0.2;
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
	double w    = (1.0 + log(1.0 + log(tf))) / (1.0 - s + s * dl / adl) * log((N + 1.0) / n) * tfq;
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
