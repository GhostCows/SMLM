package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Fps;

import java.awt.*;

public class Character extends Sprite {

	private int jumpHeight;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public Character(Map map, Fps fps, String path, double maxSpeed, double x, double y, int jumpHeight) {
		super(map, fps, path, maxSpeed, x, y);
		this.jumpHeight = jumpHeight;
	}

	@Override
	public void run() {

		if (left != right) {
			if (left) {
				xv -= tileSize / 30;
			} else {
				xv += tileSize / 30;
			}
		}

		super.run();

		if (collision.bottom()) {
			if (up) {
				yv = -Math.sqrt(2 * GRAVITY * tileSize * jumpHeight) / 30;//-jumpHeight * GRAVITY / 120;
			}
		} else {
			xv = Math.signum(xv) * Math.min(Math.abs(xv), 0.8 * tileSize / (30 - 30 * 0.8));
		}

	}

	public boolean up() {
		return up;
	}

	public void up(boolean up) {
		this.up = up;
	}

	public boolean down() {
		return down;
	}

	public void down(boolean down) {
		this.down = down;
	}

	public boolean left() {
		return left;
	}

	public void left(boolean left) {
		this.left = left;
	}

	public boolean right() {
		return right;
	}

	public void right(boolean right) {
		this.right = right;
	}

}
