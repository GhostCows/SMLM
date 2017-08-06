package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Game;


public class SMLM extends Game {
	public static final int TILE_SIZE = 64;
	private Map map;
	private Character c;

	public SMLM(String nomeJogo, int width, int height) {
		super(nomeJogo, width, height);
	}

	public static void main(String[] args) {
		new SMLM("SMLM", 64*16, 64*6).startGame();
	}

	@Override
	public void init() {
		try {
			TileMap tMap = new TileMap(TILE_SIZE, "images/tileTest1.png");
			map = new Map(tMap, 16, 6);
			map.setLevel("levels/level1.json");
			c = new Character(map, fps, "images/character.png", 32, 32, 3);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void aposTermino() {}

	@Override
	public void gameLoop() {
		map.draw(g2d);
		c.run();
		c.draw(g2d);
	}


}
