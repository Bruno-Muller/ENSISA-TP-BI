package mining.serie;

import java.io.IOException;
import java.util.HashMap;
import mining.algorithme.Algo;

public class Episode {

	private String id;
	private String title;
	private String summary;

	Episode(String id, String title, String summary) {
		this.id = id;
		this.title = title;
		this.summary = summary;
	}

	public String getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getSummary() {
		return this.summary;
	}
	
	public String getTags() {
		String result = null;
		try {
			result = Algo.porterStemmer(this.summary);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public HashMap<String, Integer> getOccurence() {
		return Algo.computeOccurences(this.getTags());
	}
	
	public double compareWith(String string) {
		double similarity = 0;
		try {
			similarity = Algo.simCosinus(Algo.porterStemmer(this.summary), Algo.porterStemmer(string));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return similarity;
	}
	
	public double compareWith(Episode episode) {
		return this.compareWith(episode.summary);
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(this.id);
		string.append(" - ");
		string.append(this.title);
		return string.toString();
	}

}
