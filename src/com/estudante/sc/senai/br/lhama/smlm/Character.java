package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Fps;

import java.awt.*;

public class Character extends Sprite {

	private int jumpHeight;
	private boolean jumping;
	private boolean onGround;

	public Character(Map map, Fps fps, String path, double x, double y, int jumpHeight) {
		super(map, fps, path, x, y);
		this.jumpHeight = jumpHeight;
	}

	public void jump() {

		yv = -30;

	}

	/*@Override
	public void run() {

		super.run();
	}?*/

}
