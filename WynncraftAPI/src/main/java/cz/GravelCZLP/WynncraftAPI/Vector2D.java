package cz.GravelCZLP.WynncraftAPI;

public class Vector2D {

	private double x, y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance(Vector2D vec) {
		double dx = vec.x - x;
		double dy = vec.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
