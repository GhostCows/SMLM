package com.estudante.sc.senai.br.lhama.smlm;

import br.senai.sc.engine.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SMLM extends Game {
	public static final int TILE_SIZE = 64;
	public static final int WIDTH = TILE_SIZE * 16;
	public static final int HEIGHT = TILE_SIZE * 6;
	private Map map;
	private Character c;

	public SMLM(String nomeJogo, int width, int height) {
		super(nomeJogo, width, height);
		addKeyListener(new KeyboardHandler());
	}

	public static void main(String[] args) {
		new SMLM("SMLM", WIDTH, HEIGHT).startGame();
	}

	@Override
	public void init() {
		try {
			TileMap tMap = new TileMap(TILE_SIZE, "tilemaps/tilemap1.png", "tilemaps/tilemap1.json");
			map = new Map(tMap, 16, 6);
			map.setLevel("levels/level1.json");
			c = new Character(map, fps, "images/character.png", 256, 32, 32, 3);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void aposTermino() {
	}

	@Override
	public void gameLoop() {
		map.draw(g2d);
		c.run();
		c.draw(g2d);
	}

	private class KeyboardHandler extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					c.up(true);
					break;
				case KeyEvent.VK_DOWN:
					c.down(true);
					break;
				case KeyEvent.VK_LEFT:
					c.left(true);
					break;
				case KeyEvent.VK_RIGHT:
					c.right(true);
					break;
				case KeyEvent.VK_R:
					c.x = 64;
					c.y = 64;
					c.xv = 0;
					c.yv = 0;
					System.out.println(c.x);
					System.out.println(c.y);
					System.out.println("hi");
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					c.up(false);
					break;
				case KeyEvent.VK_DOWN:
					c.down(false);
					break;
				case KeyEvent.VK_LEFT:
					c.left(false);
					break;
				case KeyEvent.VK_RIGHT:
					c.right(false);
					break;
			}
		}
	}

}
