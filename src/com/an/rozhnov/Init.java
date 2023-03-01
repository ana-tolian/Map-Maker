package com.an.rozhnov;

import com.an.rozhnov.windows.MainMenu;
import com.an.rozhnov.draw.MapBuildingPanel;

import javax.swing.*;


public class Init {

	private static MainMenu menu;
	public static MapBuildingPanel mapBuildingPanel;

	private static JFrame frame;
	
	public Init () {
		createUI();
	}

	private static void createUI () {
		frame = new JFrame ("Map maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 640);
		
		menu = new MainMenu ();

		frame.add(menu);
		
		frame.setVisible(true);
	}

	public static void setBuildPanel (int width, int height) {
		if (mapBuildingPanel != null) {
			mapBuildingPanel.setVisible(false);
			frame.remove(mapBuildingPanel);
		}

		mapBuildingPanel = new MapBuildingPanel (width, height);
		frame.add(mapBuildingPanel);
		mapBuildingPanel.setVisible(true);
		menu.setVisible(false);
	}
	
	public static void setBuildPanel (String path) {
		if (mapBuildingPanel != null) {
			mapBuildingPanel.setVisible(false);
			frame.remove(mapBuildingPanel);
		}

		mapBuildingPanel = new MapBuildingPanel (path);
		frame.add(mapBuildingPanel);
		mapBuildingPanel.setVisible(true);
		menu.setVisible(false);
	}
	
	public static void setMainPanel () {
		frame.add(menu);
		menu.setVisible(true);
		mapBuildingPanel.setVisible(false);
		frame.remove(mapBuildingPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				new Init();
			}
		});

	}

}
