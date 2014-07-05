package zeejfps.engine.graphics;

import zeejfps.engine.utils.math.Vector2;

public class Pixel {

	private Vector2 pos;
	private int color;
	
	public Pixel(int x, int y, int color) {
		this(new Vector2(x,y), color);
	}
	
	public Pixel(Vector2 pos, int color) {
		this.pos = pos;
		this.color = color;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public int getColor() {
		return color;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Pixel) {
			
			Pixel p = (Pixel)obj;
			return p.pos.equals(pos) && p.color == color;
			
		}
		
		return false;
	}
	
}
