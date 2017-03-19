package dev.sbk.learn.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tank extends Sprite{
	private Image image;
	public Tank(int x, int y, int speed) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/dev/sbk/learn/game/asset/images/tank.png")).getImage();
	}

	@Override
	protected void draw(Graphics2D g2d) {
		g2d.drawImage(this.image,getX(),getY(),null);
	}
	public void shoot() {
		System.out.println("Shooting");
		
	}
	public void moveLeft() {
		if(getX()<0)return ;
		incSpeed();
		setX(getX()-getSpeed());
		
	}
	public void moveRight() {
		if(getX()>GAME_WIDTH-48) return;
		incSpeed();
		setX(getX()+getSpeed());
		
	}
	private void incSpeed(){
		if(getSpeed()<AUTO_SPEED) setSpeed(getSpeed()+1);
	}
	public void resetSpeed(){
		setSpeed(0);
	}
	public Rectangle getBound(){
		return new Rectangle(getX(), getY(), this.image.getWidth(null), this.image.getHeight(null));
	}
}
