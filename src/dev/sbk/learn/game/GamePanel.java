package dev.sbk.learn.game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
public class GamePanel extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private Sprite background = new Background(0, 0, 0);
	private Tank tank = new Tank((GAME_WIDTH / 2) - 23, GAME_HEIGHT - 70, 0);
	private Thread gameThread;
	private boolean isRunning;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Jet> jets = new ArrayList<Jet>();
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	private int jetPosition[] = { 30, 80, 160, 240 };
	private int currentPosition;
	private int life = 3;
	private int score;

	public GamePanel() {
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (gameThread == null) {
			gameThread = new Thread(GamePanel.this);
		}
		gameThread.start();
	}

	@Override
	protected void onKeyUp(KeyEvent e) {
		tank.resetSpeed();
	}

	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			bullets.add(new Bullet(tank.getX() + 15, tank.getY() - 15, getRandomSpeed()));
			tank.shoot();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			tank.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			tank.moveRight();
		} else {

		}
	}

	@Override
	protected void onDraw(Graphics2D g2d) {
		g2d.setColor(Color.blue);
		background.draw(g2d);
		tank.draw(g2d);
		if (jets != null) {
			for (Jet jet : jets) {
				jet.draw(g2d);
			}
		}

		if (bullets != null) {
			for (Bullet bullet : bullets) {
				bullet.draw(g2d);
			}
		}
		if (bombs != null) {
			for (Bomb bomb : bombs) {
				bomb.draw(g2d);
			}
		}
		g2d.setColor(Color.white);
		g2d.drawString("Life : " + life, 10, 20);
		g2d.drawString("Score : " + score, GAME_WIDTH - 65, 20);
	}

	@Override
	public void run() {
		init();
		while (isRunning) {
			long startGame = System.currentTimeMillis();
			updateGame();
			renderGame();
			long endGame = System.currentTimeMillis() - startGame;
			long waitGame = (MILLISECOND / FPS) - (endGame / MILLISECOND);
			try {
				Thread.sleep(waitGame);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void renderGame() {
		repaint();
	}

	private void updateGame() {

		if (jets.size() < 5) {
			jets.add(new Jet(GAME_WIDTH, getJetYPosiotion(), getRandomSpeed()));
			currentPosition++;
		}

		for (int i = 0; i < jets.size(); i++) {
			Jet jet = jets.get(i);
			jet.update();
			for (int j = 0; j < bullets.size(); j++) {
				Bullet bullet = bullets.get(j);
				if (jet.getBound().intersects(bullet.getBound())) {
					jets.remove(jet);
					score++;
				}
			}
			if (jet.getX() == new Random().nextInt(250) || jet.getX() == new Random().nextInt(500)) {

				bombs.add(new Bomb(jet.getX() + 15, jet.getY() + 18, getRandomSpeed()));

			}
			if (jet.getX() < -90) {
				jets.remove(jet);
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.update();
			if (bullet.getY() < 0) {
				bullets.remove(bullet);
			}
		}
		for (int i = 0; i < bombs.size(); i++) {
			Bomb bomb = bombs.get(i);
			bomb.update();
			if (tank.getBound().intersects(bomb.getBound())) {
				life--;
				if (life > 0) {
					bombs.remove(bomb);
				} else {
					isRunning = false;
				}

			}

			if (bomb.getY() < 0) {
				bombs.remove(bomb);
			}
		}
	}

	private void init() {

		isRunning = true;
	}

	private int getRandomSpeed() {
		Random randomno = new Random();
		return randomno.nextInt(5) + 1;
	}

	private int getJetYPosiotion() {
		if (currentPosition >= jetPosition.length) {
			currentPosition = 0;
		}
		return jetPosition[currentPosition];
	}
}
