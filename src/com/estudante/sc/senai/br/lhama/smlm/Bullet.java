package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Fps;

import java.awt.*;

public class Bullet extends Sprite {
	public Bullet(Graphics2D g2d, Fps fps, String path, double x, double y, double xv, double yv) {
		super(fps, path, x, y);
		this.xv = xv;
		this.yv = yv;
	}
}
