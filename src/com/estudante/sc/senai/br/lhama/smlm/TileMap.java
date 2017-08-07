package com.estudante.sc.senai.br.lhama.smlm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class TileMap extends ZImage {

	private int tileSize;

	private JSONArray array;

	public TileMap(int tileSize, String path, String frictionPath) throws Exception {
		super(path);
		setTileSize(tileSize);
		array = (JSONArray) JSONValue.parse(ZFile.readFile(frictionPath));
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

		return new Tile(crop(x1, y1, x1 + tileSize, y1 + tileSize), (JSONObject) ((JSONArray) array.get(x)).get(y));
	}


}
