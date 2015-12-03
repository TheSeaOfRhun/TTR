package org.terrier.matching.models;

public class TerrierBM25B extends WeightingModel
{
    private static final long serialVersionUID = 1L;
    private static final String name = "TerrierBM25B";
    private double k1;
    private double k3;
    private double b;

    public TerrierBM25B()
    {
	super();
	k1 = 1.2;
	k3 = 8.0;
	b  = 0.75;
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
