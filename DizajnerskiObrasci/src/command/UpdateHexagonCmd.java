package command;

import geometry.HexagonAdapter;
import hexagon.Hexagon;

public class UpdateHexagonCmd implements Command {

	HexagonAdapter hexagonAdapter;
	HexagonAdapter newState;
	HexagonAdapter temp = new HexagonAdapter(new Hexagon(0,0,0));
	String cmdTxt;
	
	public UpdateHexagonCmd(HexagonAdapter hexagonAdapter, HexagonAdapter newState) {
		this.hexagonAdapter = hexagonAdapter;
		this.newState = newState;
		cmdTxt = "EDITED;" + hexagonAdapter.toString()+";"+newState.toString();
	}

	@Override
	public void execute() {
		temp = hexagonAdapter.clone(temp);
		hexagonAdapter = newState.clone(hexagonAdapter);

	}

	@Override
	public void unexecute() {
		hexagonAdapter = temp.clone(hexagonAdapter);
	}

	@Override
	public String getCmdTxt() {
		return cmdTxt;
	}
}
