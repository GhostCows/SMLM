package com.estudante.sc.senai.br.lhama.smlm;

import java.awt.*;

/**
 * Created by Marcelo Vogt on 15/10/2017.
 */
public class ZTile extends ZImage {

	private int tx;
	private int ty;
	private int tw;
	private int th;

	public ZTile(Image img, int x, int y, int width, int height) {
		super(img);
		tx = x;
		ty = y;

		tw = width;
		th = height;
	}

	public void draw(Graphics2D g2d, int x, int y, int w, int h) {
		int dx2 = x + w;
		int dy2 = y + h;
		int sx1 = tx * tw;
		int sy1 = ty * th;
		int sx2 = sx1 + tw;
		int sy2 = sy1 + th;
		g2d.drawImage(
				getImage(),
				x, y,
				dx2, dy2,
				sx1, sy1,
				sx2, sy2,
				null
		);
	}

	public void draw(Graphics2D g2d, int x, int y, int size) {
		draw(g2d, x * size, y * size, size, size);
	}

	@Override
	public String toString() {
		return "ZTile{" +
				"tx=" + tx +
				", ty=" + ty +
				", tw=" + tw +
				", th=" + th +
				'}';
	}
}
