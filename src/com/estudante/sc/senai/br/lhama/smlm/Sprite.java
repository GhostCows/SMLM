package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Fps;

import java.awt.*;

public class Sprite extends ZImage {
	protected int tileSize = SMLM.TILE_SIZE;
	protected static final double GRAVITY = SMLM.TILE_SIZE * 10;

	protected double x;
	protected double y;
	protected double xv;
	protected double yv;
	protected int w;
	protected int h;
	protected Fps fps;

	private boolean onGround;

	private Map map;

	public Sprite(Map map, Fps fps, String path, double x, double y) {
		super(path);
		this.x = x;
		this.y = y;
		this.w = getWidth();
		this.h = getHeight();
		this.fps = fps;
		this.map = map;
	}

	public void run() {
		yv += GRAVITY / 30;
		x += xv / 30;
		y += yv / 30;

		if (onGround()) {
			yv = 0;
			y = Math.floor(y / tileSize) * tileSize;
		}

	}

	public boolean onGround() {
		int minX = (int) Math.floor(x / tileSize);
		int maxX = (int) Math.floor((x + w) / tileSize);

		int minY = (int) Math.floor(y / tileSize);
		int maxY = (int) Math.floor((y + h) / tileSize);

		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {

				if (map.get(j, i) != null) {
					if (x + w > i * tileSize && x < (i + 1) * tileSize) {
						if (y + h >= j * tileSize && y + h <= j * tileSize + yv) {

							onGround = true;

						}
					}
				}
			}
		}

		return onGround;
	}

	public int colliding(Sprite spr) {

		int num = 0;

		if (x + w >= spr.x) {
			num += 1;
		} else if (spr.x + spr.w >= x) {
			num += 2;
		} else if (y + h >= spr.y) {
			num += 4;
		} else if (spr.y + spr.h >= y) {
			num += 8;
		}

		return num;

	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) Math.floor(x), (int) Math.floor(y), null);
	}
}
