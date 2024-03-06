package command;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RemoveShapesCmd implements Command {
	
	private List<Command> shapes = new ArrayList<Command>();
	List<String>commands = new ArrayList<String>();
	String cmdTxt = "";
	public RemoveShapesCmd(List<Command> shapes) {
		this.shapes = shapes;
//		for(Command command : shapes) {
//			cmdTxt += (command.getCmdTxt()+'\n');
//		}
		ListIterator<Command> iterator = shapes.listIterator(shapes.size());
		while (iterator.hasPrevious()) {
			cmdTxt += (iterator.previous().getCmdTxt()+'\n');
//		    Command command = iterator.previous();
//		    command.unexecute();
		}
	}
	@Override
	public void execute() {
		for(Command command : shapes) {
			command.execute();
		}

	}

	@Override
	public void unexecute() {
		ListIterator<Command> iterator = shapes.listIterator(shapes.size());
		while (iterator.hasPrevious()) {
		    Command command = iterator.previous();
		    command.unexecute();
		}
	}
	
	@Override
	public String getCmdTxt() {
		
		return cmdTxt;
	}
}
