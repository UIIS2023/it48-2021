package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectShapeCmd;
import command.RemoveShapeCmd; 
import command.RemoveShapesCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import gui.DialogCircle;
import gui.DialogDonut;
import gui.DialogHexagon;
import gui.DialogLine;
import gui.DialogPoint;
import gui.DialogRectangle;
import hexagon.Hexagon;
import observer.ButtonAvailability;
import observer.ButtonUpdate;
import strategy.SaveManager;
import strategy.SaveToBin;
import strategy.SaveToTxt;

public class DrawingController {

	DrawingModel model;
	DrawingFrame frame;

	ButtonAvailability buttonAvailability = new ButtonAvailability();
	ButtonUpdate buttonUpdate;
	
	SaveManager saveToTxt;
	SaveManager saveToBin;
	
	SaveManager loadBin;
	SaveManager loadTxt;
	
	
	
	List<Command> undoList = new ArrayList<Command>();
	List<Command> redoList = new ArrayList<Command>();
	
	public DrawingController() {
		
	}
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.buttonUpdate = new ButtonUpdate(frame);
		buttonAvailability.addListener(buttonUpdate);
	}
	
	
	Point p1;
	public void mouseClicked(MouseEvent e) {
		if(frame.tglbtnPoint.isSelected()) {

			Color color = frame.btnColor.getBackground();
			Point p = new Point(e.getX(), e.getY(), color);
			
			AddShapeCmd addShapeCmd = new AddShapeCmd(p, model);
			addShapeCmd.execute();
			undoList.add(addShapeCmd);
			redoList.clear();
//			frame.textAreaCommands.append("ADDED;"+p.toString()+'\n');
			frame.textAreaCommands.append(addShapeCmd.cmdTxt+'\n');
			frame.repaint();
			selectButtonAvailability();
			undoButtonAvailability();
			redoButtonAvailability();
			toBackButtonsAvailability();
			toFrontButtonsAvailability();
		}
		else if(frame.tglbtnLine.isSelected()){
	
			if(p1 != null) {
				Color color = frame.btnColor.getBackground();
				Point p2 = new Point(e.getX(), e.getY());
				Line l = new Line(p1,p2, color);
				
				p1 = null;
				
				AddShapeCmd addShapeCmd = new AddShapeCmd(l, model);
				addShapeCmd.execute();
				undoList.add(addShapeCmd);
				redoList.clear();
				frame.repaint();
				frame.textAreaCommands.append(addShapeCmd.cmdTxt+'\n');
				selectButtonAvailability();
				undoButtonAvailability();
				redoButtonAvailability();
				toBackButtonsAvailability();
				toFrontButtonsAvailability();
				
			}
			else {
				p1 = new Point(e.getX(), e.getY()); 
			}
			
			
		}
		else if(frame.tglbtnRectangle.isSelected()) {
	
			Color color = frame.btnColor.getBackground();
			Color innerColor = frame.btnInnerColor.getBackground();
			DialogRectangle dlgRectangle = new DialogRectangle();
			
			dlgRectangle.setLocationRelativeTo(null);
			dlgRectangle.getTxtXCoordinate().setText(Integer.toString(e.getX()));
			dlgRectangle.getTxtYCoordinate().setText(Integer.toString(e.getY()));
			dlgRectangle.getBtnColor().setBackground(color);
			dlgRectangle.getBtnInnerColor().setBackground(innerColor);
			dlgRectangle.setVisible(true);
			if(dlgRectangle.getRectangle()!=null) {
				AddShapeCmd addShapeCmd = new AddShapeCmd(dlgRectangle.getRectangle(),model);
				addShapeCmd.execute();
				undoList.add(addShapeCmd);
				redoList.clear();
				frame.repaint();
				frame.textAreaCommands.append(addShapeCmd.cmdTxt+'\n');
				selectButtonAvailability();
				undoButtonAvailability();
				redoButtonAvailability();
				toBackButtonsAvailability();
				toFrontButtonsAvailability();
			}
//			
		}
		else if(frame.tglbtnCircle.isSelected()) {
	
			Color color = frame.btnColor.getBackground();
			Color innerColor = frame.btnInnerColor.getBackground();
			DialogCircle dlgCircle = new DialogCircle();
			dlgCircle.setLocationRelativeTo(null);
			dlgCircle.getTxtCenterX().setText(Integer.toString(e.getX()));
			dlgCircle.getTxtCenterY().setText(Integer.toString(e.getY()));
			dlgCircle.getBtnColor().setBackground(color);
			dlgCircle.getBtnInnerColor().setBackground(innerColor);
			dlgCircle.getTxtRadius().setFocusable(true);
			dlgCircle.getTxtRadius().requestFocus();
			dlgCircle.setVisible(true);
			if(dlgCircle.getCircle()!=null) {
				AddShapeCmd addShapeCmd = new AddShapeCmd(dlgCircle.getCircle(), model);
				addShapeCmd.execute();
				undoList.add(addShapeCmd);
				redoList.clear();
				frame.repaint();
				frame.textAreaCommands.append(addShapeCmd.cmdTxt+'\n');
				selectButtonAvailability();
				undoButtonAvailability();
				redoButtonAvailability();
				toBackButtonsAvailability();
				toFrontButtonsAvailability();
			}
		}
		else if(frame.tglbtnDonut.isSelected()) {
			
			Color color = frame.btnColor.getBackground();
			Color innerColor = frame.btnInnerColor.getBackground();
			DialogDonut dlgDonut = new DialogDonut();
			dlgDonut.setLocationRelativeTo(null);
			dlgDonut.getTxtCenterX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtCenterY().setText(Integer.toString(e.getY()));
			dlgDonut.getBtnColor().setBackground(color);
			dlgDonut.getBtnInnerColor().setBackground(innerColor);
			dlgDonut.setVisible(true);
			if(dlgDonut.getDonut()!=null) {
				AddShapeCmd addShapeCmd = new AddShapeCmd(dlgDonut.getDonut(), model);
				addShapeCmd.execute();
				undoList.add(addShapeCmd);
				redoList.clear();
				frame.repaint();
				frame.textAreaCommands.append(addShapeCmd.cmdTxt+'\n');
				selectButtonAvailability();
				undoButtonAvailability();
				redoButtonAvailability();
				toBackButtonsAvailability();
				toFrontButtonsAvailability();
			}
		}
		else if(frame.tglbtnHexagon.isSelected()) {
			Color color = frame.btnColor.getBackground();
			Color innerColor = frame.btnInnerColor.getBackground();
			DialogHexagon dlgHexagon = new DialogHexagon();
			dlgHexagon.setLocationRelativeTo(null);
			dlgHexagon.getTxtCenterX().setText(Integer.toString(e.getX()));
			dlgHexagon.getTxtCenterY().setText(Integer.toString(e.getY()));
			dlgHexagon.getBtnColor().setBackground(color);
			dlgHexagon.getBtnInnerColor().setBackground(innerColor);
			dlgHexagon.setVisible(true);
			if(dlgHexagon.getHexagon()!=null) {
				AddShapeCmd addShapeCmd = new AddShapeCmd(dlgHexagon.getHexagon(), model);
				addShapeCmd.execute();
				undoList.add(addShapeCmd);
				redoList.clear();
				frame.repaint();
				frame.textAreaCommands.append(addShapeCmd.cmdTxt+'\n');
				selectButtonAvailability();
				undoButtonAvailability();
				redoButtonAvailability();
				toBackButtonsAvailability();
				toFrontButtonsAvailability();
				
			}
		}
		else if(frame.tglbtnSelect.isSelected()) {
			int i = selectedShapes.size();
//			SelectCmd selectCmd = new SelectCmd(this, e.getX(), e.getY());
//			selectCmd.execute();
			select2(e.getX(), e.getY());
			if(i != selectedShapes.size()) {
//				undoList.add(selectCmd);
				undoButtonAvailability();
				redoButtonAvailability();
				editButtonAvailability();
				deleteButtonAvailability();
				toBackButtonsAvailability();
				toFrontButtonsAvailability();
			}
			
//			select(e.getX(), e.getY());
//			editAndDeleteButtons();
		}
	
		

	}
	public void editClicked() {
		if(isShapesListEmpty()) {
			JOptionPane.showMessageDialog(null, "No shapes drawn", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(getSelectedShape() == null) {
			JOptionPane.showMessageDialog(null, "No shapes selected", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if(getSelectedShape() instanceof Point) {
				Point p = (Point) getSelectedShape();
				DialogPoint pointDlg = new DialogPoint();
				pointDlg.setLocationRelativeTo(null);
				pointDlg.getTxtXCoordinate().setText(Integer.toString(p.getX()));
				pointDlg.getTxtYCoordinate().setText(Integer.toString(p.getY()));
				pointDlg.getBtnColor().setBackground(p.getColor());
				pointDlg.setVisible(true);
				if(pointDlg.getPoint() != null) {
					Point pNew = pointDlg.getPoint();
					UpdatePointCmd updatePointCmd = new UpdatePointCmd(p, pNew);
					updatePointCmd.execute();
					undoList.add(updatePointCmd);
					redoList.clear();
					undoButtonAvailability();
					redoButtonAvailability();
					frame.repaint();	
					frame.textAreaCommands.append(updatePointCmd.getCmdTxt()+'\n');
				}
			}
		    else if(getSelectedShape() instanceof Rectangle) {
				Rectangle r = (Rectangle) getSelectedShape();
				DialogRectangle rectangleDlg = new DialogRectangle();
				rectangleDlg.setLocationRelativeTo(null);
				rectangleDlg.getTxtXCoordinate().setText(Integer.toString(r.getUpperLeftPoint().getX()));
				rectangleDlg.getTxtYCoordinate().setText(Integer.toString(r.getUpperLeftPoint().getY()));	
				rectangleDlg.getTxtHeight().setText(Integer.toString(r.getHeight()));
				rectangleDlg.getTxtWidth().setText(Integer.toString(r.getWidth()));
				rectangleDlg.getBtnColor().setBackground(r.getColor());
				rectangleDlg.getBtnInnerColor().setBackground(r.getInnerColor());
				rectangleDlg.setVisible(true);
				if(rectangleDlg.getRectangle() != null) {
					
					Rectangle rNew = rectangleDlg.getRectangle();
					UpdateRectangleCmd updateRectangleCmd = new UpdateRectangleCmd(r, rNew);
					updateRectangleCmd.execute();
					undoList.add(updateRectangleCmd);
					redoList.clear();
					undoButtonAvailability();
					redoButtonAvailability();
					frame.repaint();		
					frame.textAreaCommands.append(updateRectangleCmd.getCmdTxt()+'\n');
				}
			}
			else if (getSelectedShape() instanceof Line) {
				Line l = (Line) getSelectedShape();
				DialogLine lineDlg = new DialogLine();
				lineDlg.setLocationRelativeTo(null);
				lineDlg.getTxtStartX().setText(Integer.toString(l.getStartPoint().getX()));
				lineDlg.getTxtStartY().setText(Integer.toString(l.getStartPoint().getY()));					
                lineDlg.getTxtEndX().setText(Integer.toString(l.getEndPoint().getX()));					
                lineDlg.getTxtEndY().setText(Integer.toString(l.getEndPoint().getY()));
                lineDlg.getBtnPickAColor().setBackground(l.getColor());
                lineDlg.setVisible(true);
                if(lineDlg.getLine() != null) {
                	Line lNew = lineDlg.getLine();
                	UpdateLineCmd updateLineCmd = new UpdateLineCmd(l, lNew);
                	updateLineCmd.execute();
                	undoList.add(updateLineCmd);
                	redoList.clear();
                	undoButtonAvailability();
    				redoButtonAvailability();
    				frame.textAreaCommands.append(updateLineCmd.getCmdTxt()+'\n');
                	frame.repaint();
                	
                }
			}
			else if(getSelectedShape() instanceof Donut) {
				Donut d = (Donut) getSelectedShape();
				DialogDonut donutDlg = new DialogDonut();
				donutDlg.setLocationRelativeTo(null);
				donutDlg.getTxtCenterX().setText(Integer.toString(d.getCenter().getX()));
				donutDlg.getTxtCenterY().setText(Integer.toString(d.getCenter().getY()));
				donutDlg.getTxtRadius().setText(Integer.toString(d.getRadius()));
				donutDlg.getTxtInnerRadius().setText(Integer.toString(d.getInnerRadius()));
				donutDlg.getBtnInnerColor().setBackground(d.getInnerColor());
				donutDlg.getBtnColor().setBackground(d.getColor());
				donutDlg.setVisible(true);
				if(donutDlg.getDonut() != null) {
					Donut dNew = donutDlg.getDonut();
					UpdateDonutCmd updateDonutCmd = new UpdateDonutCmd(d, dNew);
					updateDonutCmd.execute();
					undoList.add(updateDonutCmd);
					redoList.clear();
					undoButtonAvailability();
					redoButtonAvailability();
					frame.repaint();
					frame.textAreaCommands.append(updateDonutCmd.getCmdTxt()+'\n');
				}
			}
			else if(getSelectedShape() instanceof Circle) {
				Circle c = (Circle) getSelectedShape();
				DialogCircle circleDlg = new DialogCircle();
				circleDlg.setLocationRelativeTo(null);
				circleDlg.getTxtCenterX().setText(Integer.toString(c.getCenter().getX()));
				circleDlg.getTxtCenterY().setText(Integer.toString(c.getCenter().getY()));
				circleDlg.getTxtRadius().setText(Integer.toString(c.getRadius()));
				circleDlg.getBtnInnerColor().setBackground(c.getInnerColor());
				circleDlg.getBtnColor().setBackground(c.getColor());
				circleDlg.setVisible(true);
				if(circleDlg.getCircle() != null) {
					Circle cNew = circleDlg.getCircle();
					UpdateCircleCmd updateCircleCmd = new UpdateCircleCmd(c, cNew);
					updateCircleCmd.execute();
					undoList.add(updateCircleCmd);
					redoList.clear();
					undoButtonAvailability();
					redoButtonAvailability();
					frame.repaint();
					frame.textAreaCommands.append(updateCircleCmd.getCmdTxt()+'\n');
				}						
			}
			else if(getSelectedShape() instanceof HexagonAdapter) {
				HexagonAdapter h = (HexagonAdapter) getSelectedShape();
				DialogHexagon hexagonDlg = new DialogHexagon();
				hexagonDlg.setLocationRelativeTo(null);
				hexagonDlg.getTxtCenterX().setText(Integer.toString(h.getX()));
				hexagonDlg.getTxtCenterY().setText(Integer.toString(h.getY()));
				hexagonDlg.getTxtRadius().setText(Integer.toString(h.getRadius()));
				hexagonDlg.getBtnInnerColor().setBackground(h.getInnerColor());
				hexagonDlg.getBtnColor().setBackground(h.getColor());
				hexagonDlg.setVisible(true);
				if(hexagonDlg.getHexagon() != null) {
					HexagonAdapter hNew = hexagonDlg.getHexagon();
					UpdateHexagonCmd updateHexagonAdapter = new UpdateHexagonCmd(h, hNew);
					updateHexagonAdapter.execute();
					undoList.add(updateHexagonAdapter);
					redoList.clear();
					undoButtonAvailability();
					redoButtonAvailability();
					frame.repaint();
					frame.textAreaCommands.append(updateHexagonAdapter.getCmdTxt()+'\n');
				}						
			}
		}
	}
	List<Command> deletedShapes = new ArrayList<Command>();
	public void deleteClicked() {
		if(isShapesListEmpty()) {
			JOptionPane.showMessageDialog(null, "No shapes drawn", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(selectedShapes.isEmpty())
			JOptionPane.showMessageDialog(null, "No shapes selected", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			int option = JOptionPane.showConfirmDialog(null, "Do you want to delete selected shape?");
			if(option == JOptionPane.YES_OPTION) {
				for(int i = 0;i<selectedShapes.size();i++) {
					RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(selectedShapes.get(i), model, this);
					frame.textAreaCommands.append(removeShapeCmd.getCmdTxt()+'\n');
//					removeShapeCmd.execute();
//					undoList.add(removeShapeCmd);
					deletedShapes.add(removeShapeCmd);
					
				}
//				RemoveShapesCmd removeShapeCmd = new RemoveShapesCmd(selectedShapes,model);
//				frame.textAreaCommands.append("DELETED:"+selectedShapes.get(0).toString()+'\n');
//				removeShapeCmd.execute();
				RemoveShapesCmd removeShapesCmd = new RemoveShapesCmd(deletedShapes);
				removeShapesCmd.execute();
				undoList.add(removeShapesCmd);
				
				redoList.clear();
//				selectedShapes.clear();
				frame.repaint();
				System.out.println(model.getShapes().size());
				undoButtonAvailability();
				redoButtonAvailability();
				selectButtonAvailability();
				editButtonAvailability();
				deleteButtonAvailability();
			}
		}
	}

	public List<Shape>selectedShapes = new ArrayList<Shape>();
	public void select(Shape shape) {
		if(!shape.isSelected()) {
			shape.setSelected(true);
			selectedShapes.add(shape);
//			frame.textAreaCommands.append("SELECTED;"+shape.toString()+'\n');
			frame.repaint();
		}else {
			shape.setSelected(false);
			selectedShapes.remove(shape);
//			frame.textAreaCommands.append("DESELECTED;"+shape.toString()+'\n');
			frame.repaint();
		}
	}
	public void select2(int x, int y) {
		int a = -1;
		for(int n = 0;n < model.getShapes().size();n ++) {
			if(model.getShapes().get(n).contains(x, y)) {
				a = n;
			}
		}
		if(a != -1) {
			
			if(!model.getShapes().get(a).isSelected()) {
				SelectShapeCmd selectShapeCmd = new SelectShapeCmd(this, model.getShapes().get(a));
				selectShapeCmd.execute();
				undoList.add(selectShapeCmd);
				frame.textAreaCommands.append(selectShapeCmd.getCmdTxt()+'\n');
//				model.getShapes().get(a).setSelected(true);
//				selectedShapes.add(model.getShapes().get(a));
//				frame.textAreaCommands.append("SELECTED;"+model.getShapes().get(a).toString()+'\n');
//				frame.repaint();
			}else {
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(this, model.getShapes().get(a));
				deselectShapeCmd.execute();
				undoList.add(deselectShapeCmd);
				frame.textAreaCommands.append(deselectShapeCmd.getCmdTxt()+'\n');
				
//				model.getShapes().get(a).setSelected(false);
//				selectedShapes.remove(model.getShapes().get(a));
//				frame.textAreaCommands.append("DESELECTED;"+model.getShapes().get(a).toString()+'\n');
//				frame.repaint();
			}
			
		}
		
	}

	public Shape getSelectedShape() {
		return selectedShapes.get(0);
	}
	public boolean isShapesListEmpty() {
		return model.getShapes().isEmpty();
	}
	
	public void undoClicked() {
		int i = undoList.size();
		if(i <= 0) {
			JOptionPane.showMessageDialog(null, "No shapes drawn, cannot undo", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			Command command = undoList.remove(i-1);
//			frame.textAreaCommands.append("UNDO;" + command.getCmdTxt());
			redoList.add(command);
			
			command.unexecute();
			frame.repaint();
			if(command instanceof RemoveShapesCmd) {
				String cmdTxt = command.getCmdTxt().replaceAll("DELETED", "UNDO;DELETED");
				frame.textAreaCommands.append(cmdTxt);
			}
			else {
				frame.textAreaCommands.append("UNDO;"+ command.getCmdTxt() + '\n');
			}
			
			redoButtonAvailability();
			undoButtonAvailability();
			toBackButtonsAvailability();
			toFrontButtonsAvailability();
			selectButtonAvailability();
			editButtonAvailability();
			deleteButtonAvailability();
			System.out.println(selectedShapes.size());
		}
	}
	public void redoClicked() {
		int i = redoList.size();
		if(i <= 0) {
			JOptionPane.showMessageDialog(null, "No shapes drawn, cannot redo", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			Command command = redoList.remove(i-1);
			undoList.add(command);
			
			command.execute();
			frame.repaint();
			frame.textAreaCommands.append("REDO;" + command.getCmdTxt()+'\n');
			undoButtonAvailability();
			redoButtonAvailability();
			toBackButtonsAvailability();
			toFrontButtonsAvailability();
			selectButtonAvailability();
			editButtonAvailability();
			deleteButtonAvailability();
		}
	}
	public void bringToBackClicked() {
		BringToBackCmd bringToBackCmd = new BringToBackCmd(model, getSelectedShape());
		bringToBackCmd.execute();
		undoList.add(bringToBackCmd);
		toBackButtonsAvailability();
		toFrontButtonsAvailability();
		frame.textAreaCommands.append(bringToBackCmd.getCmdTxt()+'\n');
		frame.repaint();
	}
	public void toBackClicked() {
		ToBackCmd toBackCmd = new ToBackCmd(model, getSelectedShape());
		toBackCmd.execute();
		undoList.add(toBackCmd);
		toBackButtonsAvailability();
		toFrontButtonsAvailability();
		frame.textAreaCommands.append(toBackCmd.getCmdTxt()+'\n');
		frame.repaint();
	}
	public void toFrontClicked() {
		ToFrontCmd toFrontCmd = new ToFrontCmd(model, getSelectedShape());
		toFrontCmd.execute();
		undoList.add(toFrontCmd);
		toBackButtonsAvailability();
		toFrontButtonsAvailability();
		frame.textAreaCommands.append(toFrontCmd.getCmdTxt()+'\n');
		frame.repaint();
	}
	public void bringToFrontClicked() {
		BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, getSelectedShape());
		bringToFrontCmd.execute();
		undoList.add(bringToFrontCmd);
		toBackButtonsAvailability();
		toFrontButtonsAvailability();
		frame.textAreaCommands.append(bringToFrontCmd.getCmdTxt()+'\n');
		frame.repaint();
	}
	
//	-----------------------------------------------------------------
	
	public void selectButtonAvailability() {
		if (!model.shapes.isEmpty()) {
			buttonAvailability.setSelectButton(true);
		}
		else {
			buttonAvailability.setSelectButton(false);
		}
	}
	public void editButtonAvailability() {
		if(selectedShapes.size()== 1) {
			buttonAvailability.setEditButton(true);
		}
		else {
			buttonAvailability.setEditButton(false);
		}
	}
	public void deleteButtonAvailability() {
		if(selectedShapes.size()>0) {
			buttonAvailability.setDeleteButton(true);
		}
		else {
			buttonAvailability.setDeleteButton(false);
		}
	}
	public void undoButtonAvailability() {
		if(undoList.size()>0) {
			buttonAvailability.setUndoButton(true);
		}
		else {
			buttonAvailability.setUndoButton(false);
		}
	}
	public void redoButtonAvailability() {
		if(redoList.size()>0) {
			buttonAvailability.setRedoButton(true);
		}
		else {
			buttonAvailability.setRedoButton(false);
		}
	}
	public void toFrontButtonsAvailability() {
		if(selectedShapes.size()==1 && model.getShapes().lastIndexOf(getSelectedShape()) != model.getShapes().size()-1) {
			buttonAvailability.setToFrontButtons(true);
		}
		else {
			buttonAvailability.setToFrontButtons(false);
		}
	}
	public void toBackButtonsAvailability() {
		if(selectedShapes.size()==1 && model.getShapes().lastIndexOf(getSelectedShape()) != 0) {
			buttonAvailability.setToBackButtons(true);
		}
		else {
			buttonAvailability.setToBackButtons(false);
		}
	}
	//--------------------------------------------------------------------------------------
	public void saveClicked() {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
		fileChooser.setDialogTitle("Save as");
		fileChooser.setVisible(true);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
	    fileChooser.setFileFilter(filter);
	    
	    int userSelection = fileChooser.showSaveDialog(frame);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();
	        String savePath = fileToSave.getAbsolutePath();
	        
	        System.out.println(savePath);
	        saveToTxt = new SaveManager(new SaveToTxt(frame));
	        saveToTxt.save(savePath+".txt");
	        
	        saveToBin = new SaveManager(new SaveToBin(model));
	        saveToBin.save(savePath+".bin");
	    }
	};
	public void loadBinFile() {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
	    fileChooser.setDialogTitle("Open");
	    fileChooser.setVisible(true);

	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary Files", "bin");
	    fileChooser.setFileFilter(filter);

	    int userSelection = fileChooser.showOpenDialog(frame);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToOpen = fileChooser.getSelectedFile();
	        String filePath = fileToOpen.getAbsolutePath();
	        System.out.println(filePath);
	        model.getShapes().clear();
	        selectedShapes.clear();
	        undoList.clear();
	        redoList.clear();
	        frame.textAreaCommands.setText("");
	        loadBin = new SaveManager(new SaveToBin(model));
	        loadBin.load(filePath, model);
	        frame.repaint();
	        
	    }
	    selectButtonAvailability();
	    deleteButtonAvailability();
	    editButtonAvailability();
	    undoButtonAvailability();
	    redoButtonAvailability();
	    
	}
	public void loadTxtFile() {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
	    fileChooser.setDialogTitle("Open");
	    fileChooser.setVisible(true);

	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
	    fileChooser.setFileFilter(filter);

	    int userSelection = fileChooser.showOpenDialog(frame);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToOpen = fileChooser.getSelectedFile();
	        String filePath = fileToOpen.getAbsolutePath();
	        System.out.println(filePath);
	        loadTxt = new SaveManager(new SaveToTxt(frame));
	        String[] lines = loadTxt.load(filePath, model);
	        if(lines == null) {
	        	System.out.println("null je ");
	        }
	        else {
	        	txtFileReader(lines);
	        }
	        
	    }
	    selectButtonAvailability();
	}
	public void txtFileReader(String[] string) {
		String shape1[];
		int continueDrawing = 0;
		int i = 0;
		while(i<string.length && continueDrawing==0 ) {
			String line = string[i];
//--------------------------------------------------------------------------------------------------------------------
			if (line.startsWith("ADDED")) {
	    		shape1 = line.split(";");
	    		if(shape1[1].equals("Point")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Point("+shape1[2]+","+shape1[3]+","+shape1[4]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x = Integer.parseInt(shape1[2]);
	    				int y = Integer.parseInt(shape1[3]);
	    				Color color = getColorFromString(shape1[4]);
	    				Point p = new Point(x,y,color);
	    				AddShapeCmd addShapeCmd = new AddShapeCmd(p, model);
	    				addShapeCmd.execute();
	    				undoList.add(addShapeCmd);
	    				frame.textAreaCommands.append(addShapeCmd.getCmdTxt()+'\n');
	    				
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Line")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Line(("+shape1[2]+","+shape1[3]+"),("+shape1[4]+","+shape1[5]+"))?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int startX = Integer.parseInt(shape1[2]);
	    				int startY = Integer.parseInt(shape1[3]);
	    				int endX = Integer.parseInt(shape1[4]);
	    				int endY = Integer.parseInt(shape1[5]);
	    				Color color = getColorFromString(shape1[6]);
	    				Point startPoint = new Point(startX,startY);
	    				Point endPoint = new Point(endX, endY);
	    				Line l = new Line(startPoint, endPoint, color);
	    				AddShapeCmd addShapeCmd = new AddShapeCmd(l, model);
	    				addShapeCmd.execute();
	    				undoList.add(addShapeCmd);
	    				frame.textAreaCommands.append(addShapeCmd.getCmdTxt()+'\n');
	    			}
	    		}
	    		else if(shape1[1].equals("Rectangle")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Rectangle(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[5]+
	    					","+shape1[6]+","+shape1[7]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x = Integer.parseInt(shape1[2]);
	    				int y = Integer.parseInt(shape1[3]);
	    				Point upperLeftPoint = new Point(x,y);
	    				int height = Integer.parseInt(shape1[4]);
	    				int width = Integer.parseInt(shape1[5]);
	    				Color color = getColorFromString(shape1[6]);
	    				Color innerColor = getColorFromString(shape1[7]);
	    				Rectangle r = new Rectangle(upperLeftPoint, height, width, color, innerColor);
	    				AddShapeCmd addShapeCmd = new AddShapeCmd(r, model);
	    				addShapeCmd.execute();
	    				undoList.add(addShapeCmd);
	    				frame.textAreaCommands.append(addShapeCmd.getCmdTxt()+'\n');
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Circle")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Circle(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[5]+
	    					","+shape1[6]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x = Integer.parseInt(shape1[2]);
	    				int y = Integer.parseInt(shape1[3]);
	    				Point center = new Point(x,y);
	    				int radius = Integer.parseInt(shape1[4]);
	    				Color color = getColorFromString(shape1[5]);
	    				Color innerColor = getColorFromString(shape1[6]);
	    				Circle c = new Circle(center,radius, color, innerColor);
	    				AddShapeCmd addShapeCmd = new AddShapeCmd(c, model);
	    				addShapeCmd.execute();
	    				undoList.add(addShapeCmd);
	    				frame.textAreaCommands.append(addShapeCmd.getCmdTxt()+'\n');
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Donut")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Donut(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[7]+
	    					","+shape1[5]+","+shape1[6]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x = Integer.parseInt(shape1[2]);
	    				int y = Integer.parseInt(shape1[3]);
	    				Point center = new Point(x,y);
	    				int radius = Integer.parseInt(shape1[4]);
	    				int innerRadius = Integer.parseInt(shape1[7]);
	    				Color color = getColorFromString(shape1[5]);
	    				Color innerColor = getColorFromString(shape1[6]);
	    				Donut d = new Donut(center, radius, innerRadius, color, innerColor);
	    				AddShapeCmd addShapeCmd = new AddShapeCmd(d, model);
	    				addShapeCmd.execute();
	    				undoList.add(addShapeCmd);
	    				frame.textAreaCommands.append(addShapeCmd.getCmdTxt()+'\n');
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Hexagon")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Hexagon(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[5]+
	    					","+shape1[6]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x = Integer.parseInt(shape1[2]);
	    				int y = Integer.parseInt(shape1[3]);
	    				int radius = Integer.parseInt(shape1[4]);
	    				Color color = getColorFromString(shape1[5]);
	    				Color innerColor = getColorFromString(shape1[6]);
	    				HexagonAdapter h = new HexagonAdapter(new Hexagon(x,y,radius), color, innerColor);
	    				AddShapeCmd addShapeCmd = new AddShapeCmd(h, model);
	    				addShapeCmd.execute();
	    				undoList.add(addShapeCmd);
	    				frame.textAreaCommands.append(addShapeCmd.getCmdTxt()+'\n');
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		
	    		
	            
	        }
//--------------------------------------------------------------------------------------------------------------------
			else if (line.startsWith("EDITED")) {
	    		shape1 = line.split(";");
	    		if(shape1[1].equals("Point")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to Edit Point("+shape1[2]+","+shape1[3]+","+shape1[4]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x1 = Integer.parseInt(shape1[2]);
	    				int y1 = Integer.parseInt(shape1[3]);
	    				Color color1 = getColorFromString(shape1[4]);
	    				Point p1 = new Point(x1,y1,color1);
	    				int x2 = Integer.parseInt(shape1[6]);
	    				int y2 = Integer.parseInt(shape1[7]);
	    				Color color2 = getColorFromString(shape1[8]);
	    				Point p2 = new Point(x2,y2,color2);
	    				System.out.println(p1.toString());
	    				System.out.println(p2.toString());
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(p1.toString())) {
	            				UpdatePointCmd updatePointCmd = new UpdatePointCmd((Point) shape,p2);
	    	    				updatePointCmd.execute();
	    	    				undoList.add(updatePointCmd);
	    	    				frame.textAreaCommands.append(updatePointCmd.getCmdTxt()+'\n');
//	            				shape.setSelected(true);
	            				break;
	            			}
	            		}
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Line")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Line(("+shape1[2]+","+shape1[3]+"),("+shape1[4]+","+shape1[5]+"))?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				for(int h = 0;h<shape1.length;h++) {
	    					System.out.println(h + "|"+ shape1[h]);
	    				}
//	    				System.out.println(shape1.length);
	    				String oldState = "";
	    				for(int h = 1;h<7;h++) {
	    					oldState += (shape1[h]+";");
	    				}
	    				System.out.println(oldState.replaceAll(";+$", ""));
	    				
//	    				int startX = Integer.parseInt(shape1[2]);
//	    				int startY = Integer.parseInt(shape1[3]);
//	    				int endX = Integer.parseInt(shape1[4]);
//	    				int endY = Integer.parseInt(shape1[5]);
//	    				Color color = getColorFromString(shape1[6]);
//	    				Point startPoint = new Point(startX,startY);
//	    				Point endPoint = new Point(endX, endY);
//	    				Line l = new Line(startPoint, endPoint, color);
//	    				System.out.println(l);
	    				int newStartX = Integer.parseInt(shape1[8]);
	    				int newStartY = Integer.parseInt(shape1[9]);
	    				int newEndX = Integer.parseInt(shape1[10]);
	    				int newEndY = Integer.parseInt(shape1[11]);
	    				Color newColor = getColorFromString(shape1[12]);
	    				Point newStartPoint = new Point(newStartX,newStartY);
	    				Point newEndPoint = new Point(newEndX, newEndY);
	    				Line newL = new Line(newStartPoint, newEndPoint, newColor);
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(oldState.replaceAll(";+$", ""))) {
	            				UpdateLineCmd updateLineCmd = new UpdateLineCmd((Line) shape,newL);
	    	    				updateLineCmd.execute();
	    	    				undoList.add(updateLineCmd);
	    	    				frame.textAreaCommands.append(updateLineCmd.getCmdTxt()+'\n');
//	            				shape.setSelected(true);
	            				break;
	            			}
	            		}
	    			}
	    		}
	    		else if(shape1[1].equals("Rectangle")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to edit Rectangle(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[5]+
	    					","+shape1[6]+","+shape1[7]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
//	    				for(int h = 0;h<shape1.length;h++) {
//	    					System.out.println(h + "|"+ shape1[h]);
//	    				}
	    				String oldState = "";
	    				for(int h = 1;h<8;h++) {
	    					oldState += (shape1[h]+";");
	    				}
	    				System.out.println(oldState.replaceAll(";+$", ""));
	    				int newX = Integer.parseInt(shape1[9]);
	    				int newY = Integer.parseInt(shape1[10]);
	    				Point newUpperLeftPoint = new Point(newX,newY);
	    				int newHeight = Integer.parseInt(shape1[11]);
	    				int newWidth = Integer.parseInt(shape1[12]);
	    				Color newColor = getColorFromString(shape1[13]);
	    				Color newInnerColor = getColorFromString(shape1[14]);
	    				Rectangle newR = new Rectangle(newUpperLeftPoint, newHeight, newWidth, newColor, newInnerColor);
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(oldState.replaceAll(";+$", ""))) {
	            				System.out.println(shape.toString());
	            				UpdateRectangleCmd updateRectangleCmd = new UpdateRectangleCmd((Rectangle) shape,newR);
	    	    				updateRectangleCmd.execute();
	    	    				undoList.add(updateRectangleCmd);
	    	    				frame.textAreaCommands.append(updateRectangleCmd.getCmdTxt()+'\n');
	            				shape.setSelected(true);
	            				break;
	            			}
	    				}
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Circle")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Circle(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[5]+
	    					","+shape1[6]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				String oldState = "";
	    				for(int h = 1;h<7;h++) {
	    					oldState += (shape1[h]+";");
	    				}
	    				int newX = Integer.parseInt(shape1[8]);
	    				int newY = Integer.parseInt(shape1[9]);
	    				Point newCenter = new Point(newX,newY);
	    				int newRadius = Integer.parseInt(shape1[10]);
	    				Color newColor = getColorFromString(shape1[11]);
	    				Color newInnerColor = getColorFromString(shape1[12]);
	    				Circle newC = new Circle(newCenter,newRadius, newColor, newInnerColor);
	    				System.out.println(newC.toString());
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(oldState.replaceAll(";+$", ""))) {
	            				System.out.println(shape.toString());
	            				UpdateCircleCmd updateCircleCmd = new UpdateCircleCmd((Circle) shape,newC);
	    	    				updateCircleCmd.execute();
	    	    				undoList.add(updateCircleCmd);
	    	    				frame.textAreaCommands.append(updateCircleCmd.getCmdTxt()+'\n');
	            				shape.setSelected(true);
	            				break;
	            			}
	    				}
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Donut")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to add Donut(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[7]+
	    					","+shape1[5]+","+shape1[6]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				int x = Integer.parseInt(shape1[2]);
	    				int y = Integer.parseInt(shape1[3]);
	    				Point center = new Point(x,y);
	    				int radius = Integer.parseInt(shape1[4]);
	    				int innerRadius = Integer.parseInt(shape1[7]);
	    				Color color = getColorFromString(shape1[5]);
	    				Color innerColor = getColorFromString(shape1[6]);
	    				Donut d = new Donut(center, radius, innerRadius, color, innerColor);
	    				int newX = Integer.parseInt(shape1[9]);
	    				int newY = Integer.parseInt(shape1[10]);
	    				Point newCenter = new Point(newX,newY);
	    				int newRadius = Integer.parseInt(shape1[11]);
	    				int newInnerRadius = Integer.parseInt(shape1[14]);
	    				Color newColor = getColorFromString(shape1[12]);
	    				Color newInnerColor = getColorFromString(shape1[13]);
	    				Donut newD = new Donut(newCenter, newRadius, newInnerRadius, newColor, newInnerColor);
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(d.toString())) {
	            				System.out.println(shape.toString());
	            				UpdateDonutCmd updateDonutCmd = new UpdateDonutCmd((Donut) shape,newD);
	    	    				updateDonutCmd.execute();
	    	    				undoList.add(updateDonutCmd);
	    	    				frame.textAreaCommands.append(updateDonutCmd.getCmdTxt()+'\n');
	            				shape.setSelected(true);
	            				break;
	            			}
	    				}
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		else if(shape1[1].equals("Hexagon")) {
	    			int option = JOptionPane.showConfirmDialog(null, "Do you want to edit Hexagon(("+shape1[2]+","+shape1[3]+"),"+shape1[4]+","+shape1[5]+
	    					","+shape1[6]+")?");
	    			if(option == JOptionPane.YES_OPTION) {
	    				String oldState = "";
	    				for(int h = 1;h<7;h++) {
	    					oldState += (shape1[h]+";");
	    				}
	    				int newX = Integer.parseInt(shape1[8]);
	    				int newY = Integer.parseInt(shape1[9]);
	    				int newRadius = Integer.parseInt(shape1[10]);
	    				Color newColor = getColorFromString(shape1[11]);
	    				Color newInnerColor = getColorFromString(shape1[12]);
	    				HexagonAdapter newH = new HexagonAdapter(new Hexagon(newX,newY,newRadius), newColor, newInnerColor);
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(oldState.replaceAll(";+$", ""))) {
	            				UpdateHexagonCmd updateHexagonCmd = new UpdateHexagonCmd((HexagonAdapter) shape,newH);
	    	    				updateHexagonCmd.execute();
	    	    				undoList.add(updateHexagonCmd);
	    	    				frame.textAreaCommands.append(updateHexagonCmd.getCmdTxt()+'\n');
	            				shape.setSelected(true);
	            				break;
	            			}
	    				}
	    			}
	    			else {
	    				continueDrawing = option;
	    			}
	    		}
	    		
	    		
	            
	        }
//--------------------------------------------------------------------------------------------------------------------
			else if (line.startsWith("SELECTED")) {
	            	String line2 = cutString(line, "SELECTED;");
	            	int option = JOptionPane.showConfirmDialog(null, "Do you want to select "+line2);
	    			if(option == JOptionPane.YES_OPTION) {
	    				for (Shape shape : model.getShapes()) {
	            			if(shape.toString().equals(line2)) {
	            				SelectShapeCmd selectShapeCmd = new SelectShapeCmd(this, shape);
	            				selectShapeCmd.execute();
	            				undoList.add(selectShapeCmd);
	            				frame.textAreaCommands.append(selectShapeCmd.getCmdTxt()+'\n');
//	            				shape.setSelected(true);
	            				break;
	            			}
	            		}
	                }
	    			else {
	    				continueDrawing = option;
	    			}
	            
	        }
//--------------------------------------------------------------------------------------------------------------------
			else if (line.startsWith("DESELECTED")) {
	        	String line2 = cutString(line, "DESELECTED;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to deselect "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for (Shape shape : model.getShapes()) {
	        			if(shape.toString().equals(line2)) {
	        				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(this, shape);
            				deselectShapeCmd.execute();
            				undoList.add(deselectShapeCmd);
            				frame.textAreaCommands.append(deselectShapeCmd.getCmdTxt()+'\n');
//	        				shape.setSelected(false);
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
//--------------------------------------------------------------------------------------------------------------------
	        } else if (line.startsWith("DELETED")) {
	        	String line2 = cutString(line, "DELETED;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to delete "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for (Shape shape : model.getShapes()) {
//						if(shape.isSelected())
//						{
//							System.out.println("selektovan"+shape.toString());
//							RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(shape, model, this);
//							removeShapeCmd.execute();
//							undoList.add(removeShapeCmd);
//							frame.textAreaCommands.append(removeShapeCmd.getCmdTxt()+'\n');
//////        				model.removeShape(shape);
//        					break;
//							
//						}
	        			if(shape.toString().equals(line2)) {
	        				RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(shape, model, this);
	        				removeShapeCmd.execute();
	        				undoList.add(removeShapeCmd);
	        				frame.textAreaCommands.append(removeShapeCmd.getCmdTxt()+'\n');
//	        				model.removeShape(shape);
	        				break;
	        			}
	        			
	        		}
	            }
				else {
    				continueDrawing = option;
    			}

//--------------------------------------------------------------------------------------------------------------------
	        } 
	        else if (line.startsWith("TOBACK")) {
	        	String line2 = cutString(line, "TOBACK;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to move to back "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for (Shape shape : model.getShapes()) {
	        			if(shape.toString().equals(line2)) {
	        				ToBackCmd toBackCmd = new ToBackCmd(model, shape);
	        				toBackCmd.execute();
	        				undoList.add(toBackCmd);
	        				frame.textAreaCommands.append(toBackCmd.getCmdTxt()+'\n');
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
//--------------------------------------------------------------------------------------------------------------------
	        } else if (line.startsWith("TOFRONT")) {
	        	String line2 = cutString(line, "TOFRONT;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to move to front "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for (Shape shape : model.getShapes()) {
	        			if(shape.toString().equals(line2)) {
	        				ToFrontCmd toFrontCmd = new ToFrontCmd(model, shape);
	        				toFrontCmd.execute();
	        				undoList.add(toFrontCmd);
	        				frame.textAreaCommands.append(toFrontCmd.getCmdTxt()+'\n');
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
//--------------------------------------------------------------------------------------------------------------------
	        } else if (line.startsWith("BRINGTOBACK")) {
	        	String line2 = cutString(line, "BRINGTOBACK;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to bring to back "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for (Shape shape : model.getShapes()) {
	        			if(shape.toString().equals(line2)) {
	        				BringToBackCmd bringToBackCmd = new BringToBackCmd(model, shape);
	        				bringToBackCmd.execute();
	        				undoList.add(bringToBackCmd);
	        				frame.textAreaCommands.append(bringToBackCmd.getCmdTxt()+'\n');
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
//--------------------------------------------------------------------------------------------------------------------
	        } else if (line.startsWith("BRINGTOFRONT")) {
	        	String line2 = cutString(line, "BRINGTOFRONT;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to bring to front "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for (Shape shape : model.getShapes()) {
	        			if(shape.toString().equals(line2)) {
	        				BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, shape);
	        				bringToFrontCmd.execute();
	        				undoList.add(bringToFrontCmd);
	        				frame.textAreaCommands.append(bringToFrontCmd.getCmdTxt()+'\n');
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
	        }
//--------------------------------------------------------------------------------------------------------------------
	        else if(line.startsWith("UNDO")) {
	        	int undoListSize = undoList.size();
	        	String line2 = cutString(line, "UNDO;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to undo "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for(Command command : undoList) {
						
	        			if(command.getCmdTxt().equals(line2)) {
	        				command.unexecute();
	        				redoList.add(command);
	        				undoList.remove(undoListSize-1);
	        				frame.repaint();
	        				frame.textAreaCommands.append(line + '\n');
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
	        }
//--------------------------------------------------------------------------------------------------------------------
	        else if(line.startsWith("REDO")) {
	        	int redoListSize = redoList.size();
	        	String line2 = cutString(line, "REDO;");
	        	int option = JOptionPane.showConfirmDialog(null, "Do you want to redo "+line2);
				if(option == JOptionPane.YES_OPTION) {
					for(Command command : redoList) {
						
	        			if(command.getCmdTxt().equals(line2)) {
	        				command.execute();
	        				undoList.add(command);
	        				redoList.remove(redoListSize-1);
	        				frame.repaint();
	        				frame.textAreaCommands.append(line + '\n');
	        				break;
	        			}
	        		}
	            }
				else {
    				continueDrawing = option;
    			}
	        }
	        else {
	//            System.out.println("Ostale linije: " + line);
	        }
			frame.repaint();
			i++;
			for(Command command : undoList) {
				System.out.println(command.getCmdTxt());
			}
			System.out.println("-----------------------------------");
		}
		if(continueDrawing == 0) {
			JOptionPane.showMessageDialog(null, "Txt file loaded successfully","Load txt file", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Loading txt file stopped", "Load txt file", JOptionPane.ERROR_MESSAGE);
		}
//    	frame.repaint();
    }
	public Color getColorFromString(String string) {
		int r = Integer.parseInt(string.substring(string.indexOf("r=") + 2, string.indexOf(",", string.indexOf("r="))));
		int g = Integer.parseInt(string.substring(string.indexOf("g=") + 2, string.indexOf(",", string.indexOf("g="))));
		int b = Integer.parseInt(string.substring(string.indexOf("b=") + 2, string.indexOf("]", string.indexOf("b="))));

		return new Color(r,g,b);
	}
	public String cutString(String string, String stringToRemove) {
//		String originalniString = "SELECTED:Hexagon;196;356;90;java.awt.Color[r=0,g=0,b=255];java.awt.Color[r=255,g=204,b=204]";
//		return string.substring(string.indexOf("SELECTED:") + "SELECTED:".length());
		return string.substring(string.indexOf(stringToRemove) + stringToRemove.length());
	}
	

}