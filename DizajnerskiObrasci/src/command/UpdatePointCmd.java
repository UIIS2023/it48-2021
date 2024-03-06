package command;

import geometry.Point;

public class UpdatePointCmd implements Command {
	
	private Point point;
	private Point newState;
	private Point temp = new Point();
	public String cmdTxt;
	
	public UpdatePointCmd(Point point, Point newState) {
		this.point = point;
		this.newState = newState;
		cmdTxt = "EDITED;" + point.toString()+";"+newState.toString();
	}

	@Override
	public void execute() {
		temp = point.clone(temp);
		point = newState.clone(point);
		System.out.println(temp.toString());
		System.out.println(point.toString());
		System.out.println(newState.toString());
	}

	@Override
	public void unexecute() {
		point = temp.clone(point);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
