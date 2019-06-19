package game;

//http://blog.slapware.eu/game-engine/preface/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import shapes.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7580815534084638412L;
	private final int WIDTH = 1000; // 1200
	private final int HEIGHT = 750; // 1000
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private BufferStrategy bs;

	public Game() {
		handler = new Handler();
		addKeyListener(new GameKeyListener(handler));
		this.createBufferStrategy(3);
		bs = this.getBufferStrategy();
		new Frame(this, WIDTH, HEIGHT);
		handler.addMover(new Player(0, 0, 32, 32, Color.white, WIDTH, HEIGHT, handler));
		requestFocusInWindow();
	}

	private void render() {
		Graphics2D g2 = null;
		do {
			try {
				g2 = (Graphics2D) bs.getDrawGraphics();
				handler.render(g2);
			} finally {
				g2.dispose();
			}
			bs.show();
		} while (bs.contentsLost());
	}

	private void tick() {
		handler.tick();

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 600.;
		double ns = 1e9 / amountOfTicks;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
		}
		stop();
	}

	public static void main(String[] args) {
		new Game();
	}

}
