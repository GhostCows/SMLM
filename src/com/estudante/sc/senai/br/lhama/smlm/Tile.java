package com.estudante.sc.senai.br.lhama.smlm;

import org.json.simple.JSONObject;

import java.awt.*;

public class Tile extends ZImage {

	private double friction;
	private boolean collision;

	public Tile(ZImage img, JSONObject obj) {
		super(img);

		friction = Double.valueOf(obj.get("f").toString());
		collision = (Boolean) obj.get("c");
	}

	public double getFriction() {
		return friction;
	}

	public boolean isCollision() {
		return collision;
	}

	@Override
	public String toString() {
		return "f: " + friction + " c: " + collision;
	}
}
