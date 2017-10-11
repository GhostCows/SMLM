package com.estudante.sc.senai.br.lhama.smlm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Marcelo Vogt on 26/07/2017.
 */
public class ZImage {

	private Image image;
	private ZRect rect;

	public ZImage(String path) {
		rect = new ZRect();
		if (path != null) {
			setImage(getClass().getClassLoader().getResource(path));
		}
	}

	public ZImage(Image img) {
		rect = new ZRect();
		image = img;
	}

	public ZImage(ZImage img) {
		rect = new ZRect();
		image = img.getImage();
	}

	public ZImage(ZRect rect, String path) {
		this.rect = rect;
		setImage(getClass().getClassLoader().getResource(path));
	}

	public ZImage(ZRect rect, Image img) {
		this.rect = rect;
		image = img;
	}

	public ZImage(ZRect rect, ZImage img) {
		this.rect = rect;
		image = img.getImage();
	}

	public void setImage(ZImage image) {
		if (image.getImage() != null) {
			this.image = image.getImage();
		}
	}

	public void setImage(URL path) {
		try {
			image = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(rect);
		rect.setW(image.getWidth(null));
		rect.setH(image.getHeight(null));
	}

	public int getWidth() {
		return (int) rect.w;
	}

	public int getHeight() {
		return (int) rect.h;
	}

	public Image getImage() {
		return image;
	}

	public BufferedImage getBufferedImage() {
		return (BufferedImage) image;
	}

	public ZRect getRect() {
		return rect;
	}

	public ZImage crop(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.max(x1, x2) - x;
		int h = Math.max(y1, y2) - y;
		return new ZImage(getBufferedImage().getSubimage(x, y, w, h));
	}

	public void draw(Graphics2D g2d, int x, int y) {
		g2d.drawImage(image, x, y, null);
	}

	public void draw(Graphics2D g2d) {
		if (rect == null) {
			draw(g2d, 0, 0);
		} else {
			rect.draw(g2d, this);
		}
	}

}