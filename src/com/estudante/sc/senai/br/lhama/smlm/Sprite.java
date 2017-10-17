package com.estudante.sc.senai.br.lhama.smlm;

import java.util.HashMap;

public class Sprite extends ZRect {
	private HashMap<String, ZStrip> animations;
	private String defaultAnimation;

	public Sprite(HashMap<String, String> paths, double x, double y, double w, double h) {
		super(x, y, w, h);
		loadAnimations(paths);
		defaultAnimation = "idle";
	}

	public Sprite(HashMap<String, String> paths, String defaultAnimation, double x, double y, double w, double h) {
		super(x, y, w, h);
		loadAnimations(paths);
		this.defaultAnimation = defaultAnimation;
	}

	public Sprite(Sprite sprite) {
		animations = sprite.animations;
		defaultAnimation = sprite.defaultAnimation;
		x = sprite.x;
		y = sprite.y;
		w = sprite.w;
		h = sprite.h;
	}

	private void loadAnimations(HashMap<String, String> paths) {
		animations = new HashMap<>();
		paths.forEach((name, path) -> {
			String[] ps = path.split("#");
			ZStrip strip = new ZStrip(ps[0], Integer.parseInt(ps[1]));

			animations.put(name, strip);
		});
	}
}
