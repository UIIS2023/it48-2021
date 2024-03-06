package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

	DrawingModel model;
	Shape shape;
	int position;
	int b;
	public String cmdTxt;
	public BringToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		cmdTxt = "BRINGTOFRONT;" + shape.toString();
	}

	@Override
	public void execute() {
		int a = model.getShapes().lastIndexOf(shape);
		b = model.getShapes().size()-1 - a;
		for(int i = b, j = model.getShapes().lastIndexOf(shape);i>0;i--, j++) {
			Collections.swap(model.getShapes(), j, j+1);
		}
	}

	@Override
	public void unexecute() {
		//int z = model.getShapes().lastIndexOf(shape);
		for(int a = b, j = model.getShapes().size()-1;a>0;a--, j--) {
			Collections.swap(model.getShapes(), j, j-1);
		}
	}
	
	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}

}
