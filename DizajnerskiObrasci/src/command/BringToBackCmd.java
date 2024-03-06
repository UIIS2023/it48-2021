package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

	DrawingModel model;
	Shape shape;
	int position;
	int b;
	public String cmdTxt;
	public BringToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		cmdTxt = "BRINGTOBACK;" + shape.toString();
	}

	@Override
	public void execute() {
		b = model.getShapes().lastIndexOf(shape);
		for(int i = b;i>0;i--) {
			Collections.swap(model.getShapes(), i, i-1);
		}
	}

	@Override
	public void unexecute() {
		//int z = model.getShapes().lastIndexOf(shape);
		for(int a = b, j = 0;a > 0;a--, j++) {
			Collections.swap(model.getShapes(), j, j+1);
		}
	}
	
	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}

}
