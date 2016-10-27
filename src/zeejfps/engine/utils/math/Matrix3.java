package zeejfps.engine.utils.math;

public class Matrix3 {

	public double 
	
		m00, m01, m02, 
		m10, m11, m12,
		m20, m21, m22;

	public Matrix3() {
		
		m00 = 1; m01 = 0; m02 = 0;
		m10 = 0; m11 = 1; m12 = 0;
		m20 = 0; m21 = 0; m22 = 1;
		
	}
	
	public Matrix3(Matrix3 copy) {
		
		m00 = copy.m00; m01 = copy.m01; m02 = copy.m02;
		m10 = copy.m10; m11 = copy.m11; m12 = copy.m02;
		m20 = copy.m20; m21 = copy.m21; m22 = copy.m22;
		
	}
	
	public Matrix3 mult(double value) {
		
		m00 *= value; m01*= value; m02 *= value;
		m10 *= value; m11*= value; m12 *= value;
		m20 *= value; m21*= value; m22 *= value;
		
		return this;
	}
	
	public Matrix3 mult(Matrix3 mat) {
		
		double _m00 = (m00 * mat.m00) + (m01 * mat.m10) + (m02 * mat.m20);
		double _m01 = (m00 * mat.m01) + (m01 * mat.m11) + (m02 * mat.m21);
		double _m02 = (m00 * mat.m02) + (m01 * mat.m12) + (m02 * mat.m22);
		
		double _m10 = (m10 * mat.m00) + (m11 * mat.m10) + (m12 * mat.m20);
		double _m11 = (m10 * mat.m01) + (m11 * mat.m11) + (m12 * mat.m11);
		double _m12 = (m10 * mat.m02) + (m11 * mat.m12) + (m12 * mat.m22);
		
		double _m20 = (m20 * mat.m00) + (m21 * mat.m10) + (m22 * mat.m20);
		double _m21 = (m20 * mat.m01) + (m21 * mat.m11) + (m22 * mat.m11);
		double _m22 = (m20 * mat.m02) + (m21 * mat.m12) + (m22 * mat.m22);
		
		m00 = _m00;
		m01 = _m01;
		m02 = _m02;
		m10 = _m10;
		m11 = _m11;
		m12 = _m12;
		m20 = _m20;
		m21 = _m21;
		m22 = _m22;
		
		return this;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[m00]=").append(m00).append(", [m01]=").append(m01).append(", [m02]=").append(m02).append("\n");
		sb.append("[m10]=").append(m10).append(", [m11]=").append(m11).append(", [m12]=").append(m12).append("\n");
		sb.append("[m20]=").append(m20).append(", [m21]=").append(m21).append(", [m22]=");
		
		return sb.toString();
	}
	
	public static Matrix3 mult(Matrix3 mat1, Matrix3 mat2) {
		
		Matrix3 result = new Matrix3(mat1);
		result.mult(mat2);
		
		return result;
	}
	
	public static Matrix3 mult(Matrix3 mat1, double value) {
		
		Matrix3 result = new Matrix3(mat1);
		result.mult(value);
		
		return result;
	}
	
	public static Vector2 mult(Matrix3 mat, Vector2 vec) {
		
		Vector2 result = new Vector2();
		
		result.x = mat.m00 * vec.x + mat.m01 * vec.y + mat.m02;
		result.y = mat.m10 * vec.x + mat.m11 * vec.y + mat.m12;
		
		return result;
	}
	
}
