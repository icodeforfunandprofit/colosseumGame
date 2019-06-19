package shapes;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Scanner;

public class Polygone extends Polygon {

	private static final long serialVersionUID = 1L;
	private Color c;
	private boolean fill;

	public Polygone(int[] xpoints, int[] ypoints, int npoints, Color c) {
		super(xpoints, ypoints, npoints);
		this.c = c;
	}

	public Polygone(int[] xpoints, int[] ypoints, int npoints, Color c, boolean fill) {
		this(xpoints, ypoints, npoints, c);
		this.fill = fill;
	}

	public Polygone(String data) {
//		Scanner sc = new Scanner(data);
//		sc.useDelimiter(",\\s*");
//		String type = sc.next().trim();
//		if (type.charAt(0) == 'f') fill = true;
//		else fill = false;
//		c = new Color(sc.nextInt());
//		
//		ArrayList<Integer> xList = new ArrayList<Integer>();
//		ArrayList<Integer> yList = new ArrayList<Integer>();	
//		while (sc.hasNextInt()) {
//			xList.add(sc.nextInt());
//			yList.add(sc.nextInt());
//		}
//		sc.close();
//		npoints = xList.size();
//		
//		xpoints = new int[npoints];
//		ypoints = new int[npoints];
//		
//		for(int i = 0; i < npoints; i++) {
//			xpoints[i] = xList.get(i);
//			ypoints[i] = yList.get(i);
//		}
		String[] data2 = data.split(",\\s*");
		this.fill = data2[0].contains("fill");
		this.c = new Color(Integer.parseInt(data2[1]));
		npoints = 0;
		for (int i = 2; i < data2.length - 1; i += 2) {
			xpoints[i / 2 - 1] = Integer.parseInt(data2[i]);
			ypoints[i / 2 - 1] = Integer.parseInt(data2[i + 1]);
			npoints++;
		}
	}

	public boolean isFill() {
		return fill;
	}

	public Color getDrawColor() {
		return c;
	}

	public String toString() {
		String typeColor = (fill ? "filledPolygon," : "polygon,") + c.getRGB();
		String xy = "";
		for (int i = 0; i < npoints; i++) {
			xy += "," + xpoints[i] + "," + ypoints[i];
		}
		return String.format("%s%s%n", typeColor, xy);
	}

}
