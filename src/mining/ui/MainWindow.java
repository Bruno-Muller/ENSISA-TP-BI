package mining.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;
import mining.serie.Serie;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private View view;
	private Serie model;

	
	public MainWindow() {
		super("BI TP Mining - Bruno Muller");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.model = new Serie();
		this.view = new View(this.model);
		this.view.setPreferredSize(new Dimension(850,600));
		this.getContentPane().add(this.view, java.awt.BorderLayout.CENTER);
	}
	
	public static void main(String[] args)
	{
		
		 try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		MainWindow self = new MainWindow();
		self.pack();
		self.setVisible(true);
	}

}
