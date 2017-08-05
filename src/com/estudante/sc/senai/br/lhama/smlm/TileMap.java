package com.estudante.sc.senai.br.lhama.smlm;

import java.awt.*;
import java.net.URI;
import java.net.URL;

public class TileMap extends ZImage {

	private int tileSize;

	public TileMap(int tileSize, String path) throws Exception {
		super(path);
		setTileSize(tileSize);
	}

	public TileMap(int tileSize, URI path) throws Exception {
		super(path);
		setTileSize(tileSize);
	}

	public TileMap(int tileSize, URL path) throws Exception {
		super(path);
		setTileSize(tileSize);
	}

	public TileMap(int tileSize, Image img) throws Exception {
		super(img);
		setTileSize(tileSize);
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) throws Exception {
		if(getWidth() % tileSize == 0 && getHeight() % tileSize == 0) {
			this.tileSize = tileSize;
		} else {
			throw new Exception("Invalid tile size");
		}
	}

	public Tile getTile(int x, int y) {
		int x1 = tileSize * x;
		int y1 = tileSize * y;
		return new Tile(crop(x1, y1, x1 + tileSize, y1 + tileSize));
	}


}
