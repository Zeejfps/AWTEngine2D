package zeejfps.engine.utils.math;

/**
 * This is a utility class that can be used in your games to represent speed and direction or used as a point in space.
 * @author Zeejfps
 */
public final class Vector2 {

	// the vector components
	public double x, y;
	
	/**
	 * Creates a vector2 and initializes the x and y to 0.
	 */
	public Vector2() {
		this(0, 0);
	}
	
	/**
	 * Creates a vector with the same values of x and y as the passed in vector.
	 * @param copy the vector you want to copy.
	 */
	public Vector2(Vector2 copy) {
		this(copy.x, copy.y);
	}
	
	/**
	 * Creates a vector with given values of x and y.
	 * @param x the x component of a vector.
	 * @param y the y component of a vector.
	 */
	public Vector2(double x, double y) {
		
		this.x = x; 
		this.y = y; 
		
	}
	
	/**
	 * This method will add a vector to the current vector.
	 * @param vec vector to be added to this vector.
	 * @return returns 'this' vector so you can chain multiple methods.
	 */
	public Vector2 add(Vector2 vec) {
		x += vec.x;
		y += vec.y;
		
		return this;
	}
	
	/**
	 * This method will add a scalar value to the current vector.
	 * @param value the scalar value to be added to this vector.
	 * @return returns 'this' vector so you can chain multiple methods.
	 */
	public Vector2 add(double value) {
		x += value;
		y += value;
		
		return this;
	}
	
	/**
	 * This method will subtract a vector from the current vector.
	 * @param vec vector to be subtracted to this vector.
	 * @return returns 'this' vector so you can chain multiple methods.
	 */
	public Vector2 sub(Vector2 vec) {
		x -= vec.x;
		y -= vec.y;
		
		return this;
	}
	
	/**
	 * This method will subtract a scalar value from the current vector.
	 * @param value the scalar value to be subtracted to this vector.
	 * @return returns 'this' vector so you can chain multiple methods.
	 */
	public Vector2 sub(double value) {
		x -= value;
		y -= value;
		
		return this;
	}
	
	/**
	 * This method will multiply 'this' vector by a scalar value.
	 * @param value the scalar value to be multiplied by.
	 * @return returns 'this' vector so you can chain multiple methods.
	 */
	public Vector2 mult(double value) {
		x *= value;
		y *= value;
		
		return this;
	}
	
	/**
	 * This method will divide 'this' vector by a scalar value.
	 * @param value the scalar value to be divided by.
	 * @return returns 'this' vector so you can chain multiple methods.
	 */
	public Vector2 div(double value) {
		x /= value;
		y /= value;
		
		return this;
	}
	
	@Override
	public String toString() {
		
		return "Vector2[x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Vector2) {
			Vector2 v = (Vector2)obj;
			return v.x == x && v.y == y;
		}
		
		return false;
	}
	
	//These methods are simply a wrapper and should be used if you do not want to modify the original vector.
	
	/**
	 * 
	 * @param v1 
	 * @param v2
	 * @return resulting vector from performed operation.
	 */
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		
		Vector2 result = new Vector2(v1);
		result.add(v2);
		
		return result;
	}
	
	/**
	 * 
	 * @param v1 
	 * @param v2
	 * @return resulting vector from performed operation.
	 */
	public static Vector2 add(Vector2 v1, double value) {
		
		Vector2 result = new Vector2(v1);
		result.add(value);
		
		return result;
	}
	
	/**
	 * 
	 * @param v1 
	 * @param v2
	 * @return resulting vector from performed operation.
	 */
	public static Vector2 sub(Vector2 v1, Vector2 v2) {
		
		Vector2 result = new Vector2(v1);
		result.sub(v2);
		
		return result;
	}
	
	/**
	 * 
	 * @param v1 
	 * @param v2
	 * @return resulting vector from performed operation.
	 */
	public static Vector2 sub(Vector2 v1, double value) {
		
		Vector2 result = new Vector2(v1);
		result.sub(value);
		
		return result;
	}
	
	/**
	 * 
	 * @param v1 
	 * @param v2
	 * @return resulting vector from performed operation.
	 */
	public static Vector2 mult(Vector2 v1, double value) {
		
		Vector2 result = new Vector2(v1);
		result.mult(value);
		
		return result;
	}
	
	/**
	 * 
	 * @param v1 
	 * @param v2
	 * @return resulting vector from performed operation.
	 */
	public static Vector2 div(Vector2 v1, double value) {
		
		Vector2 result = new Vector2(v1);
		result.div(value);
		
		return result;
	}
	
	/**
	 * This method will give the dot product of two vectors.
	 * @param v1 
	 * @param v2
	 * @return a double value representing the dot product of two vectors.
	 */
	public static double dotProduct(Vector2 v1, Vector2 v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}

	/**
	 * This method will calculate the magnitude of a given vector.
	 * @param vec the vectors whos magnitude to compute.
	 * @return returns the magnitude, aka length, of the given vector.
	 */
	public static double magnitude(Vector2 vec) {
		return Math.sqrt(vec.x * vec.x + vec.y * vec.y);
	}
	
}
