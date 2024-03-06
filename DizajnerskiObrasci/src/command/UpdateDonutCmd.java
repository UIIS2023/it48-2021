package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {

	Donut donut;
	Donut newState;
	Donut temp = new Donut();
	String cmdTxt;
	
	public UpdateDonutCmd(Donut donut, Donut newState) {
		this.donut = donut;
		this.newState = newState;
		cmdTxt = "EDITED;" + donut.toString()+";"+newState.toString();
	}

	@Override
	public void execute() {
		temp = donut.clone(temp);
		donut = newState.clone(donut);
	}

	@Override
	public void unexecute(){
		donut = temp.clone(donut);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
