package command;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	
	private DrawingModel model;
	private DrawingController controller;
	private Shape shape;
	private int shapePosition;
	public String cmdTxt;
	public RemoveShapeCmd(Shape shape, DrawingModel model, DrawingController controller) {
		this.shape = shape;
		this.model = model;
		this.controller = controller;
		cmdTxt = "DELETED;" + shape.toString();
	}

	@Override
	public void execute() {
		shapePosition = model.getShapes().lastIndexOf(shape);
		if(shapePosition != -1) {
			System.out.println("novo" + shapePosition);

			model.removeShape(shape);
			controller.selectedShapes.remove(shape);
		}
		
	}

	@Override
	public void unexecute() {
		if(shapePosition != -1) {
			model.getShapes().add(shapePosition, shape);
			controller.selectedShapes.add(shape);
			System.out.println("dodava ga na" + shapePosition);
		}
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
