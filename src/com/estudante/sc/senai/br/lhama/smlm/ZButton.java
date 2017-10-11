package com.estudante.sc.senai.br.lhama.smlm;

/**
 * Created by Marcelo Vogt on 29/09/2017.
 */
public class ZButton extends ZImage {

	private ZImage normal, clicked, hovered;
	private ZRunnable click, hover, over;
	private boolean prevClicking, prevHovering;

	public ZButton(ZRect rect, String normal, String clicked, Runnable click) {
		super(rect, normal);
		this.normal = new ZImage(normal);
		this.clicked = new ZImage(clicked);
		this.click = new ZRunnable(click);
		this.hover = new ZRunnable();
		this.over = new ZRunnable();
	}

	public ZButton(ZRect rect, String normal, String clicked, String hovered, Runnable click, Runnable hover) {
		super(rect, normal);
		this.normal = new ZImage(normal);
		this.clicked = new ZImage(clicked);
		this.hovered = new ZImage(hovered);
		this.click = new ZRunnable(click);
		this.hover = new ZRunnable(hover);
		this.over = new ZRunnable();
	}

	public ZButton(ZRect rect, String normal, String clicked, String hovered, Runnable click, Runnable hover, Runnable over) {
		super(rect, normal);
		this.normal = new ZImage(normal);
		this.clicked = new ZImage(clicked);
		this.hovered = new ZImage(hovered);
		this.click = new ZRunnable(click);
		this.hover = new ZRunnable(hover);
		this.over = new ZRunnable(over);
	}

	public void update(ZPoint mouse, boolean clicking) {
		boolean h = mouse.in(getRect());
		if(h) {
			if (clicking) {
				setImage(clicked);
			} else if (prevClicking) {
				click.run();
			} else {
				setImage(hovered);
				over.run();
				if (!prevHovering) {
					hover.run();
				}
			}
		}else {
			setImage(normal);
		}
		prevClicking = clicking;
		prevHovering = h;
	}

}
