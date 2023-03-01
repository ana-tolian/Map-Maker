package com.an.rozhnov.windows;

import com.an.rozhnov.Init;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class SetupImageDialog extends JDialog{

	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel titleLabel;
	private JLabel pixelLabel1;
	private JLabel pixelLabel2;
	
	private JTextField widthField;
	private JTextField heightField;
	
	private JButton okayButton;
	private JButton cancelButton;
	
	private JPanel titlePanel;
	private JPanel setupPanel;
	private JPanel buttonPanel;
	
	public static int width;
	public static int height;
	
	private static JFrame frame = new JFrame ();
	
	
	public SetupImageDialog () {
		super (frame, "New map", true);
		setSize(250, 300);
		setLocationRelativeTo(null);
		setResizable(false);
	
		// Labels
		widthLabel = new JLabel ("Width: ");
	  	widthLabel.setAlignmentX(LEFT_ALIGNMENT);
		widthLabel.setPreferredSize(new Dimension (50, 20));
	  
		heightLabel = new JLabel ("Height: ");
		heightLabel.setAlignmentX(LEFT_ALIGNMENT);
		heightLabel.setPreferredSize(new Dimension (50, 20));
	  
		titleLabel = new JLabel ("Set width and height for the map");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
	  
		pixelLabel1 = new JLabel ("tiles");
		pixelLabel2 = new JLabel ("tiles");
	  
		// Fields  
		widthField = new JTextField (10);
		heightField = new JTextField (10);
	
	
		// Buttons
		okayButton = new JButton ("Ok");
		okayButton.addActionListener(e -> {
			if (!widthField.getText().equals("")  &&  !heightField.getText().equals("")) {
				width = Integer.parseInt(widthField.getText());
				height = Integer.parseInt(heightField.getText());

				if (width != 0 && height != 0) {
					Init.setBuildPanel(width, height);
					setVisible(false);
				}

			} else JOptionPane.showMessageDialog(SetupImageDialog.this,
						"Введите ненулевые значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
		});

		cancelButton = new JButton ("Cancel");
		cancelButton.addActionListener(e -> setVisible(false));
	
		// Panels
		titlePanel = new JPanel ();
		titlePanel.add("Center", titleLabel);
	 
		setupPanel = new JPanel (new FlowLayout());
		setupPanel.setBorder(BorderFactory.createEtchedBorder());
		setupPanel.add(widthLabel);
		setupPanel.add(widthField);
		setupPanel.add(pixelLabel1);
		setupPanel.add(heightLabel);
		setupPanel.add(heightField);	
		setupPanel.add(pixelLabel2);
	 
		buttonPanel = new JPanel (new FlowLayout());
		buttonPanel.add(okayButton);
		buttonPanel.add(cancelButton);
	
		add("North", titlePanel);
		add("Center", setupPanel);
		add("South", buttonPanel);
		
	}
	
}
