package command;


import geometry.Line;
import geometry.Point;

public class UpdateLineCmd implements Command {

	Line line;
	Line newState;
	Line temp = new Line(new Point(), new Point());
	Line proba;
	String cmdTxt;
	
	public UpdateLineCmd(Line line, Line newState) {
		this.line = line;
		this.newState = newState;
		cmdTxt = "EDITED;" + line.toString()+";"+newState.toString();
	}

	@Override
	public void execute() {
		temp = line.clone(temp);
		line = newState.clone(line);
	}

	@Override
	public void unexecute() {
		line = temp.clone(line);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
