package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import shapes.Curve;
import shapes.Ellipse;
import shapes.Line;
import shapes.Player;
import shapes.Polygone;
import shapes.Rectangle;

public class Handler {

	LinkedList<Player> movers;
	private ArrayList<Shape> shapesLevel1;
	private ArrayList<Shape> shapesLevel2;
	private ArrayList<Shape> shapesLevel3;
	private ArrayList<Shape> shapes;
	private int level;

	public Handler() {
		movers = new LinkedList<Player>();
		shapesLevel1 = new ArrayList<Shape>();
		shapesLevel2 = new ArrayList<Shape>();
		shapesLevel3 = new ArrayList<Shape>();
		loadLevels();
		level = 3;
		shapes = getShapes();
	}

	public void tick() {
		for (int i = 0; i < movers.size(); i++) {
			Player mover = movers.get(i);
			mover.tick();
		}
	}

	public Shape checkCollisions(Player m) {
		if (shapes.size() > 1) {
			Shape s = null;

			for (int i = 0; i < shapes.size(); i++) {
				s = shapes.get(i);
				if (s.intersects(m))
					return s;
			}
		}
		return null;
	}

	public void render(Graphics2D g2) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape s = shapes.get(i);
			if (s instanceof Line) {
				Line o = (Line) s;
				g2.setColor(o.getDrawColor());
			} else if (s instanceof Curve) {
				Curve o = (Curve) s;
				g2.setColor(o.getDrawColor());
			} else if (s instanceof Ellipse) {
				Ellipse o = (Ellipse) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			} else if (s instanceof Rectangle) {
				Rectangle o = (Rectangle) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			} else if (s instanceof Polygone) {
				Polygone o = (Polygone) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			}

			g2.draw(s);
		}

		for (int i = 0; i < movers.size(); i++) {
			Player mover = movers.get(i);
			mover.render(g2);
		}
	}

	public void addMover(Player player) {
		player.setShapes(shapes);
		this.movers.add(player);
	}

	public void removeMover(Player mover) {
		this.movers.remove(mover);
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public void setLevel(int level) {
		this.level = level;
		getShapes();
	}

	public void setLevel(int level, Player p) {
		this.level = level;
		getShapes();
		p.setShapes(shapes);
		p.setReadyToRespond(false);
		p.nullSpeaker();
	}

	public int getLevel() {
		return level;
	}

	public ArrayList<Shape> getShapes() {
		switch (level) {
		case 1:
			shapes = shapesLevel1;
			break;
		case 2:
			shapes = shapesLevel2;
			break;
		case 3:
			shapes = shapesLevel3;
			break;
		}

		return shapes;
	}

	private void loadLevels() {
//		assigning levels 1-3
		for (int i = 1; i < 4; i++) {
			setLevel(i);
			shapes = getShapes();
			generateShapes(i);
			}
		setLevel(1);
		}

//		File picturePath = new File("levels/Level" + level + ".jpg");
//		bi = ImageIO.read(picturePath);
//		int width = bi.getWidth();
//		int height = bi.getHeight();
//		if (bi.getType() != BufferedImage.TYPE_BYTE_GRAY) {
//			BufferedImage biTemp = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//			Graphics biTempG = biTemp.getGraphics();
//			biTempG.drawImage(bi, 0, 0, null);
//			bi = biTemp;
//			int[] data = new int[bi.getWidth() * bi.getHeight()];
//			bi.getData().getPixels(0, 0, bi.getWidth(), bi.getHeight(), data);
//			showBi();

	private void generateShapes(int num) {
		// TODO Auto-generated method stub
		switch (num) {
		case 1:
			generateRectangles(num);
			break;
		case 2:
			generateRectangles(num);
			generateCircles(num);
			break;
		case 3:
			generateRectangles(num);
			generateCircles(num);
			generatePolygons(num);
			break;
		}
	}

	private void generatePolygons(int num) {
	// TODO Auto-generated method stub
		int amount = (int)(Math.random() * ((0-5*num)+1));
		int numpoints = 4;
		int[] xpoints = new int[4];
		int[] ypoints = new int[4];
		xpoints[0] = 1;
		ypoints[0] = 1;
		xpoints[1] = 1;
		ypoints[1] = 11;
		xpoints[2] = 6;
		ypoints[2] = 6;
		xpoints[3] = xpoints[0];
		ypoints[3] = ypoints[0];
		
		for (int i = 0; i < 3; i++) {
			
		}
}

	private void generateCircles(int num) {
	// TODO Auto-generated method stub
		int amount = (int)(Math.random() * ((0-5*num)+1));
		for (int i = 0; i < amount; i++) {
			
		}
}

	private void generateRectangles(int num) {
		int amount = (int)(Math.random() * ((0-5*num)+1));
		for (int i = 0; i < amount; i++) {
//			two random x,y points on the screen (between 100-600)
			int x = (int)(Math.random() * ((100-600)+1))+100;
			int y = (int)(Math.random() * ((100-600)+1))+100;
			int sidelength = 15;
			shapesLevel1.add(new Rectangle(x, y, sidelength, sidelength, Color.black, true));
			}
	}
}
