package geometry;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
public class Donut extends Circle implements Serializable{
	private int innerRadius;
	private Color color;
	private Color innerColor;
	
	public Donut() {
		super();
	}
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);
	}
	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
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
		return super.area() - this.innerRadius*this.innerRadius*Math.PI;
	}
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut d = (Donut)obj;
			if(super.equals(d) && this.innerRadius == d.innerRadius)
				return true;
			else return false;
		}
		return false;
	}
	
	public String toString() {
		
		return "Donut;"+super.toString2() + ";" + this.innerRadius;
	}
	
	public boolean contains(int x, int y) {
		double d= this.getCenter().distance(x, y);
		if(d> this.innerRadius && super.contains(x, y))
			return true;
		else return false;
	}
	public boolean contains(Point p) {
		double d = this.getCenter().distance(p.getX(), p.getY());
		if(d > this.innerRadius && super.contains(p))
			return true;
		else return false;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Ellipse2D outerCircle = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		Ellipse2D innerCircle = new Ellipse2D.Double(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius,
				2 * innerRadius);
		
		Area outerArea = new Area(outerCircle);
        Area innerArea = new Area(innerCircle);
        
        outerArea.subtract(innerArea);
        
        g2d.setColor(getInnerColor());
        g2d.fill(outerArea);
        
        g2d.setColor(getColor());
        g2d.drawOval(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
        g2d.drawOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius,
				2 * innerRadius);
        

        g2d.dispose();

			if (isSelected()) {
				g.setColor(Color.BLUE);
				g.drawRect(getCenter().getX() - getRadius() - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() + getRadius() - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() - getRadius() - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() + getRadius() - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
				g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
				g.setColor(Color.black);
			}

		
//		g.setColor(getInnerColor());
//		g.fillOval(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
//		
//		g.setColor(color);
//		g.fillOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);  
//		
//		g.setColor(super.getColor());
//		g.drawOval(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
//		g.drawOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);
//		  
//		
//		
//		if(this.isSelected()) {
//			g.setColor(Color.BLUE);
//			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - this.innerRadius - 2, this.getCenter().getY() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() + this.innerRadius - 2, this.getCenter().getY() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - this.innerRadius - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() + this.innerRadius - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - this.getRadius() - 2, this.getCenter().getY() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() + this.getRadius() - 2, this.getCenter().getY() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - this.getRadius() - 2, 4, 4);
//			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() + this.getRadius() - 2, 4, 4);
//			g.setColor(Color.BLACK);
//		}
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int)(this.area() - ((Donut)o).area());
		}
		return 0;
	}
	
	public Donut clone(Donut donut) {
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		donut.setInnerRadius(this.getInnerRadius());
		try {
			donut.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		return donut;
	}
	
	
	

}
