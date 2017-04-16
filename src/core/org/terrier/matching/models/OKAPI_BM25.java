package org.terrier.matching.models;

public class OKAPI_BM25 extends WeightingModel
{
    private static final long serialVersionUID = 1L;
    private static final String name = "OKAPI_BM25";
    private double k1;
    private double b;

    public OKAPI_BM25()
    {
	super();
    }
    public final String getInfo() {return name;}

    public final double score(double tf, double dl)
    {
	// OKAPI BM25
	// K1   = 2.0;
	// K3   = 1000.0;
	// b    = 0.8;
	// tf_  = ((K1 + 1) * tf) / (K1 * (1 - b + b * (dl / avg(dl))) + tf);	
	// df_  = log((n - df + 0.5) / (df + 0.5))
	// qtf_ = ((K3 + 1) * qtf) / (K3 + qtf)
	
	double n    = numberOfDocuments;
	double df   = documentFrequency;
	double adl  = averageDocumentLength;
	double qtf  = keyFrequency;
	double w    = 3.0 * tf / (0.4 + 1.6 * dl / adl + tf) * Math.log((n - df + 0.5) / (df + 0.5)) * 1001.0 * qtf / (1000.0 + qtf);
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
