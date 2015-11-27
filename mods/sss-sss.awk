# The program reads a file containing strings that describe how to
# construct a tf*idf/ln formula and writes out Terrier-4.0 Java
# classes that incorporate that particular weighting formula.
#
# USAGE : awk -f sss-sss.awk
# input : The files sss-sss.parts and TMPL.java
# output: A file sss-sss.formulae, that contain the formulae list for
#         reference.
#         The Java classes go into classes/
#
# TMPL.java acts as a template. The program constructs as a string,
# the formula and the Java class name, and based on queues in the
# template places the two strings at the appropriate positions using
# gsub(). There is no fancy code-generation going on here.
#
# Since the program makes no sense without sss-sss.parts and
# TMPL.java, both are included here, lest they are lost. To use it
# copy-paste, and remove the #'s.
#
# I one's I don't know how to implement in Terrier-4.0 is marked OFF.
#
# The classes directory needs to be created beforehand.
#
# The Java classes have to be moved to
# terrier.TREC/src/core/org/terrier/matching/models/ and Terrier
# rebuilt (using 'ant') to make them available at run-time.

# sss-sss.parts

# TF
# b:1.0
# t:tf
# n:0.5 + 0.5 * tf / max(tf):OFF
# l:log(tf)
#
# IDF
# x:1.0
# f:log(N / n)
# p:log((N - n) / n)
# i:log((N - n + 0.5) / (n + 0.5))
#
# LN
# x:1.0
# c:1.0 / |v|:OFF
# d:dl
# l:log(dl)
# a:log(dl / adl)

# TMPL.java

# package org.terrier.matching.models;
#
# public class TMPL extends WeightingModel {
#
#     private static final long serialVersionUID = 1L;
#     private static final String name = "TMPL";
#
#     public TMPL() {super();}
#     public final String getInfo() {return name;}
#
#     public final double log(double n){
# 	return Math.log(n)/Math.log(2.0);
#     }
#
#     public final double score(double tf, double dl) {
# 	double N    = numberOfDocuments;
# 	double n    = documentFrequency;
# 	double adl  = averageDocumentLength;
# 	double w    = 1.0;
# 	return w;
#     }
#
#     @Deprecated
#     @Override
#     public final double score(double tf, double docLength,
#     			      double documentFrequency,	double termFrequency,
#     			      double keyFrequency) 
#     {return 0;}
# }


function printa(a) {
    for (i in a)
	printf("%s %s\n", i, a[i])
}

BEGIN {
    
    f = "sss-sss.parts"
    intf  = 0
    inidf = 0
    inln  = 0
    while (getline <f) {
	n = split($0, s, ":")
	# print n;continue;
	if (n == 1)
	    t = s[1]
	else if (n == 2) {
	    if (t == "TF")
		TF[s[1]] = s[2]
	    if (t == "IDF")
		IDF[s[1]] = s[2]
	    if (t == "LN")
		LN[s[1]] = s[2]
	}
    }

    # printa(TF)
    # printf("\n")
    # printa(IDF)
    # printf("\n")
    # printa(LN)
    # exit(0)
    
    x = 1

    fmt_pretty = "%2d %s%s%s (%-24s) * (%-30s) / (%-13s)\n"
    fmt_w      = "double w    = (%s) * (%s) / (%s);"
    
    for (i in TF) {
	for (j in IDF) {
	    for (k in LN) {
		
		printf(fmt_pretty, x++,
		       i, j, k,
		       TF[i], IDF[j], LN[k]) >"sss-sss.formulae"

		line  = sprintf(fmt_w, TF[i], IDF[j], LN[k])
		class = toupper(i j k)

		#printf("%s %s\n", class, line)

		fi = "TMPL.java"
		fo = "classes/" class ".java"

		while(getline l<fi) {
		    gsub("TMPL", class, l)
		    gsub(/double w .*= 1.0;/, line, l)
		    print l>fo
		}

		close(fi)
		close(fo)
	    }
	    printf("\n") >"sss-sss.formulae"
	}
    }
}
