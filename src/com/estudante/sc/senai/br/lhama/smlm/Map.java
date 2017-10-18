package com.estudante.sc.senai.br.lhama.smlm;

import java.awt.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marcelo Vogt on 12/10/2017.
 */
public class Map {

	private ZTileMap tileMap;
	private ArrayList<Sprite> sprites;
	private int tileSize;
	private ArrayList<ArrayList<ZTile>> tiles;
	private int state = 0;

	public Map(String levelName, String tilemapName, int tileSize) {
		try {
			tileMap = new ZTileMap(tilemapName, tileSize);
			importLevel(levelName);
			this.tileSize = tileSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void importLevel(String levelName) throws Exception {

		loadTiles(levelName.concat(".csv"));

		loadLevelSprites(levelName.concat("sprites.csv"));
	}

	private void loadLevelSprites(String path) throws InvalidPathException {
		sprites = new ArrayList<>();
		String content = ZFile.readFile(path);

		ArrayList<String> rows = new ArrayList<>(
				Arrays.asList(content.split("\r\n"))
		);

		for (int i = 0; i < rows.size(); i++) {
			String row = rows.get(i);

			ArrayList<String> ts = new ArrayList<>(
					Arrays.asList(row.split(","))
			);

			for (int j = 0; j < ts.size(); j++) {
				String t = ts.get(j);

				if(!t.equals("-1")) {
//					sprites.add(CARREGAR O SPRITE);
				}
			}
		}
	}

	private void loadTiles(String path) throws InvalidPathException {
		tiles = new ArrayList<>();

		String content = ZFile.readFile(path);

		ArrayList<String> rows = new ArrayList<>(
				Arrays.asList(content.split("\r\n"))
		);

		for (int i = 0; i < rows.size(); i++) {
			String row = rows.get(i);

			ArrayList<String> ts = new ArrayList<>(
					Arrays.asList(row.split(","))
			);

			tiles.add(i, new ArrayList<>());

			for (int j = 0; j < ts.size(); j++) {
				String t = ts.get(j);

				ZTile tile = null;
				int index = Integer.parseInt(t);

				if(index != -1) {
					tile = tileMap.get(index);
				}

				tiles.get(i).add(j, tile);
			}
		}
	}

	public void draw(Graphics2D g2d) {
		for (int i = 0; i < tiles.size(); i++) {
			ArrayList<ZTile> rows = tiles.get(i);
			for (int j = 0; j < rows.size(); j++) {
				ZTile tile = rows.get(j);
				if (tile != null) {
					tile.draw(g2d, j, i, tileSize);
				}
			}
		}
	}
}
