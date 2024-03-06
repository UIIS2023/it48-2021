package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends Shape implements Serializable{
	private Point center = new Point();
	private int radius;
	private Color color;
	private Color innerColor;
	
	public Circle() {
		
	}
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected = selected;
	}
	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if(radius<0) {
			throw new Exception("Poluprecnik ne moze da bude manji od 0");
		}		
		System.out.println("Testiranje daljeg izvrsavanja metode");
		this.radius = radius;
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
	public double area() {
		return (this.radius * this.radius) * Math.PI;
	}
	public double circumfence() {
		return (this.radius * 2) * Math.PI;
	}
	
	@Override
	public String toString() {
		return "Circle;" + this.center.toString2() + ";" + this.radius+";"+this.getColor()+";"+this.getInnerColor();
	}
	public String toString2() {
		return this.center.toString2() + ";" + this.radius+";"+this.getColor()+";"+this.getInnerColor();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle c = (Circle)obj;
			if(this.getCenter().equals(c.getCenter()) && this.getRadius() == c.getRadius())
				return true;						
		}
		return false;
	}
	
	public boolean contains(int x, int y) {
		if(this.getCenter().distance(x, y) <= radius)
			return true;
		else return false;
	}
	public boolean contains(Point p) {
		if(this.getCenter().distance(p.getX(), p.getY()) <= radius)
			return true;
		else return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);

		
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 2, this.center.getY() - 2, 4, 4);
			g.drawRect(this.center.getX() - this.radius - 2, this.center.getY() - 2, 4, 4);
			g.drawRect(this.center.getX() + this.radius - 2, this.center.getY() - 2, 4, 4);
			g.drawRect(this.center.getX() - 2, this.center.getY() - this.radius - 2, 4, 4);
			g.drawRect(this.center.getX() - 2, this.center.getY() + this.radius - 2, 4, 4);
			g.setColor(Color.BLACK);
		}
	}
	
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}
	public void moveTo(int x, int y) {
		this.center.moveTo(x, y);
	}
	
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return (int)(this.area()-((Circle)o).area());
		}
		return 0;
	}
	
	public Circle clone(Circle circle) {
		circle.getCenter().setX(this.getCenter().getX());
		circle.getCenter().setY(this.getCenter().getY());
		try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setColor(this.getColor());
		circle.setInnerColor(this.getInnerColor());
		return circle;
	}
	
	
	
	

	
}
