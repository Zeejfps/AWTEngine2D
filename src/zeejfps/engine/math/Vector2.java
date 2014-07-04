package zeejfps.engine.math;

public final class Vector2 {

	public double x, y;
	
	public Vector2() {
		this(0, 0);
	}
	
	public Vector2(double x, double y) {
		
		this.x = x; 
		this.y = y; 
		
	}
	
	public Vector2 add(Vector2 vec) {
		x += vec.x;
		y += vec.y;
		
		return this;
	}
	
	public Vector2 add(double value) {
		x += value;
		y += value;
		
		return this;
	}
	
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		
		Vector2 result = new Vector2();
		result.x = v1.x + v2.x;
		result.y = v1.y + v2.y;
		
		return result;
	}
	
}
