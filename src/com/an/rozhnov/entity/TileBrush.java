package com.an.rozhnov.entity;

import java.awt.image.BufferedImage;

public class TileBrush {
	
	protected BufferedImage sprite;
	protected String type;
	protected boolean isSecondLayer;
	
	
	public TileBrush (BufferedImage sprite, String type, boolean secondLayer) {
		this.type = type;
		this.sprite = sprite;
		this.isSecondLayer = secondLayer;
	}
	
	public Tile createTile (int x, int y) {
		return new Tile (x, y, sprite, type);
	}

	public BufferedImage getSprite () {
		return sprite;
	}

	public String getType() {
		return type;
	}

	public boolean isSecondLayer() {
		return isSecondLayer;
	}
}
