package com.estudante.sc.senai.br.lhama.smlm;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Marcelo Vogt on 04/08/2017.
 */
public class Map {

	private int tileSize;
	private int width;
	private int height;
	private TileMap tMap;
	private ArrayList<ArrayList<Tile>> map;

	public Map(TileMap tMap, int width, int height) {

		this.tMap = tMap;
		this.tileSize = tMap.getTileSize();
		this.width = width;
		this.height = height;

	}

	public Tile get(int x, int y) {
		return map.get(x).get(y);
	}

	public void set(int x, int y, Tile t) {
		map.get(x).set(y, t);
	}

	public void setLevel(String path) throws Exception {

		InputStream is = getClass().getClassLoader().getResourceAsStream(path);

		Scanner s = new Scanner(is).useDelimiter("\\A");
		String json = s.hasNext() ? s.next() : "";

		System.out.println(json);

//		int[][][] ints = new int[][][]{{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}}, {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}}, {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}}, {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}}, {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}}, {{1, 1}, {0, 0}, {0, 1}, {1, 0}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}}};

		JSONArray array = (JSONArray) JSONValue.parse(json);


		map = new ArrayList<>(height);

		for (int i = 0; i < array.size(); i++) {
			map.add(new ArrayList<>(width));
			JSONArray row = (JSONArray) array.get(i);
			for (int j = 0; j < row.size(); j++) {
				JSONArray col = (JSONArray) row.get(j);
				if (((Long) col.get(0)) != -1) {
					map.get(i).add(tMap.getTile(((Long) col.get(0)).intValue(), ((Long) col.get(1)).intValue()));
				} else {
					map.get(i).add(null);
				}
			}
		}

	}

	public void draw(Graphics2D g2d) {

		for (int i = 0; i < map.size(); i++) {
			ArrayList<Tile> row = map.get(i);
			for (int j = 0; j < row.size(); j++) {
				Tile col = row.get(j);
				if (col != null) {
					col.draw(g2d, j * tileSize, i * tileSize);
				}
			}
		}

	}

}
