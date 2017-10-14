package com.estudante.sc.senai.br.lhama.smlm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marcelo Vogt on 12/10/2017.
 */
public class Map {

	private ArrayList<ArrayList<Tile>> tiles;
	private TileMap tileMap;
	private Sprite[] sprites;

	public Map(String levelPath, String tilemapPath, int tileSize) {
		try {
			tileMap = new TileMap(tilemapPath, tileSize);
			importLevel(levelPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void importLevel(String path) throws Exception {
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

				Tile tile = null;
				try {
					int index = Integer.parseInt(t);
					tile = tileMap.get(index);

					if(tile != null) {
						tile.setPos(j, i);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				tiles.get(i).add(j, tile);
			}
		}
	}

	public void draw(Graphics2D g2d) {
		for (ArrayList<Tile> rows : tiles) {
			for (Tile tile : rows) {
				if(tile != null) {
					tile.draw(g2d);
				}
			}
		}
	}

	public Tile get(int x, int y) {
		return tileMap.get(x, y);
	}
}
