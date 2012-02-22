package chesspuzzles.knightstour;

public class Point {

	private int x = 0;
	private int y = 0;

	public Point(int x, int y) {
		setPoint(x, y);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
