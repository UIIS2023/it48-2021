package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command {

	Circle circle;
	Circle newState;
	Circle temp = new Circle();
	String cmdTxt;
	
	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle = circle;
		this.newState = newState;
		cmdTxt = "EDITED;" + circle.toString()+";"+newState.toString();
	}

	@Override
	public void execute() {
		temp = circle.clone(temp);
		circle = newState.clone(circle);
	}

	@Override
	public void unexecute() {
		circle = temp.clone(circle);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
