package zeejfps.engine.utils;

import zeejfps.engine.utils.math.Matrix3;

public class Transform {
	
	private Matrix3 transform;
	
	public Transform() {
		
		transform = new Matrix3();
		
	}
	
	public void rotate(double angle) {
		
		double a = Math.toRadians(angle);
		
		Matrix3 shear1 = new Matrix3();
		shear1.m01 = -Math.tan(a/2);
		
		Matrix3 shear2 = new Matrix3();
		shear2.m10 = Math.sin(a);
		
		transform.mult(shear1.mult(shear2));
	}
	
	public void scale(double x, double y) {
		
		Matrix3 scale = new Matrix3();
		scale.m00 = x; scale.m01 = 0;
		scale.m10 = 0; scale.m11 = y;
		
		transform.mult(scale);
		
	}
	
	public void translate(double x, double y) {
		
		Matrix3 translate = new Matrix3();
		translate.m02 = x;
		translate.m12 = y;
		
		transform.mult(translate);
	}
	
	public Matrix3 getMatrix() {
		return transform;
	}
	
}
