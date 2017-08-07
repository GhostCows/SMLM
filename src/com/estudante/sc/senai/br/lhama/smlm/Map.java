package com.estudante.sc.senai.br.lhama.smlm;

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

		String json = ZFile.readFile(path);

		JSONArray array = (JSONArray) JSONValue.parse(json);

		map = new ArrayList<>(height);

		for (int i = 0; i < array.size(); i++) {
			JSONArray row = (JSONArray) array.get(i);
			map.add(new ArrayList<>(width));
			for (int j = 0; j < row.size(); j++) {
				JSONArray col = (JSONArray) row.get(j);
				try {
					map.get(i).add(tMap.getTile(((Long) col.get(0)).intValue(), ((Long) col.get(1)).intValue()));
				} catch (Exception e) {
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
					col.draw(g2d, i * tileSize, j * tileSize);
				}
			}
		}

	}

}
