package org.terrier.matching.models;

public class SMART_dtb_nnn extends WeightingModel
{
    private static final long serialVersionUID = 1L;
    private static final String name = "SMART_dtb_nnn";

    public SMART_dtb_nnn()
    {
	super();
    }

    public final String getInfo() {return name;}

    public final double score(double tf, double dl)
    {
	// SMART dtb.nnn
	// s    = 0.2
	// tf_  = (1 + log(1 + log(tf))) / ((1 - s) * avg(dl) + s * dl)
	// df_  = log((n + 1) / df)
	// qtf_ = qtf
	
	double n    = numberOfDocuments;
	double df   = documentFrequency;
	double adl  = averageDocumentLength;
	double qtf  = keyFrequency;
	double w    = (1.0 + Math.log(1.0 + Math.log(tf))) / (0.8 * adl + 0.2 * dl) * Math.log((n + 1.0) / df) * qtf;
	return w;
    }

    @Deprecated
    @Override
    public final double score(double tf, double docLength,
    			      double documentFrequency,	double termFrequency,
    			      double keyFrequency) 
    {return 0;}
}
