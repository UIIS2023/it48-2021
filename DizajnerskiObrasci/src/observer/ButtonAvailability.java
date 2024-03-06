package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ButtonAvailability {
	
	private boolean selectButton;
	private boolean editButton;
	private boolean deleteButton;
	
	private boolean undoButton;
	private boolean redoButton;
	
	private boolean toBackButtons;
	private boolean toFrontButtons;
	
	private PropertyChangeSupport propertyChangeSupport;
	
	public ButtonAvailability() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void setSelectButton(boolean selectButton) {
		propertyChangeSupport.firePropertyChange("select", this.selectButton, selectButton);
		this.selectButton = selectButton;
	}

	public void setEditButton(boolean editButton) {
		propertyChangeSupport.firePropertyChange("edit", this.editButton, editButton);
		this.editButton = editButton;
	}

	public void setDeleteButton(boolean deleteButton) {
		propertyChangeSupport.firePropertyChange("delete", this.deleteButton, deleteButton);
		this.deleteButton = deleteButton;
	}
	
	public void setUndoButton(boolean undoButton) {
		propertyChangeSupport.firePropertyChange("undo", this.undoButton, undoButton);
		this.undoButton = undoButton;
	}
	public void setRedoButton(boolean redoButton) {
		propertyChangeSupport.firePropertyChange("redo", this.redoButton, redoButton);
		this.redoButton = redoButton;
	}
	
	public void setToBackButtons(boolean toBackButtons) {
		propertyChangeSupport.firePropertyChange("toback", this.toBackButtons, toBackButtons);
		this.toBackButtons = toBackButtons;
	}
	public void setToFrontButtons(boolean toFrontButtons) {
		propertyChangeSupport.firePropertyChange("tofront", this.toFrontButtons, toFrontButtons);
		this.toFrontButtons = toFrontButtons;
	}
	
	public void addListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public void removeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	
	
}
