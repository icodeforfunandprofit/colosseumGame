package shapes;

import java.awt.Color;
import java.awt.geom.Path2D.Double;
import java.awt.geom.PathIterator;
import java.util.Scanner;

public class Curve extends Double {

	private static final long serialVersionUID = 1L;
	private Color c;

	public Curve(int x1, int y1, Color c) {
		moveTo(x1, y1);
		this.c = c;
	}

	public Curve(String data) {
		Scanner sc = new Scanner(data);
		sc.useDelimiter(",\\s*");
		String type = sc.next();  // not used
		c = new Color(sc.nextInt());
		moveTo(sc.nextInt(), sc.nextInt());
		while (sc.hasNextInt()) {
			lineTo(sc.nextInt(), sc.nextInt());
		}
		sc.close();
	}


	public Color getDrawColor() {
		return c;
	}

	public String toString() {
		String typeColor = "curve," + c.getRGB();
		String xy = "";
		 PathIterator pathIterator = getPathIterator(null);
		    double[] coordinates = new double[6];
		    while (!pathIterator.isDone()) {
		        int type = pathIterator.currentSegment(coordinates);
		        if (type == PathIterator.SEG_LINETO || type == PathIterator.SEG_MOVETO) {
		            int x = (int) coordinates[0];
		            int y = (int) coordinates[1];
		            xy += "," + x + "," + y;
		        }
		        pathIterator.next();
		    }
		return String.format("%s%s%n", typeColor, xy);
	}

}
