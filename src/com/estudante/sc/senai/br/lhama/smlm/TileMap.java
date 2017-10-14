package com.estudante.sc.senai.br.lhama.smlm;

import org.json.simple.JSONArray;

public class TileMap extends ZImage {

	private JSONArray array;
	private int tileSize;
	private int cols;
	private int rows;

	public TileMap(String path, int tileSize) throws Exception {
		super(path.concat(".png"));
		array = (JSONArray) ZFile.readJSON(path.concat(".json"));
		this.tileSize = tileSize;
		if (getWidth() % tileSize != 0 || getHeight() % tileSize != 0)
			throw new Exception("Invalid image or tile size");
		cols = getWidth() / tileSize;
		rows = getHeight() / tileSize;
	}

	public Tile get(int x, int y) {
		return new Tile(this, x, y);
	}

	public Tile get(int index) {
		if (index == -1) {
			return null;
		}
		return get(
				Math.floorMod(index, cols),
				Math.floorDiv(index, cols)
		);
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public int getTileSize() {
		return tileSize;
	}

	public int getSize() {
		return tileSize;
	}
}
