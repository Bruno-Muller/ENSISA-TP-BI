package mining.ui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class SearchResultView extends JPanel {
	
	private static int nbrSearch = 1;
	private JLabel lblSaison;
	private JTextArea textAreaSearch;
	private JScrollPane scrollPane;
	private JPanel panelResultat;
	private JLabel lblSrie;

	/**
	 * Create the panel.
	 */
	public SearchResultView() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, this);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Resultats", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, this);
		add(panel);
		
		JPanel panelRecherche = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, panelRecherche);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		scrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 2, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 2, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -2, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane, -2, SpringLayout.EAST, panel);
		panel.add(scrollPane);
		
		panelResultat = new JPanel();
		scrollPane.setViewportView(panelResultat);
		panelResultat.setLayout(new BoxLayout(panelResultat, BoxLayout.Y_AXIS));
		springLayout.putConstraint(SpringLayout.SOUTH, panelRecherche, 200, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panelRecherche, -10, SpringLayout.EAST, this);
		panelRecherche.setBorder(new TitledBorder(null, "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panelRecherche, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panelRecherche, 10, SpringLayout.WEST, this);
		add(panelRecherche);
		SpringLayout sl_panelRecherche = new SpringLayout();
		panelRecherche.setLayout(sl_panelRecherche);
		
		lblSaison = new JLabel("Saison");
		sl_panelRecherche.putConstraint(SpringLayout.WEST, lblSaison, 2, SpringLayout.WEST, panelRecherche);
		sl_panelRecherche.putConstraint(SpringLayout.EAST, lblSaison, -2, SpringLayout.EAST, panelRecherche);
		panelRecherche.add(lblSaison);
		
		textAreaSearch = new JTextArea();
		textAreaSearch.setEditable(false);
		textAreaSearch.setWrapStyleWord(true);
		textAreaSearch.setLineWrap(true);
		sl_panelRecherche.putConstraint(SpringLayout.NORTH, textAreaSearch, 2, SpringLayout.SOUTH, lblSaison);
		sl_panelRecherche.putConstraint(SpringLayout.WEST, textAreaSearch, 2, SpringLayout.WEST, panelRecherche);
		sl_panelRecherche.putConstraint(SpringLayout.SOUTH, textAreaSearch, -2, SpringLayout.SOUTH, panelRecherche);
		sl_panelRecherche.putConstraint(SpringLayout.EAST, textAreaSearch, -2, SpringLayout.EAST, panelRecherche);
		panelRecherche.add(textAreaSearch);
		
		lblSrie = new JLabel("S\u00E9rie :");
		sl_panelRecherche.putConstraint(SpringLayout.NORTH, lblSrie, 2, SpringLayout.NORTH, panelRecherche);
		sl_panelRecherche.putConstraint(SpringLayout.WEST, lblSrie, 2, SpringLayout.WEST, panelRecherche);
		sl_panelRecherche.putConstraint(SpringLayout.EAST, lblSrie, -2, SpringLayout.EAST, panelRecherche);
		sl_panelRecherche.putConstraint(SpringLayout.NORTH, lblSaison, 2, SpringLayout.SOUTH, lblSrie);
		panelRecherche.add(lblSrie);

	}
	
	public int searchNumber() {
		return nbrSearch++;
	}
	
	public void setSerie(String title) {
		this.lblSrie.setText("SŽrie : " + title);
	}
	
	public void setSaison(String string) {
		this.lblSaison.setText("Saison " + string);
	}
	
	public void setSearch(String string) {
		this.textAreaSearch.setText(string);
	}
	
	public void addEpidose(String episode, double similarity) {
		this.panelResultat.add(new JLabel(episode));
		JProgressBar bar = new JProgressBar();
		int val = (int) (100 * similarity);
		bar.setValue(val);
		this.panelResultat.add(bar);
	}
	
}
