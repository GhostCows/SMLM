package com.estudante.sc.senai.br.lhama.smlm;

import java.awt.*;

/**
 * Created by Marcelo Vogt on 29/09/2017.
 */
public class ZRect {

	public double x;
	public double y;
	public double w;
	public double h;

	public ZRect(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public ZRect(double w, double h) {
		x = 0;
		y = 0;
		this.w = w;
		this.h = h;
	}

	public ZRect() {
		x = 0;
		y = 0;
		w = 0;
		h = 0;
	}

	public ZPoint getUpLeft() {
		return new ZPoint(x, y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public void draw(Graphics2D g2d, ZImage img) {
		g2d.drawImage(img.getImage(), (int) x, (int) y, (int) w, (int) h, null);
	}

}
