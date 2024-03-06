package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable{
	private Point upperLeftPoint = new Point();
	private int height;
	private int width;
	private Color color;
	private Color innerColor;

	
	public Rectangle() {
		
	}
	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		this.selected = selected;
	}
	public Rectangle(Point upperLeftPoint, int height, int width, Color color, Color innerColor) {
		this(upperLeftPoint, height, width);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	public double area() {
		return (this.height*this.width);
	}
	public double circumfence() {
		return (this.height + this.width)*2;
	}
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getInnerColor() {
		return this.innerColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	@Override
	public String toString() {
		return "Rectangle;" + this.upperLeftPoint.toString2() + ";" + this.height+ ";" + this.width+";"+this.getColor()+";"+this.getInnerColor();
//		return this.upperLeftPoint + ", width: " + this.width + ", height: " + this.height;		
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle r = (Rectangle)obj;
			return (r.getUpperLeftPoint().equals(this.upperLeftPoint) && r.getHeight() == this.height && r.getWidth() == this.width );
		}
		else return false;
	}
	
	public boolean contains(int x, int y) {
		if (x > this.upperLeftPoint.getX() && x < this.upperLeftPoint.getX() + width
				&& y > this.upperLeftPoint.getY() && y < this.upperLeftPoint.getY() + height) {
			return true;
		}
		else return false;
	}
	public boolean contains(Point p) {
		if (p.getX() > this.upperLeftPoint.getX() && p.getX() < this.upperLeftPoint.getX() + width
				&& p.getY() > this.upperLeftPoint.getY() && p.getY() < this.upperLeftPoint.getY() + height)
			return true;
		else return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), this.width, this.height);
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX() - 2, this.upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 2, this.upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(this.upperLeftPoint.getX() - 2, this.upperLeftPoint.getY() + this.height - 2, 4, 4);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 2, this.upperLeftPoint.getY() + this.height - 2, 4, 4);
			g.setColor(Color.BLACK);
		}
		
	}
	
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}
	public void moveTo(int x, int y) {
		this.upperLeftPoint.moveTo(x, y);
	}
	
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return (int)(this.area() - ((Rectangle)o).area());
		}
		return 0;
	}
	
	public Rectangle clone(Rectangle rectangle) {
		rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		rectangle.setHeight(this.getHeight());
		rectangle.setWidth(this.getWidth());
		rectangle.setColor(this.getColor());
		rectangle.setInnerColor(this.getInnerColor());
		return rectangle;
	}

}
