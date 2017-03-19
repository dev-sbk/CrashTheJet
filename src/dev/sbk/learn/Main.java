package dev.sbk.learn;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dev.sbk.learn.game.GamePanel;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame gameFrame = new JFrame("Game");
				gameFrame.add(new GamePanel());
				gameFrame.pack();
				gameFrame.setSize(450, 650);
				gameFrame.setResizable(false);
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setLocationRelativeTo(null);
				gameFrame.setVisible(true);

			}
		});
	}

}
