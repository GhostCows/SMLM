package com.estudante.sc.senai.br.lhama.smlm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Marcelo Vogt on 04/08/2017.
 */
public class Map {

	private int tileSize;
	private int width;
	private int height;
	private TileMap tMap;
	private ArrayList<HashMap<String, Long>> cols;
	private ArrayList<ArrayList<Tile>> map;
	private JSONObject obj;

	public Map(TileMap tMap, int width, int height) {

		this.tMap = tMap;
		this.tileSize = tMap.getTileSize();
		this.width = width;
		this.height = height;

	}

	public Tile get(int x, int y) {
		return map.get(y).get(x);
	}

	public void set(int x, int y, Tile t) {
		map.get(y).set(x, t);
	}

	public void setLevel(int levelNumber, int width, int height) throws Exception {

		String level = ZFile.readFile("levels/level" + levelNumber + ".json");
		String cols = ZFile.readFile("levels/colision" + levelNumber + ".json");

		JSONArray array1 = (JSONArray) JSONValue.parse(level);
		JSONArray array2 = (JSONArray) JSONValue.parse(cols);

		map = new ArrayList<>(width);
		this.cols = new ArrayList<>(array2.size());

		for (int i = 0; i < height; i++) {
			map.add(new ArrayList<>(width));
			for (int j = 0; j < width; j++) {
				JSONArray row = (JSONArray) array1.get(i);
				try {
					JSONArray col = (JSONArray) row.get(j);
					map.get(i).add(tMap.getTile(((Long) col.get(0)).intValue(), ((Long) col.get(1)).intValue()));
				} catch (Exception e) {
					map.get(i).add(null);
				}
			}
		}

		for (Object object : array2) {
			try {
				JSONObject obj = (JSONObject) object;
				HashMap<String, Long> ob = new HashMap<>();
				for (Object o : obj.keySet()) {
					if(o instanceof String) {
						ob.put((String) o, (Long) obj.get(o));
					}
				}
				this.cols.add(ob);
			} catch (Exception e) {
				e.printStackTrace();
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
