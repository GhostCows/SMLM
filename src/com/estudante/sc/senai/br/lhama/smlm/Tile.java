package com.estudante.sc.senai.br.lhama.smlm;

import java.awt.*;

/**
 * Created by Marcelo Vogt on 12/10/2017.
 */
public class Tile {

	private TileMap tileMap;
	private int x;
	private int y;
	private int px;
	private int py;
	private int size;
	private int index;
	private double friction;
	private Collision collision;

	public Tile(TileMap tileMap, int x, int y) {
		this.tileMap = tileMap;
		this.x = x;
		this.y = y;
		size = tileMap.getTileSize();
		index = y * tileMap.getCols() + x;
	}

	public void setPos(int px, int py) {
		this.px = px;
		this.py = py;
	}

	public void draw(Graphics2D g2d) {
		int ts = size;
		int s = tileMap.getSize();

		int dx1 = px * s;
		int dy1 = py * s;
		int dx2 = (px + 1) * s;
		int dy2 = (py + 1) * s;

		int sx1 = x * ts;
		int sy1 = y * ts;
		int sx2 = (x + 1) * ts;
		int sy2 = (y + 1) * ts;

		g2d.drawImage(
				tileMap.getImage(),
				dx1, dy1,
				dx2, dy2,
				sx1, sy1,
				sx2, sy2,
				null
		);
	}

	@Override
	public String toString() {
		return "Tile{" +
				"index=" + index +
				'}';
	}
}
