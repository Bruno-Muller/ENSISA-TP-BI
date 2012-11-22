package mining.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mining.serie.Serie;

public class Controller implements ActionListener, ItemListener, ListSelectionListener
{
	private Serie model;
	private View view;
		
	
	public Controller(Serie newModel) {
		model = newModel;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	final public View getView() {
		return this.view;
	}
	
	public void setModel(Serie model) {
		this.model = model;
	}
	
	public Object getModel() {
		return this.model;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String action = ae.getActionCommand();
		if (action.equals("Go")) {
			Cursor oldCursor = this.view.getCursor();
			this.view.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			try {
				this.model.setSerie(this.view.getUrl());
				this.view.setTitle(this.model.getTitle());
				this.view.setSeasonsList(this.model.getSeasonList());
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				this.view.setCursor(oldCursor);
			}
		}
		else if (action.equals("Chercher")) {
			
			if (this.view.getSearchText().equals(""))
				return;
			if (this.view.getSelectedEpisode() == null)
				return;
			
			HashMap<String, Double> similarity = this.view.getSelectedSeason().search(this.view.getSearchText());
			String[] episodes = new String[similarity.size()];
			
			episodes = similarity.keySet().toArray(episodes);
			SearchResultView panel = new SearchResultView();

			for (String episode : episodes) {
				if (similarity.get(episode) == 0)
					continue;
				panel.addEpidose(episode, similarity.get(episode));
			}
			panel.setSaison(this.view.getSelectedSeason().getTitle());
			panel.setSearch(this.view.getSearchText());
			panel.setSerie(this.model.getTitle());
			
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setTitle("Recherche " + panel.searchNumber());
			frame.getContentPane().add(panel, java.awt.BorderLayout.CENTER);
			frame.setPreferredSize(new Dimension(300, 600));
			frame.pack();
			frame.setVisible(true);	
			// test
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED)
			this.view.setEpisodesList(this.view.getSelectedSeason().getEpisodesList());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && (this.view.getSelectedEpisode() != null))
				this.view.setEpisode(this.view.getSelectedEpisode());
	}
	
}

