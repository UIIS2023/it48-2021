package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;


public class HexagonAdapter extends Shape implements Serializable{
	
	
	private Hexagon hexagon;
	public HexagonAdapter() {
		
	}
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public HexagonAdapter(Hexagon hexagon, Color color, Color innerColor) {
		this.hexagon = hexagon;
		setColor(color);
		setInnerColor(innerColor);
		
	}

	
	public int getX() {
		return hexagon.getX();
	}
	public int getY() {
		return hexagon.getY();
	}
	public int getRadius() {
		return hexagon.getR();
	}
	public void setRadius(int radius) {
		hexagon.setR(radius);
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}
	
	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getX() - 2, this.getY() - 2, 4, 4);
			g.drawRect(this.getX() - this.getRadius() - 2, this.getY() - 2, 4, 4);
			g.drawRect(this.getX() + this.getRadius() - 2, this.getY() - 2, 4, 4);
			g.drawRect(this.getX() - 2, (int) (this.getY() - (this.getRadius()/2*Math.sqrt(3)) - 2), 4, 4);
			g.drawRect(this.getX() - 2, (int) (this.getY() + (this.getRadius()/2*Math.sqrt(3)) - 2), 4, 4);
			g.setColor(Color.BLACK);
		}
	}
	public String toString() {
		return "Hexagon;" + this.getX() + ";" + this.getY() + ";" + this.getRadius()+";"+this.getColor()+";"+this.getInnerColor();
		
	}
	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public boolean contains(Point p) {
		return false;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
	public HexagonAdapter clone(HexagonAdapter hexagon) {
		hexagon.setX(this.getX());
		hexagon.setY(this.getY());
		hexagon.setRadius(this.getRadius());
		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());
		return hexagon;
	}

}
