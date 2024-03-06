package geometry;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Moveable, Comparable{
	protected boolean selected;
	
	private Color clr = Color.black;
	private Color innerClr = Color.white;
	
	public Shape() {
		
	}
	public Shape(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	public abstract boolean contains(Point p);
	
	public Color getColor() {
		return this.clr;
	}
	public void setColor(Color clr) {
		this.clr = clr;
	}
	
	public Color getInnerColor() {
		return this.innerClr;
	}
	public void setInnerColor(Color innerClr) {
		this.innerClr = innerClr;
	}
	

}
