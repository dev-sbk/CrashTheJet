package dev.sbk.learn.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet extends Sprite{
	private Image image;
	public Bullet(int x, int y, int speed) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/dev/sbk/learn/game/asset/images/fire.png")).getImage();
	}

	@Override
	protected void draw(Graphics2D g2d) {
		g2d.drawImage(this.image,getX(),getY(),null);
	}

	public void update() {
		setY(getY()-getSpeed());
		
	}
	public Rectangle getBound(){
		return new Rectangle(getX(), getY(), this.image.getWidth(null), this.image.getHeight(null));
	}
}
