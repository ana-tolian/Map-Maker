package com.an.rozhnov.entity;

import com.an.rozhnov.draw.MapBuildingPanel;
import com.an.rozhnov.utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Map {
	
	private final int width;
	private final int height;

	private boolean isMapChanged = true;

	private Tile[] tiles;

	
	public Map (Tile[] tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		initialiseMap();
	}

	public void initialiseMap () {
		tiles = new Tile [2 * width * height];

		BufferedImage defaultSprite = MapBuildingPanel.loader.getSpriteSet().get(Constants.DEFAULT_SPRITE);

		int x = 0;
		int y = 0;

		for (int i = 0; i < tiles.length; i++) {
			if (i >= getMapLength() / 2)
				tiles[i] = new Tile(x, y, null, "");
			else
				tiles[i] = new Tile(x, y, defaultSprite, Constants.DEFAULT_SPRITE);

			x = (x + 1) % width;
			if (x == 0) y++;
			if (y >= height) y %= height;
		}
	}

	public void draw (Graphics2D g2) {
		for (int i = 0; i < getMapLength(); i++) {
			if (tiles[i] != null)
				tiles[i].draw(g2);
		}
	}

	public void setTile (int x, int y) {
		x /= Constants.SPR_WIDTH;
		y /= Constants.SPR_HEIGHT;

		if (x < width && x >= 0 && y < height && y >= 0) {
			int i = twoDimCoordsIntoOneDimWithSecondLayer(x, y);

			if (!MapBuildingPanel.brush.isSecondLayer()) {
				checkEveryTile(collectAllBorderingTilesWithSameSuperType(MapBuildingPanel.brush.createTile(tiles[i].getX(), tiles[i].getY()), i));
			} else {
				tiles[i] = MapBuildingPanel.brush.createTile(tiles[i].getX(), tiles[i].getY());
			}

			isMapChanged = true;
		}
	}

	private ArrayList<Tile> collectAllBorderingTilesWithSameSuperType (Tile tile, int j) {

		boolean[]  markedTiles = new boolean[getMapLength() / 2];

		ArrayList<Tile> borderingTiles = new ArrayList<>();
		addAll(borderingTiles, getNeighbouringTiles(tile));

		setTileAndDecorate(tile, j);

		if (borderingTiles.size() == 0)
			return null;

		/* borderingTiles.size() / 8 is needed to cut excess checks */
		for (int i = 0; i < borderingTiles.size() / 8; i++) {
			markedTiles[twoDimCoordsIntoOneDim(borderingTiles.get(i))] = true;
			addAll(borderingTiles, getNeighbouringTiles(borderingTiles.get(i)), markedTiles);
		}

		return borderingTiles;
	}

	private void checkEveryTile (ArrayList<Tile> t) {
		if (t == null)
			return;

		for (Tile temp : t)
			setTileAndDecorate(temp, twoDimCoordsIntoOneDim(temp));
	}

	private void setTileAndDecorate (Tile tile, int i) {

		Tile [] neighbouringTiles = getNeighbouringTiles(tile);

		String type = tile.getSuperType();

		if (!type.equals("grass")) {
			if (neighbouringTiles[0] != null && neighbouringTiles[1] != null && neighbouringTiles[2] != null && neighbouringTiles[3] != null) {
				type += "_0.1";

			} else if (neighbouringTiles[1] != null && neighbouringTiles[2] != null && neighbouringTiles[3] != null) {
				type += "_1.1";

			} else if (neighbouringTiles[0] != null && neighbouringTiles[2]  != null && neighbouringTiles[3] != null) {
				type += "_1.2";

			} else if (neighbouringTiles[0] != null && neighbouringTiles[1] != null && neighbouringTiles[3] != null) {
				type += "_1.3";

			} else if (neighbouringTiles[0] != null && neighbouringTiles[1] != null && neighbouringTiles[2] != null) {
				type += "_1.4";

			} else if (neighbouringTiles[0] != null && neighbouringTiles[1] != null) {
				type += "_2.1";

			} else if (neighbouringTiles[1] != null && neighbouringTiles[2] != null) {
				type += "_2.2";

			} else if (neighbouringTiles[2] != null && neighbouringTiles[3] != null) {
				type += "_2.3";

			} else if (neighbouringTiles[3] != null && neighbouringTiles[0] != null) {
				type += "_2.4";

			} else if (neighbouringTiles[0] != null && neighbouringTiles[2] != null) {
				type += "_2.5";

			} else if (neighbouringTiles[3] != null && neighbouringTiles[1] != null) {
				type += "_2.6";

			} else if (neighbouringTiles[0] != null) {
				type += "_3.1";

			} else if (neighbouringTiles[1] != null) {
				type += "_3.2";

			} else if (neighbouringTiles[2] != null) {
				type += "_3.3";

			} else if (neighbouringTiles[3] != null) {
				type += "_3.4";

			} else
				type += "_4.1";
		} else {
			type += "_0.1";
		}

		tiles[i] = new Tile(tile.getX(), tile.getY(), MapBuildingPanel.loader.getSpriteSet().get(type), type);

	}

	private Tile[] getNeighbouringTiles (Tile tile) {
		Tile [] neighbouringTiles = new Tile [4];

		if (!tile.getSuperType().equals("water"))
			tile = new Tile(tile.getX(), tile.getY(), tile.getSprite(), tiles[twoDimCoordsIntoOneDim(tile)].getType());

		int x = tile.getX();
		int y = tile.getY();

		int top_t = getTopTileIndex(x, y);
		int left_t = getLeftTileIndex(x, y);
		int bottom_t = getBottomTileIndex(x, y);
		int right_t = getRightTileIndex(x, y);

		if (top_t >= 0)
			neighbouringTiles[0] = tiles[top_t];

		if (right_t < getMapLength() / 2)
			neighbouringTiles[1] = tiles[right_t];

		if (bottom_t < getMapLength() / 2)
			neighbouringTiles[2] = tiles[bottom_t];

		if (left_t >= 0)
			neighbouringTiles[3] = tiles[left_t];

		removeTilesThatNotSameType(neighbouringTiles, tile);

		return neighbouringTiles;
	}

	private void removeTilesThatNotSameType (Tile[] neighbours, Tile tile) {
		for (int j = 0; j < 4; j++)
			if (neighbours[j] != null && !neighbours[j].equals(tile))
				neighbours[j] = null;
	}

	private int getTopTileIndex (int x, int y) {
		return twoDimCoordsIntoOneDim(x, y - 1);
	}

	private int getBottomTileIndex (int x, int y) {
		return twoDimCoordsIntoOneDim(x, y + 1);
	}

	private int getLeftTileIndex (int x, int y) {
		return twoDimCoordsIntoOneDim(x - 1, y);
	}

	private int getRightTileIndex (int x, int y) {
		return twoDimCoordsIntoOneDim(x + 1, y);
	}

	private int twoDimCoordsIntoOneDim (Tile tile) {
		return (tile.getX() + tile.getY() * width);
	}

	private int twoDimCoordsIntoOneDim (int x, int y) {
		return (x + y * width);
	}

	private int twoDimCoordsIntoOneDimWithSecondLayer (int x, int y) {
		int a = 0;

		if ((MapBuildingPanel.brush.isSecondLayer()))
			a = getMapLength() / 2;

		return a + (x + y * width);
	}

	private void addAll (ArrayList<Tile> tile, Tile[] t) {
		for (Tile temp : t)
			if (temp != null)
				tile.add(temp);
	}

	private void addAll (ArrayList<Tile> tile, Tile[] t, boolean[] markedTiles) {
		for (Tile temp : t)
			if (temp != null && !markedTiles[twoDimCoordsIntoOneDim(temp)])
				tile.add(temp);
	}
	
	public int getMapLength () {
		return tiles.length;
	}

	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void fixateChanges () {
		isMapChanged = false;
	}

	public boolean isMapChanged() {
		return isMapChanged;
	}

	@Override
	public String toString () {
		return width + " " + height;
	}

}
