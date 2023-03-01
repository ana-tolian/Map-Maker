package com.an.rozhnov.entity;

import com.an.rozhnov.utils.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Tile {

	protected int x;
	protected int y;
	protected String type;
	protected BufferedImage sprite;

	public Tile (int x, int y, BufferedImage sprite, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.sprite = sprite;
	}


	public void draw (Graphics g) {
		if (sprite != null)
			g.drawImage(sprite, x * Constants.SPR_WIDTH, y * Constants.SPR_HEIGHT, Constants.SPR_WIDTH, Constants.SPR_HEIGHT, null);
	}
	
	public void draw (Graphics2D g2) {
		if (sprite != null)
			g2.drawImage(sprite, x * Constants.SPR_WIDTH, y * Constants.SPR_HEIGHT, Constants.SPR_WIDTH, Constants.SPR_HEIGHT, null);
	}

	public boolean isOnTile (int x, int y) {
		return x >= this.x * Constants.SPR_WIDTH && y >= this.y * Constants.SPR_HEIGHT &&
				x <= this.x * Constants.SPR_WIDTH + Constants.SPR_WIDTH && y <= this.y * Constants.SPR_HEIGHT + Constants.SPR_HEIGHT;
	}
	
	public BufferedImage getSprite () {
		return sprite;
	}
	
	public String getType () {
		return type;
	}

	public String getSuperType () {
		return type.substring(0, type.indexOf("_"));
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}

	public String toString () {
		return "x=" + x + " y=" + y + " type=" + type + " movable=true";
	}

	@Override
	public boolean equals (Object o) {
		if (o == null || !(o instanceof Tile))
			return false;

		Tile t = (Tile) o;

		if (this.getSuperType().equals(t.getSuperType()))
			return true;
		return false;
	}

}
