package shapes;

import java.awt.Color;
import java.awt.geom.Ellipse2D.Double;
import java.util.Scanner;

public class Ellipse extends Double {

	private static final long serialVersionUID = 1L;
	private Color c;
	private boolean fill;
	private boolean speaking;
	private String words;

	public Ellipse(int x1, int y1, int width, int heigth, Color c) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = heigth;
		this.c = c;
	}

	public Ellipse(int x1, int y1, int width, int heigth, Color c, boolean fill) {
		this(x1, y1, width, heigth, c);
		this.fill = fill;
	}

	public Ellipse(String data) {
		Scanner sc = new Scanner(data);
		sc.useDelimiter(",\\s*");
		String type = sc.next();
		this.x = sc.nextInt();
		this.y = sc.nextInt();
		this.width = sc.nextInt();
		this.height = sc.nextInt();
		this.c = new Color(sc.nextInt());
		sc.close();
		this.fill = type.contains("filled");
	}

	public boolean isFill() {
		return fill;
	}

	public Color getDrawColor() {
		return c;
	}

	public boolean isSpeaking() {
		return speaking;
	}

	public String speak() {
		speaking = true;
		words = "What does the Buffalo say to his son in the morning?";
		return words;
	}

	public String toString() {
		return String.format("%s, %d, %d, %d, %d, %d%n", (fill ? "filledCircle " : "circle "), (int) x, (int) y,
				(int) width, (int) height, c.getRGB());
	}

}
