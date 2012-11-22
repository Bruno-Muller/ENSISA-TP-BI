package mining.ui;

import javax.swing.JPanel;

import mining.serie.Episode;
import mining.serie.Season;
import mining.serie.Serie;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class View extends JPanel {
	
	private Serie model;
	private Controller controller;
	private DefaultListModel listModel;
	
	private JTextField textFieldUrl;
	private JComboBox comboBoxSeasons;
	private JTextArea textAreaSummary;
	private JList listEpisodes;
	private JPanel panelTags;
	private JPanel panelListEpisodes;
	private JLabel lblEpisode;
	private JPanel panelSearch;
	private JTextArea textAreaSearch;
	private JPanel panelComparator;
	private JScrollPane scrollPane_1;
	private JLabel lblSerie;

	/**
	 * Create the panel.
	 */
	public View(Serie model) {
		this.model = model;
		this.controller = defaultController(model);
		this.controller.setView(this);
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		this.listModel = new DefaultListModel();
		
		JPanel panelLienSerie = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelLienSerie, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panelLienSerie, 10, SpringLayout.WEST, this);
		panelLienSerie.setBorder(new TitledBorder(null, "Lien de la s\u00E9rie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelLienSerie);
		panelLienSerie.setLayout(new BoxLayout(panelLienSerie, BoxLayout.X_AXIS));
		
		textFieldUrl = new JTextField();
		panelLienSerie.add(textFieldUrl);
		springLayout.putConstraint(SpringLayout.WEST, textFieldUrl, 116, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldUrl, -542, SpringLayout.SOUTH, this);
		textFieldUrl.setText("http://www.imdb.com/title/tt0460649/");
		textFieldUrl.setColumns(10);
		
		JButton buttonGo = new JButton("Go");
		panelLienSerie.add(buttonGo);
		springLayout.putConstraint(SpringLayout.WEST, buttonGo, 502, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, buttonGo, 11, SpringLayout.NORTH, this);
		buttonGo.addItemListener(this.controller);
		
		buttonGo.addActionListener(this.controller);
		springLayout.putConstraint(SpringLayout.EAST, textFieldUrl, -6, SpringLayout.WEST, buttonGo);
		
		JPanel panelSeason = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panelSeason, -160, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, panelLienSerie, -10, SpringLayout.WEST, panelSeason);
		springLayout.putConstraint(SpringLayout.NORTH, panelSeason, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panelSeason, -10, SpringLayout.EAST, this);
		panelSeason.setBorder(new TitledBorder(null, "Num\u00E9ro de la saison", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelSeason);
		panelSeason.setLayout(new BoxLayout(panelSeason, BoxLayout.X_AXIS));
		
		JLabel lblSaison = new JLabel("Saison");
		panelSeason.add(lblSaison);
		springLayout.putConstraint(SpringLayout.NORTH, lblSaison, 16, SpringLayout.NORTH, this);
		
		comboBoxSeasons = new JComboBox();
		panelSeason.add(comboBoxSeasons);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxSeasons, 12, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxSeasons, -10, SpringLayout.EAST, this);
		this.comboBoxSeasons.addItemListener(this.controller);
		springLayout.putConstraint(SpringLayout.EAST, lblSaison, -6, SpringLayout.WEST, comboBoxSeasons);
		
		panelListEpisodes = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panelListEpisodes, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panelListEpisodes, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panelListEpisodes, 300, SpringLayout.WEST, this);
		panelListEpisodes.setBorder(new TitledBorder(null, "Liste des \u00E9pisodes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelListEpisodes);
		SpringLayout sl_panelListEpisodes = new SpringLayout();
		panelListEpisodes.setLayout(sl_panelListEpisodes);
		
		JPanel panelEpisode = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panelEpisode, 10, SpringLayout.EAST, panelListEpisodes);
		springLayout.putConstraint(SpringLayout.EAST, panelEpisode, -310, SpringLayout.EAST, this);
		panelEpisode.setBorder(new TitledBorder(null, "Episode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelEpisode);
		SpringLayout sl_panelEpisode = new SpringLayout();
		panelEpisode.setLayout(sl_panelEpisode);
		
		lblEpisode = new JLabel("Pas d'\u00E9pisode s\u00E9lectionn\u00E9");
		sl_panelEpisode.putConstraint(SpringLayout.NORTH, lblEpisode, 2, SpringLayout.NORTH, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.WEST, lblEpisode, 2, SpringLayout.WEST, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.EAST, lblEpisode, -2, SpringLayout.EAST, panelEpisode);
		panelEpisode.add(lblEpisode);
		
		JLabel lblTags = new JLabel("Tags:");
		sl_panelEpisode.putConstraint(SpringLayout.NORTH, lblTags, 2, SpringLayout.SOUTH, lblEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.WEST, lblTags, 2, SpringLayout.WEST, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.EAST, lblTags, -2, SpringLayout.EAST, panelEpisode);
		panelEpisode.add(lblTags);
		
		panelTags = new JPanel();
		sl_panelEpisode.putConstraint(SpringLayout.NORTH, panelTags, 2, SpringLayout.SOUTH, lblTags);
		sl_panelEpisode.putConstraint(SpringLayout.WEST, panelTags, 2, SpringLayout.WEST, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.EAST, panelTags, -2, SpringLayout.EAST, panelEpisode);
		panelEpisode.add(panelTags);
		springLayout.putConstraint(SpringLayout.EAST, panelTags, -321, SpringLayout.EAST, this);
		panelTags.setForeground(Color.BLACK);
		panelTags.setBackground(Color.WHITE);
		panelTags.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		springLayout.putConstraint(SpringLayout.NORTH, panelTags, 89, SpringLayout.SOUTH, panelLienSerie);
		springLayout.putConstraint(SpringLayout.WEST, panelTags, 6, SpringLayout.EAST, panelListEpisodes);
		springLayout.putConstraint(SpringLayout.SOUTH, panelTags, -15, SpringLayout.NORTH, panelEpisode);
		
		JLabel lblRsum = new JLabel("R\u00E9sum\u00E9 :");
		sl_panelEpisode.putConstraint(SpringLayout.SOUTH, panelTags, -2, SpringLayout.NORTH, lblRsum);
		sl_panelEpisode.putConstraint(SpringLayout.WEST, lblRsum, 2, SpringLayout.WEST, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.EAST, lblRsum, -2, SpringLayout.EAST, panelEpisode);
		panelEpisode.add(lblRsum);
		
		textAreaSummary = new JTextArea();
		textAreaSummary.setWrapStyleWord(true);
		sl_panelEpisode.putConstraint(SpringLayout.NORTH, lblRsum, -17, SpringLayout.NORTH, textAreaSummary);
		sl_panelEpisode.putConstraint(SpringLayout.WEST, textAreaSummary, 2, SpringLayout.WEST, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.SOUTH, textAreaSummary, -2, SpringLayout.SOUTH, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.EAST, textAreaSummary, -2, SpringLayout.EAST, panelEpisode);
		sl_panelEpisode.putConstraint(SpringLayout.NORTH, textAreaSummary, -100, SpringLayout.SOUTH, panelEpisode);
		panelEpisode.add(textAreaSummary);
		springLayout.putConstraint(SpringLayout.SOUTH, textAreaSummary, -171, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textAreaSummary, -280, SpringLayout.EAST, this);
		textAreaSummary.setLineWrap(true);
		textAreaSummary.setEditable(false);
		springLayout.putConstraint(SpringLayout.WEST, textAreaSummary, 47, SpringLayout.EAST, panelListEpisodes);
		springLayout.putConstraint(SpringLayout.NORTH, textAreaSummary, 21, SpringLayout.SOUTH, panelEpisode);
		
		panelSearch = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelSearch, -170, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panelSearch, 10, SpringLayout.EAST, panelListEpisodes);
		springLayout.putConstraint(SpringLayout.SOUTH, panelEpisode, -10, SpringLayout.NORTH, panelSearch);
		springLayout.putConstraint(SpringLayout.SOUTH, panelSearch, -10, SpringLayout.SOUTH, this);
		panelSearch.setBorder(new TitledBorder(null, "Chercher un \u00E9pisode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelSearch);
		
		JButton btnSearch = new JButton("Chercher");
		btnSearch.addActionListener(this.controller);
		
		SpringLayout sl_panelSearch = new SpringLayout();
		sl_panelSearch.putConstraint(SpringLayout.NORTH, btnSearch, -30, SpringLayout.SOUTH, panelSearch);
		sl_panelSearch.putConstraint(SpringLayout.WEST, btnSearch, -110, SpringLayout.EAST, panelSearch);
		sl_panelSearch.putConstraint(SpringLayout.SOUTH, btnSearch, -2, SpringLayout.SOUTH, panelSearch);
		sl_panelSearch.putConstraint(SpringLayout.EAST, btnSearch, -20, SpringLayout.EAST, panelSearch);
		panelSearch.setLayout(sl_panelSearch);
		panelSearch.add(btnSearch);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSearch, -71, SpringLayout.SOUTH, this);
		
		scrollPane_1 = new JScrollPane();
		sl_panelListEpisodes.putConstraint(SpringLayout.NORTH, scrollPane_1, 2, SpringLayout.NORTH, panelListEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.WEST, scrollPane_1, 2, SpringLayout.WEST, panelListEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.SOUTH, scrollPane_1, -2, SpringLayout.SOUTH, panelListEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.EAST, scrollPane_1, -2, SpringLayout.EAST, panelListEpisodes);
		panelListEpisodes.add(scrollPane_1);
		
		listEpisodes = new JList();
		scrollPane_1.setViewportView(listEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.NORTH, listEpisodes, 2, SpringLayout.NORTH, panelListEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.WEST, listEpisodes, -278, SpringLayout.EAST, panelListEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.SOUTH, listEpisodes, 74, SpringLayout.NORTH, panelListEpisodes);
		sl_panelListEpisodes.putConstraint(SpringLayout.EAST, listEpisodes, -205, SpringLayout.EAST, panelListEpisodes);
		springLayout.putConstraint(SpringLayout.WEST, listEpisodes, 110, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, listEpisodes, -20, SpringLayout.SOUTH, this);
		listEpisodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listEpisodes.setModel(this.listModel);
		springLayout.putConstraint(SpringLayout.NORTH, listEpisodes, 258, SpringLayout.SOUTH, panelTags);
		springLayout.putConstraint(SpringLayout.EAST, listEpisodes, -274, SpringLayout.WEST, btnSearch);
		this.listEpisodes.addListSelectionListener(this.controller);
		
		textAreaSearch = new JTextArea();
		textAreaSearch.setLineWrap(true);
		textAreaSearch.setWrapStyleWord(true);
		sl_panelSearch.putConstraint(SpringLayout.NORTH, textAreaSearch, 2, SpringLayout.NORTH, panelSearch);
		sl_panelSearch.putConstraint(SpringLayout.WEST, textAreaSearch, 2, SpringLayout.WEST, panelSearch);
		sl_panelSearch.putConstraint(SpringLayout.SOUTH, textAreaSearch, -2, SpringLayout.NORTH, btnSearch);
		sl_panelSearch.putConstraint(SpringLayout.EAST, textAreaSearch, -2, SpringLayout.EAST, panelSearch);
		panelSearch.add(textAreaSearch);
		
		JPanel panelRight = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panelSearch, -10, SpringLayout.WEST, panelRight);
		springLayout.putConstraint(SpringLayout.WEST, panelRight, 10, SpringLayout.EAST, panelEpisode);
		springLayout.putConstraint(SpringLayout.SOUTH, panelRight, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panelRight, -10, SpringLayout.EAST, this);
		add(panelRight);
		panelRight.setBorder(new TitledBorder(null, "Similitudes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -93, SpringLayout.WEST, panelRight);
		SpringLayout sl_panelRight = new SpringLayout();
		panelRight.setLayout(sl_panelRight);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panelRight.putConstraint(SpringLayout.NORTH, scrollPane, 2, SpringLayout.NORTH, panelRight);
		sl_panelRight.putConstraint(SpringLayout.WEST, scrollPane, 2, SpringLayout.WEST, panelRight);
		sl_panelRight.putConstraint(SpringLayout.SOUTH, scrollPane, -2, SpringLayout.SOUTH, panelRight);
		sl_panelRight.putConstraint(SpringLayout.EAST, scrollPane, -2, SpringLayout.EAST, panelRight);
		panelRight.add(scrollPane);
		
		panelComparator = new JPanel();
		scrollPane.setViewportView(panelComparator);
		panelComparator.setLayout(new BoxLayout(panelComparator, BoxLayout.Y_AXIS));
		
		lblSerie = new JLabel("S\u00E9rie :");
		springLayout.putConstraint(SpringLayout.NORTH, lblSerie, 2, SpringLayout.SOUTH, panelLienSerie);
		springLayout.putConstraint(SpringLayout.WEST, lblSerie, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, panelRight, 2, SpringLayout.SOUTH, lblSerie);
		springLayout.putConstraint(SpringLayout.NORTH, panelEpisode, 2, SpringLayout.SOUTH, lblSerie);
		springLayout.putConstraint(SpringLayout.NORTH, panelListEpisodes, 2, SpringLayout.SOUTH, lblSerie);
		add(lblSerie);

	}
	
	public void setModel(Serie model) {
		this.model = model;
		this.controller.setModel(model);
	}
	
	public Object getModel() {
		return this.model;
	}
	
	public Controller defaultController(Serie model) {
		return new Controller(model);
	}
	
	final public Controller getController() {
		return this.controller;
	}
	
	public String getUrl() {
		return this.textFieldUrl.getText();
	}
	
	public void setSeasonsList(String[] seasons) {
		this.comboBoxSeasons.removeAllItems();
		for (String season : seasons)
			this.comboBoxSeasons.addItem(season);
	}
	
	public Season getSelectedSeason() {
		return this.model.getSeason((String) this.comboBoxSeasons.getSelectedItem());
	}
	
	public void setEpisodesList(String[] episodes) {
		this.listModel.clear();
		for (String episode : episodes)
			this.listModel.addElement(episode);
		this.listEpisodes.setSelectedIndex(0);
	}
	
	public Episode getSelectedEpisode() {
		if (this.listEpisodes.getSelectedValue() != null)
			return this.getSelectedSeason().getEpisode((String) this.listEpisodes.getSelectedValue());
		if (this.listModel.size() > 0)
			this.getSelectedSeason().getEpisode((String) this.listModel.firstElement());
		return null;
	}
	
	public String getSearchText() {
		return this.textAreaSearch.getText();
	}
	
	public void setTitle(String string) {
		this.lblSerie.setText("SŽrie : " + string);
	}
	
	public void setEpisode(Episode episode) {
		this.textAreaSummary.setText(episode.getSummary());
		
		this.lblEpisode.setText(episode.getTitle());
		
		HashMap<String, Integer> occ = episode.getOccurence();
		
		Integer[] integers = new Integer[occ.values().size()];
		integers = occ.values().toArray(integers);
		
		int max = 1;
		for (Integer integer : integers)
			max = Math.max(max, integer.intValue());
		
		String[] tags = new String[occ.keySet().size()];
		tags = occ.keySet().toArray(tags);
		
		this.panelTags.removeAll();
		this.panelTags.repaint();
		for (String tag: tags){
			JLabel label = new JLabel();
			label.setText(tag);
			Font font = new Font("Arial",Font.BOLD,12*occ.get(tag));
			int color = (int) (255 - 255/max*occ.get(tag));
			label.setForeground(new Color(color, color, color));
			label.setFont(font);
			this.panelTags.add(label);
		}
			
		this.panelComparator.removeAll();
		this.panelComparator.repaint();
		
		Season season = this.getSelectedSeason();
		String[] eps = season.getEpisodesList();
		for (String ep : eps) {
			this.panelComparator.add(new JLabel(season.getEpisode(ep).getId() + " - " + season.getEpisode(ep).getTitle()));
			JProgressBar bar = new JProgressBar();
			int val = (int) (100 * episode.compareWith(this.getSelectedSeason().getEpisode(ep)));
			bar.setValue(val);
			this.panelComparator.add(bar);
		}
		
	}
}
