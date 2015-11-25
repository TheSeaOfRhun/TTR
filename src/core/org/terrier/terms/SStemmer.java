/* Harman, Donna. "How effective is suffixing?." JASIS 42.1 (1991): 7-15. */

package org.terrier.terms;
import java.io.*;

public class SStemmer extends StemmerTermPipeline
{  
    protected char[] b;
    protected int i;
    protected static final int SIZE = 50;

    public SStemmer()
    {  
	b = new char[SIZE];
	i = 0;
    }

    public SStemmer(TermPipeline next)
    {
	super(next);
	b = new char[SIZE];
	i = 0;
    }

    public String toString() { return new String(b, 0, i); }
    public int getResultLength() { return i; }

    public void stem()
    {
	i = b.length;

	if(i <= 3)
	    return;

	if(b[i-3] == 'i' && b[i-2] == 'e' && b[i-1] == 's' &&
	   b[i-4] != 'e' && b[i-4] != 'a'){
	    b[i-3] = 'y';
	    i = i - 2;
	    return;
	}
	
	if(b[i-2] == 'e' && b[i-1] == 's' && 
	   b[i-3] != 'a' && b[i-3] != 'e' && b[i-3] != 'o'){
	    b[i-2] = 'e';
	    i = i - 1;
	    return;
	}

	if(b[i-1] == 's' &&
	   b[i-2] != 'u' && b[i-2] != 's'){
	    i = i - 1;
	    return;
	}
    }
	
    public static void main(String[] args)
    {
	String w = new String(args[0]);
	SStemmer s = new SStemmer();
	s.b = w.toCharArray();
	s.stem();
	System.out.println(s.toString());
    }

    /* Stemmer implementation */
    /** {@inheritDoc} */
    public String stem(String w) {
	if(w.length() > SIZE){
	    char[] new_b = new char[w.length() + SIZE];
	    this.b = new_b;
	}
	this.b = w.toCharArray();
	this.stem();
	return this.toString();
    }
}
