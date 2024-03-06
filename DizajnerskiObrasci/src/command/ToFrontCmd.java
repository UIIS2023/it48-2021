package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	DrawingFrame frame;
	DrawingModel model;
	Shape shape;
	String cmdTxt;

	public ToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		cmdTxt = "TOFRONT;" + shape.toString();
	}

	@Override
	public void execute() {
		int z = model.getShapes().lastIndexOf(shape);
		Collections.swap(model.getShapes(), z+1, z);

	}

	@Override
	public void unexecute() {
		int z = model.getShapes().lastIndexOf(shape);
		Collections.swap(model.getShapes(), z-1, z);
	}
	
	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
