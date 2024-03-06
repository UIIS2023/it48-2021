package command;

import mvc.DrawingController;



public class SelectCmd implements Command {
	
	DrawingController controller;
	int x;
	int y;
	public String cmdTxt;
	
	public SelectCmd(DrawingController controller, int x, int y) {
		this.controller = controller;
		this.x = x;
		this.y = y;
//		cmdTxt = "SELECTED;" + shape.toString()+'\n';
	}

	@Override
	public void execute() {
		controller.select2(x, y);
	}

	@Override
	public void unexecute() {
		controller.select2(x, y);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
