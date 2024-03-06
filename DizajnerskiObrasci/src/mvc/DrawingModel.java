package mvc;

import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel {

	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void addShape(Shape s) {
		shapes.add(s);
	}
	
	public void removeShape(Shape s) {
		shapes.remove(s);
		
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	
}
