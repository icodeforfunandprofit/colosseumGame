package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import shapes.Player;

public class GameKeyListener implements KeyListener {
	private Handler handler;
	private Player player;
	
	public GameKeyListener(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		player = handler.movers.getFirst();
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP: 
			player.setVelY(-2);
			break;
		case KeyEvent.VK_DOWN:
			player.setVelY(2);
			break;
		case KeyEvent.VK_LEFT:
			player.setVelX(-2);
			break;
		case KeyEvent.VK_RIGHT:
			player.setVelX(2);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP: 
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			player.setVelX(0);
			player.setVelY(0);
			break;
		case KeyEvent.VK_ENTER:
			player.respond();
			break;
		case KeyEvent.VK_1:
			handler.setLevel(1, player);
			break;
		case KeyEvent.VK_2:
			handler.setLevel(2, player);
			break;
		case KeyEvent.VK_3:
			handler.setLevel(3, player);
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
