package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable{
	private int x;
	private int y;
	private Color color;
	public Point() {
		
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, boolean selected) {
		this(x, y);
		this.selected = selected;
	}
	public Point(int x, int y, Color color) {
		this(x,y);
		setColor(color);
	}
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		if(x>0)
			this.x = x;
		else System.out.println("Vrijednost mora biti pozitivna");
	}
	
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		if(y>0)
			this.y = y;
		else System.out.println("Vrijednost mora biti pozitivna");
	}
		
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	

	public double distance(int x2, int y2) {
		double dx = x2 - this.x;
		double dy = y2 - this.y;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	
	@Override
	public String toString() {
		return "Point;" + this.x + ";" + this.y + ";" + this.getColor();
	}
	public String toString2() {
		return this.x + ";" + this.y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point p = (Point)obj;
			if(this.x == p.x && this.y == p.y)
				return true;
			else return false;
		}
		return false;
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <=2;
	}
	public boolean contains(Point p) {
		return this.distance(p.getX(), p.getY()) <= 5;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x-1, y, x+1, y);
		g.drawLine(x, y+1, x, y-1);
		//g.drawLine(this.x, this.y, this.x, this.y);
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
			g.setColor(Color.BLACK);
		}
	}
	
	public void moveBy(int x, int y) {
		this.x += x;
		this.y += y;
	}
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int compareTo(Object o) {
		if(o instanceof Point) {
			return (int)(this.distance(0, 0) - ((Point)o).distance(0, 0));
		}
		return 0;
	}
	
	public Point clone(Point point) {
		point.setX(this.getX());
		point.setY(this.getY());
		point.setColor(this.getColor());
		return point;
	}

}
