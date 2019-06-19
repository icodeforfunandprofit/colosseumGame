package shapes;

import java.awt.Color;
import java.awt.geom.Line2D.Double;

public class Line extends Double {

	private static final long serialVersionUID = 1L;
	private Color c;
	
	public Line (int x1, int y1, int x2, int y2, Color c) {
		super(x1, y1, x2, y2);
		this.c = c;
	}
	
	public Line (String line) {
		String[] data = line.split(",\\s*");
		x1 = Integer.parseInt(data[0]);
		y1 = Integer.parseInt(data[1]);
		x2 = Integer.parseInt(data[2]);
		y2 = Integer.parseInt(data[3]);
		c = new Color(Integer.parseInt(data[4]));
	}
	
	public Color getDrawColor() {
		return c;
	}
	
	public String toString() {
	  	return String.format("%d, %d, %d, %d, %d%n", (int) x1, (int) y1,
	  		(int) x2, (int) y2, c.getRGB());
	  }

}
