package shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import shapes.Rectangle;
import game.Handler;

public class Player extends Rectangle implements MoveableShape {

	private static final long serialVersionUID = -9086638501058842642L;
	private int gameWidth;
	private int gameHeight;
	private Handler handler;
	private Shape shape;
	private ArrayList<Shape> shapes;
	private Shape speaker;
	private boolean readyToRespond;
	private String response;

	public Player(int x, int y, int w, int h, Color c, int gameWidth, int gameHeight, Handler handler) {
		super(x, y, w, h, c);
		this.c = c;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.handler = handler;
		setVelX((float) 1.);
		setVelY((float) 1.);
	}

	public void tick() {
		if (x - width > gameWidth || x < 0) {
			setVelX(getVelX() * -1);
		}
		if (y - width > gameHeight || y < 0) {
			setVelY(getVelY() * -1);
		}
		shape = handler.checkCollisions(this);
		if (shapes.size() > 1) {
			if (shape == null) {
				x += getVelX();
				y += getVelY();
			} else {
				handler.removeShape(shape);
			}
		}
		if (shapes.size() == 1 && shapes.get(0).intersects(this)) {
			if (shapes.get(0) instanceof Ellipse)
				speaker = (Ellipse) shapes.get(0);
			else if (shapes.get(0) instanceof Polygone) {
				speaker = (Polygone) shapes.get(0);
				readyToRespond = false;
			}
			else
				speaker = null;
		} else {
			x += getVelX();
			y += getVelY();
		}
	}

	public void render(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.draw(this);
		if (speaker != null && speaker instanceof Ellipse) {
			java.awt.Rectangle r = speaker.getBounds();
			g2.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 22));
			g2.setColor(Color.magenta);
			g2.drawString(((Ellipse) speaker).speak(), r.x, r.y);
			if (readyToRespond)
				g2.drawString(response, (int) (x + width), (int) y);
		}
	}

	public void stop() {
		setVelX(0);
		setVelY(0);
	}
	
	public void nullSpeaker() {
		speaker = null;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void setReadyToRespond(boolean b) {
		readyToRespond = b;
		
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String respond() {
		setResponse("Bison");
		readyToRespond = true;
		return response;

	}

	public String toString() {
		return String.format("%s, %d, %d, %d, %d, %d%n", (fill ? "filledPlayer " : "player"), (int) x, (int) y,
				(int) width, (int) height, c.getRGB());
	}

}
