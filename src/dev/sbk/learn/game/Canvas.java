package dev.sbk.learn.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public abstract class Canvas extends JPanel implements Helper, KeyListener {
	private static final long serialVersionUID = 1L;

	public Canvas() {
		this.addKeyListener(Canvas.this);
		setDoubleBuffered(true);
		setFocusable(true);
		requestFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d =(Graphics2D) g;
		onDraw(g2d);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		onKeyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		onKeyUp(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	protected abstract void onKeyUp(KeyEvent e);

	protected abstract void onKeyPressed(KeyEvent e);
    protected abstract void onDraw(Graphics2D g2d);
}
