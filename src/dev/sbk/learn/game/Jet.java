package dev.sbk.learn.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Jet extends Sprite{

	private Image image;
	public Jet(int x, int y, int speed) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/dev/sbk/learn/game/asset/images/jet.png")).getImage();
	}

	@Override
	protected void draw(Graphics2D g2d) {
		g2d.drawImage(this.image,getX(),getY(),null);
	}
	public void update() {
		setX(getX()-getSpeed());
		
	}
	public Rectangle getBound(){
		return new Rectangle(getX(), getY(), this.image.getWidth(null), this.image.getHeight(null));
	}

	
}
