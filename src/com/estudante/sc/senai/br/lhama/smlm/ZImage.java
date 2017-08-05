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

	protected Image image;

	public ZImage(String path) {
		setImage(getClass().getClassLoader().getResource(path));
	}

	public ZImage(URI path) {
		try {
			setImage(new URL(path.toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public ZImage(URL path) {
		setImage(path);
	}

	public ZImage(Image img) {
		image = img;
	}

	public ZImage(ZImage img) {
		image = img.getImage();
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setImage(URL path) {

		String fileName = path.getFile();

		try {

			if (fileName.substring(fileName.lastIndexOf(".")).equalsIgnoreCase("gif")) {

				image = new ImageIcon(path).getImage();

			} else {

				image = ImageIO.read(path);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getWidth() {
		return getBufferedImage().getWidth();
	}

	public int getHeight() {
		return getBufferedImage().getHeight();
	}

	public Image getImage() {
		return image;
	}

	public BufferedImage getBufferedImage() {
		return (BufferedImage) image;
	}

	public ZImage crop(int x1, int y1, int x2, int y2) {
		int x =  Math.min(x1, x2);
		int y =  Math.min(y1, y2);
		int w = Math.max(x1, x2) - x;
		int h = Math.max(y1, y2) - y;
		return new ZImage(getBufferedImage().getSubimage(x, y, w, h));
	}

	public void draw(Graphics2D g2d, int x, int y) {
		g2d.drawImage(image, x, y, null);
	}

}