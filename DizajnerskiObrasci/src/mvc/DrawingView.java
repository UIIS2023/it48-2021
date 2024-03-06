package mvc;

//import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel{
	
	DrawingModel model = new DrawingModel();
	int n;
//	private Color clr;
	
	
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator <Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
			//Shape sh = it.next();
			//sh.draw(g);
		}		
	}
	

}
