package command;

import geometry.Point;
import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {

	Rectangle rectangle;
	Rectangle newState;
	Rectangle temp = new Rectangle(new Point(), 0, 0);
	String cmdTxt;
	
	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle = rectangle;
		this.newState = newState;
		cmdTxt = "EDITED;" + rectangle.toString()+";"+newState.toString();
	}

	@Override
	public void execute() {
		temp = rectangle.clone(temp);
		rectangle = newState.clone(rectangle);
	}

	@Override
	public void unexecute() {
		rectangle = temp.clone(rectangle);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
