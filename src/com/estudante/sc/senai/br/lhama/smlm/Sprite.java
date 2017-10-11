package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Fps;

import java.awt.*;

public class Sprite extends ZImage {
	protected int tileSize = SMLM.TILE_SIZE;
	protected static final double GRAVITY = SMLM.TILE_SIZE * 10;

	protected Collision collision;

	protected double x;
	protected double y;
	protected double xv;
	protected double yv;
	protected int w;
	protected int h;
	protected Fps fps;

	protected double maxFall;

	protected double maxSpeed;

	private Map map;

	public Sprite(Map map, Fps fps, String path, double maxSpeed, double x, double y) {
		super(path);
		this.x = x;
		this.y = y;
		this.w = getWidth();
		this.h = getHeight();
		this.fps = fps;
		this.map = map;
		this.maxSpeed = Math.abs(maxSpeed);
	}

	public void run() {

		collide();

		xv = Math.signum(xv) * Math.min(Math.abs(xv), maxSpeed);
		yv += GRAVITY / Math.pow(fps.getTargetFps(), 2);
		yv = Math.min(Math.abs(yv), 100);

	}

	public void collide() {
		x += xv;

		colliding();

		if(collision.left()) {
			xv = 0;
			x = Math.floor(x / tileSize) * tileSize;
		}

		if(collision.right()) {
			xv = 0;
			x = Math.ceil(x / tileSize) * tileSize;
		}

		y += yv;

		colliding();

		if(collision.top()) {
			yv = 0;
			y = Math.ceil(y / tileSize) * tileSize;
		}

		if (collision.bottom()) {
			yv = 0;
			y = Math.floor(y / tileSize) * tileSize;
			xv *= 0.8;
		}
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

	public Collision colliding() {

		collision = new Collision(0);

		int minX = (int) Math.max(Math.floor(x / tileSize), 0);
		int maxX = (int) Math.min(Math.floor((x + w) / tileSize), SMLM.WIDTH / tileSize - 1);

		int minY = (int) Math.max(Math.floor(y / tileSize), 0);
		int maxY = (int) Math.min(Math.floor((y + h) / tileSize), SMLM.HEIGHT / tileSize - 1);

		int yv = (int) Math.floor(this.yv);

		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				if (map.get(i, j) != null) {

					int x0 = i * tileSize;
					int x1 = (i + 1) * tileSize;
					int y0 = j * tileSize;
					int y1 = (j + 1) * tileSize;

					if (x + w > x0 && x < x1) {
						if (y >= y1 + yv && y <= y1) {

							collision.top(true);

						}
						if (y + h >= y0 && y + h <= y0 + yv + 1) {

							collision.bottom(true);

						}
					}
					if (y + h > y0 && y < y1) {
						if (x + w >= x0 && x + w <= x0 + xv) {

							collision.left(true);

						}
						if (x >= x1 + xv && x <= x1) {

							collision.right(true);

						}
					}
				}
			}
		}

		return collision;

	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getImage(), (int) Math.floor(x), (int) Math.floor(y), null);
	}
}
