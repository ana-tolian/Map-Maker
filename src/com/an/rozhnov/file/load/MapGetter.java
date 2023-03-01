package com.an.rozhnov.file.load;

import com.an.rozhnov.entity.Map;
import com.an.rozhnov.entity.Tile;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MapGetter {

	private Map map;

	private final ArrayList<String> strs;
	private Tile[] tiles;

	private final HashMap<String, BufferedImage> spriteSet;
	
	
	public MapGetter(String levelPath, SpriteLoader loader) throws FileNotFoundException {
		strs = new ArrayList<> ();
		Scanner in = new Scanner(new FileReader(levelPath));
		
		spriteSet = loader.getSpriteSet();
		
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			strs.add(line);
		}

		map = loadMap();
	}

	private Map loadMap () {
		String[] s = strs.get(0).split(" ");

		int width = Integer.parseInt(s[0]);
		int height = Integer.parseInt(s[1]);

		tiles = new Tile [2 * width * height];
		loadTiles();

		map = new Map (tiles, width, height);
		return map;
	}

	public Map getMap () {
		return  map;
	}
	
	private void loadTiles() {
			int x;
			int y;
			int j = 0;
			String type = "";


		for (int i = 1; i <= strs.size() - 1; i++) {
			String[] data = strs.get(i).split(" ");
			
			x = Integer.parseInt(data[0].substring(data[0].indexOf("=") + 1));
			y = Integer.parseInt(data[1].substring(data[1].indexOf("=") + 1));
			type = data[2].substring(data[2].indexOf("=") + 1);
//			secondLayer = Boolean.parseBoolean(data[3].substring(data[3].indexOf("=") + 1));

			tiles[j++] = new Tile (x, y, spriteSet.get(type), type);
		}
	}

}
