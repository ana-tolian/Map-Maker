package com.an.rozhnov.draw;

import com.an.rozhnov.entity.Map;
import com.an.rozhnov.entity.TileBrush;
import com.an.rozhnov.file.load.SpriteLoader;
import com.an.rozhnov.utils.Constants;
import com.an.rozhnov.draw.sub_panels.*;

import java.awt.*;

import javax.swing.*;


public class MapBuildingPanel extends JPanel implements Runnable {

	private UpperPanel upperPanel;
	private BottomPanel bottomPanel;
	private CreationPanel creationPanel;
	private DrawingModePanel drawingModePanel;

	public static TileBrush brush;
	public static SpriteLoader loader;
	public static Map map;

	public static String path;
	public static boolean brushMode = false;
	public static boolean dragMode = true;
	public static boolean eraseMode = false;


	public MapBuildingPanel(int width, int height) {
		this.path = null;

		loader = new SpriteLoader(Constants.SPRITE_PATH);
		creationPanel = new CreationPanel(width, height);
		init();
	}
	
	public MapBuildingPanel(String levelPath) {
		this.path = levelPath;

		loader = new SpriteLoader(Constants.SPRITE_PATH);
		creationPanel = new CreationPanel(levelPath);
		init();
	}

	private void init () {
		upperPanel = new UpperPanel();
		bottomPanel = new BottomPanel();
		drawingModePanel = new DrawingModePanel();

		this.setFocusable(false);
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		this.add(upperPanel, BorderLayout.NORTH);
		this.add(creationPanel, BorderLayout.CENTER);
		this.add(drawingModePanel, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);

		start();
	}

	public void setBrushMode () {
		brushMode = true;
		dragMode = false;
		eraseMode = false;
	}

	public void setDragMode () {
		brushMode = false;
		dragMode = true;
		eraseMode = false;
	}

	public void setEraseMode () {
		brushMode = false;
		dragMode = false;
		eraseMode = true;
	}

	public DrawingModePanel getDrawingModePanel () {
		return drawingModePanel;
	}

	private void start () {
		Thread t = new Thread (this);
		t.start();
	}
	
	public void run () {
		while (true) {
			try {
				Thread.sleep(50);
				creationPanel.repaint();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
