package es.jysa.priceCalculatorNG.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame {
	
	private JFrame frame;
	
	public MainFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		ImageIcon icon = new ImageIcon("");
		frame.setIconImage(icon.getImage());
		frame.setTitle("Prestashop Price Calculator NG");
		frame.setBounds(100, 100, 1024 , 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setVisible(true);
	}
	
}
