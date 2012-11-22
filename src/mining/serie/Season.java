package mining.serie;

import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Season {

	private HashMap<String, Episode> episodes;
	private String[] key;
	private String title;
	
	Season(String title) {
		this.episodes = new HashMap<String, Episode>();
		this.title = title;
	}
	
	public void setSeason(String url) throws Exception {
		
		Document doc = Jsoup.connect(url).get();
			
		this.episodes.clear(); 
		
		// On récupère les identifiants, titres et résumés
		Elements ids = doc.select("div.image a div div");
		Elements titles = doc.select("div.info strong a");
		Elements summaries = doc.select("div.item_description");
		
		// On teste qu'on a bien le même nombre d'éléments sinon il y a un problème
		if ((titles.size() != summaries.size()) || summaries.size() != ids.size())
			throw new Exception("Number of names, titles and summaries does no match.");
		
		this.key = new String[titles.size()];
		
		// On crée les épisodes
		for (int i= 0; i< titles.size(); i++) {
			Episode episode = new Episode(ids.get(i).text(), titles.get(i).text(), summaries.get(i).text());
			this.key[i] = episode.toString();
			this.episodes.put(this.key[i], episode);
		}
	}
	
	public HashMap<String, Double> search(String string) {
		HashMap<String, Double> similarity = new HashMap<String, Double>();
		String[] episodes = this.getEpisodesList();
		for(String episode : episodes)
			similarity.put(episode, this.getEpisode(episode).compareWith(string));
		return similarity;
	}
	
	public String[] getEpisodesList() {
		return this.key;
	}
	
	public Episode getEpisode(String key) {
		return this.episodes.get(key);
	}
	
	public String getTitle() {
		return this.title;
	}
	
}
