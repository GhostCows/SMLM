package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Fps;

import java.awt.*;

public class Sprite extends ZImage {
	private static final double GRAVITY = SMLM.TILE_SIZE * 10;

	protected double x;
	protected double y;
	protected double xv;
	protected double yv;
	protected int w;
	protected int h;
	protected Fps fps;

	public Sprite(Fps fps, String path, double x, double y) {
		super(path);
		this.x = x;
		this.y = y;
		this.w = getWidth();
		this.h = getHeight();
		this.fps = fps;
	}

	public void run(Map map) {
		yv += GRAVITY / 30;
		x += xv / 30;
		y += yv / 30;

		// Collision

		

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
