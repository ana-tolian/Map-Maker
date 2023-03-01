package com.an.rozhnov.file.load;

import com.an.rozhnov.utils.Constants;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class SpriteLoader {
	
	private String directory;
	private HashMap<String, BufferedImage> spriteSet;
	private HashMap<String, Boolean> overlaySprites;
	
	
	public SpriteLoader(String path) {
		directory = path;
		
		spriteSet = new HashMap<>();
		overlaySprites = new HashMap<>();
		
		loadImages();
		if (directory.equals(Constants.SPRITE_PATH))
			loadOverlaySettings();
	}
	
	
	private void loadImages () {
		File folder = new File (directory);
		File [] files = folder.listFiles();
		
		try {
			for (int i = 0; i < files.length; i++)
				spriteSet.put(files[i].getName().substring(0, files[i].getName().lastIndexOf(".")), ImageIO.read(files[i]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadOverlaySettings () {
		try (Scanner in = new Scanner(new File(Constants.SETTINGS_PATH + "overlay.txt"))) {
			while (in.hasNextLine()) {
				String overlaySpriteName = in.nextLine();

				for (String spriteName : spriteSet.keySet()) {
					if (spriteName.contains(overlaySpriteName))
						overlaySprites.put(spriteName, true);
				}
			}

			for (String spriteName : spriteSet.keySet())
				if (!overlaySprites.containsKey(spriteName))
					overlaySprites.put(spriteName, false);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, BufferedImage> getSpriteSet () {
		return spriteSet;
	}

	public HashMap<String, Boolean> getOverlaySprites() {
		return overlaySprites;
	}
}