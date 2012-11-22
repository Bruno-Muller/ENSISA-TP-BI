package mining.algorithme;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public abstract class Algo {
	
	public static double simCosinus(String string1, String string2) {
		
		HashMap<String, Integer> occurence1 = new HashMap<String, Integer>();
		HashMap<String, Integer> occurence2 = new HashMap<String, Integer>();
		// lister l’intégralité des différents mots
		
		// text1
		String[] tab1 = string1.split(" ");
		for (int i=0; i< tab1.length; i++) {
			occurence1.put(tab1[i], 0);
			occurence2.put(tab1[i], 0);
		}
		
		//text2
		String[] tab2 = string2.split(" ");
		for (int i=0; i< tab2.length; i++) {
			occurence1.put(tab2[i], 0);
			occurence2.put(tab2[i], 0);
		}
		
		// calcul des occurences
		//text1
		for (int i= 0; i< tab1.length; i++)
				occurence1.put(tab1[i], new Integer(occurence1.get(tab1[i]).intValue() + 1));

		//text2
		for (int i= 0; i< tab2.length; i++)
				occurence2.put(tab2[i], new Integer(occurence2.get(tab2[i]).intValue() + 1));
		
		
		// calcul similarité cosinus
		//produit scalaire
		double scalaire = 0;
		Iterator<String> it = occurence1.keySet().iterator();
		while (it.hasNext()) {
			String word = it.next();
			scalaire += (occurence1.get(word) * occurence2.get(word));
		}

		return scalaire/(Algo.norme(occurence1) * Algo.norme(occurence2));
	}
	
	public static double norme(HashMap<String, Integer> occ) {
		double norme = 0;
		Iterator<String> it = occ.keySet().iterator();
		while (it.hasNext()) {
			int in = occ.get(it.next()).intValue();
			norme += (in * in);
		}
		norme = Math.sqrt(norme);
		return norme;
	}
	
	public static HashMap<String, Integer> computeOccurences(String string) {

		// Calcul les occurences
		HashMap<String, Integer> occurence = new HashMap<String, Integer>();
		String[] tab = string.split(" ");
		for (int i= 0; i< tab.length; i++) {
			Integer in = occurence.get(tab[i]);
			if (in == null)
				occurence.put(tab[i], 1);
			else
				occurence.put(tab[i], new Integer(in.intValue() + 1));

		}
		return occurence;
	}

	public static String porterStemmer(String input) throws IOException {

		String[] abc = { "when", "while", "who", "thi", "up", "have",
				"don", "ha", "hi", "him", "so", "out", "an", "that", "is",
				"in", "the", "he", "she", "her", "s", "i", "m", "t",
				"after", "from", "all", "can", "do", "which", "doesn", "go" };
		HashSet<String> stopWords = new HashSet<String>(Arrays.asList(abc));

		TokenStream ts = new LowerCaseTokenizer(Version.LUCENE_36, new StringReader(input));
		ts = new StandardFilter(Version.LUCENE_36, ts);
		ts = new PorterStemFilter(ts);
		ts = new StopFilter(Version.LUCENE_36, ts, stopWords);

		boolean hasnext = ts.incrementToken();
		
		StringBuffer sb = new StringBuffer();
		while (hasnext) {
			CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
			if (sb.length() > 0)
				sb.append(" ");
			sb.append(ta.toString());
			hasnext = ts.incrementToken();
		}
		ts.close();

		return sb.toString();
	}

}
