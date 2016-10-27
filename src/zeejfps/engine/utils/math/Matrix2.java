package zeejfps.engine.utils.math;

public class Matrix2 {
	
	public double 
	
		m00, m01, 
		m10, m11;

	public Matrix2() {
		
		m00 = 1; m01 = 0;
		m10 = 0; m11 = 1;
		
	}
	
	public Matrix2(Matrix2 copy) {
		
		m00 = copy.m00; m01 = copy.m01;
		m10 = copy.m10; m11 = copy.m11;
		
	}
	
	public Matrix2 mult(double value) {
		
		m00 *= value; m01*= value;
		m10 *= value; m11*= value;
		
		return this;
	}
	
	public Matrix2 mult(Matrix2 mat) {
		
		double _m00 = m00 * mat.m00 + m01 * mat.m10;
		double _m01 = m00 * mat.m01 + m01 * mat.m11;
		
		double _m10 = m10 * mat.m00 + m11 * mat.m10;
		double _m11 = m10 * mat.m01 + m11 * mat.m11;
		
		m00 = _m00;
		m01 = _m01;
		m10 = _m10;
		m11 = _m11;
		
		return this;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[m00]=").append(m00).append(", [m01]=").append(m01).append("\n");
		sb.append("[m10]=").append(m10).append(", [m11]=").append(m11);
		
		return sb.toString();
	}
	
	public static Matrix2 mult(Matrix2 mat1, Matrix2 mat2) {
		
		Matrix2 result = new Matrix2(mat1);
		result.mult(mat2);
		
		return result;
	}
	
	public static Matrix2 mult(Matrix2 mat1, double value) {
		
		Matrix2 result = new Matrix2(mat1);
		result.mult(value);
		
		return result;
	}
	
	public static Vector2 mult(Matrix2 mat, Vector2 vec) {
		
		Vector2 result = new Vector2();
		
		result.x = mat.m00 * vec.x + mat.m01 * vec.y;
		result.y = mat.m10 * vec.x + mat.m11 * vec.y;
		
		return result;
	}
	
}
