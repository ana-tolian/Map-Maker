package com.an.rozhnov.windows;

import com.an.rozhnov.Init;
import com.an.rozhnov.file.save.FileChooserDialog;
import com.an.rozhnov.ui_elements.MenuButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MainMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JPanel nullPanel;

	private MenuButton[] buttons = {new MenuButton("Create"), new MenuButton("Load"),
								new MenuButton("Settings"), new MenuButton("Exit")};

	public MainMenu() {
		setBackground(Color.GRAY);
		setFocusable(false);
		setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
	
		nullPanel = new JPanel ();
			nullPanel.setBackground(Color.GRAY);
			nullPanel.setPreferredSize(new Dimension (50, 50));
			nullPanel.setMaximumSize(new Dimension (50, 50));
			nullPanel.setMinimumSize(new Dimension (50, 50));
	
		panel = new JPanel (new FlowLayout(FlowLayout.CENTER));
			panel.setBackground(Color.GRAY);
			panel.setPreferredSize(new Dimension (200, 1000));
			panel.setMaximumSize(new Dimension (200, 1000 ));
			panel.setMinimumSize(new Dimension (200, 1000 ));
	
	
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].addActionListener(this);
				panel.add(buttons[i]);
			}
	
			add("North", nullPanel);
			add("Center", panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
	
		if (button.getActionCommand().equals("Create")) {
			SetupImageDialog setup = new SetupImageDialog ();
			setup.setVisible(true);
			
		} else if (button.getActionCommand().equals("Load")) {
			FileChooserDialog f = new FileChooserDialog ("Open");

			if (f.getOperationResult() == JFileChooser.APPROVE_OPTION && f.getFile() != null)
				Init.setBuildPanel(FileChooserDialog.path);
			
		} else if (button.getActionCommand().equals("Settings")) {
				
		} else if (button.getActionCommand().equals("Exit"))
			System.exit(0);
	}

}

