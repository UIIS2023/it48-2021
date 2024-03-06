package command;

import geometry.Shape;
import mvc.DrawingController;

public class DeselectShapeCmd implements Command {

	DrawingController controller;
	Shape shape;
	public String cmdTxt;
	
	public DeselectShapeCmd(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape = shape;
		cmdTxt = "DESELECTED;" + shape.toString();
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
