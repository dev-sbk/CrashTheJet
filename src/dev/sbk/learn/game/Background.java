package dev.sbk.learn.game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Background extends Sprite {
	private Image image;

	public Background(int x, int y, int speed) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/dev/sbk/learn/game/asset/images/bg.jpg")).getImage();
	}

	@Override
	protected void draw(Graphics2D g2d) {
		
		g2d.drawImage(this.image,getX(),getY(),null);
	}

}
