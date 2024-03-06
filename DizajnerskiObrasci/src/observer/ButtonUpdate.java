 package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class ButtonUpdate implements PropertyChangeListener{
	private boolean selectButton;
	private boolean editButton;
	private boolean deleteButton;
	
	private boolean undoButton;
	private boolean redoButton;
	
	private boolean toFrontButtons;
	private boolean toBackButtons;
	DrawingFrame frame;
	
	public ButtonUpdate(DrawingFrame frame) {
		this.frame = frame;
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("select")) {
			this.selectButton = (boolean)evt.getNewValue();
			switchSelectButton(selectButton);
		}
		else if(evt.getPropertyName().equals("edit")) {
			this.editButton = (boolean)evt.getNewValue();
			switchEditButton(editButton);
		}
		else if(evt.getPropertyName().equals("delete")) {
			this.deleteButton= (boolean)evt.getNewValue();
			switchDeleteButton(deleteButton);
		}
		else if(evt.getPropertyName().equals("undo")) {
			this.undoButton = (boolean)evt.getNewValue();
			switchUndoButton(undoButton);
		}
		else if(evt.getPropertyName().equals("redo")) {
			this.redoButton = (boolean)evt.getNewValue();
			switchRedoButton(redoButton);
		}
		else if(evt.getPropertyName().equals("toback")) {
			this.toBackButtons = (boolean)evt.getNewValue();
			switchToBackButtons(toBackButtons);
		}
		else if(evt.getPropertyName().equals("tofront")) {
			this.toFrontButtons = (boolean)evt.getNewValue();
			switchToFrontButtons(toFrontButtons);
		}
		
	}
	
	public void switchSelectButton(boolean button) {
		frame.tglbtnSelect.setEnabled(button);
	}
	public void switchEditButton(boolean button) {
		frame.btnEdit.setEnabled(button);
	}
	public void switchDeleteButton(boolean button) {
		frame.btnDelete.setEnabled(button);
	}
	public void switchUndoButton(boolean button) {
		frame.btnUndo.setEnabled(button);
	}
	public void switchRedoButton(boolean button) {
		frame.btnRedo.setEnabled(button);
	}
	public void switchToBackButtons(boolean button) {
		frame.btnBringToBack.setEnabled(button);
		frame.btnToBack.setEnabled(button);
	}
	public void switchToFrontButtons(boolean button) {
		frame.btnBringToFront.setEnabled(button);
		frame.btnToFront.setEnabled(button);
	}

}
