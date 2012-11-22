package mining.serie;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Serie {
	
	private HashMap<String, String> urls;
	private HashMap<String, Season> seasons;
	private String[] key;
	private String url;
	private String title;
	
	public Serie() {
		this.urls = new HashMap<String, String>();
		this.seasons = new HashMap<String, Season>();
	}
	
	public void setSerie(String url) throws IOException {
		
		this.url = url;
		
		Document doc = Jsoup.connect(this.url)
				.timeout(10000)
				.userAgent("Mozilla")
				.get();
			
		this.urls.clear(); 
		Elements serie = doc.select("h1.header");
		this.title = serie.first().ownText();
		Elements season = doc.select("#maindetails_center_bottom div.article div.txt-block").first().select("span.see-more a");
		this.key = new String[season.size()];
		for (int i= 0; i< season.size(); i++) {
			this.key[i] = season.get(i).text();
			this.urls.put(this.key[i], season.get(i).attr("href"));
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Season getSeason(String string) {
		Season season = this.seasons.get(string);
		if (season == null) {
			try {
				season = new Season(string);
				season.setSeason(this.url + this.urls.get(string));
				this.seasons.put(string, season);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return season;
	}
	
	public String[] getSeasonList() {
		return this.key;
	}
}
