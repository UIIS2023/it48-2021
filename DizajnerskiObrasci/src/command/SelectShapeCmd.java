package command;

import geometry.Shape;
import mvc.DrawingController;

public class SelectShapeCmd implements Command {

	DrawingController controller;
	Shape shape;
	public String cmdTxt;
	
	public SelectShapeCmd(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape = shape;
		cmdTxt = "SELECTED;" + shape.toString();
	}
	@Override
	public void execute() {
		controller.select(shape);
	}

	@Override
	public void unexecute() {
		controller.select(shape);
	}
	
	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
