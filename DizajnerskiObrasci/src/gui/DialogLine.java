package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DialogLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtStartX;
	protected JTextField txtStartY;
	protected JTextField txtEndX;
	protected JTextField txtEndY;
	Color clr;
	private Line line = null;
	protected JButton btnPickAColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogLine dialog = new DialogLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogLine() {
		setTitle("Edit line");
		setModal(true);
		setBounds(100, 100, 450, 381);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblStartPoint = new JLabel("Start point");
			lblStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblStartPoint.setBounds(179, 11, 87, 14);
			contentPanel.add(lblStartPoint);
		}
		{
			JLabel lblXCoordinateStart = new JLabel("X coordinate");
			lblXCoordinateStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblXCoordinateStart.setBounds(30, 44, 87, 14);
			contentPanel.add(lblXCoordinateStart);
		}
		{
			txtStartX = new JTextField();
			txtStartX.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtStartX.setBounds(234, 37, 174, 30);
			contentPanel.add(txtStartX);
			txtStartX.setColumns(10);
		}
		{
			JLabel lblYCoordinateStart = new JLabel("Y coordinate");
			lblYCoordinateStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblYCoordinateStart.setBounds(30, 85, 87, 14);
			contentPanel.add(lblYCoordinateStart);
		}
		{
			txtStartY = new JTextField();
			txtStartY.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtStartY.setBounds(234, 78, 174, 30);
			contentPanel.add(txtStartY);
			txtStartY.setColumns(10);
		}
		{
			JLabel lblEndPoint = new JLabel("End point");
			lblEndPoint.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEndPoint.setBounds(179, 119, 87, 14);
			contentPanel.add(lblEndPoint);
		}
		{
			JLabel lblXCoordinateEnd = new JLabel("X coordinate");
			lblXCoordinateEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblXCoordinateEnd.setBounds(30, 151, 87, 14);
			contentPanel.add(lblXCoordinateEnd);
		}
		{
			txtEndX = new JTextField();
			txtEndX.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtEndX.setBounds(234, 144, 174, 30);
			contentPanel.add(txtEndX);
			txtEndX.setColumns(10);
		}
		{
			JLabel lblYCoordinateEnd = new JLabel("Y coordinate");
			lblYCoordinateEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblYCoordinateEnd.setBounds(30, 192, 87, 14);
			contentPanel.add(lblYCoordinateEnd);
		}
		{
			txtEndY = new JTextField();
			txtEndY.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtEndY.setBounds(234, 185, 174, 30);
			contentPanel.add(txtEndY);
			txtEndY.setColumns(10);
		}
		{
			btnPickAColor = new JButton("Color");
			btnPickAColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnPickAColor.setBounds(28, 243, 112, 30);
		
			btnPickAColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.black;
					Color color = JColorChooser.showDialog(null, "Select a color", initialColor);
					btnPickAColor.setBackground(color);
				}
			});
			contentPanel.add(btnPickAColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtStartX.getText().isEmpty() || txtStartY.getText().isEmpty()
								|| txtEndX.getText().isEmpty() || txtEndY.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "You need to enter all data", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (!isNumeric(txtStartX.getText())) {
							JOptionPane.showMessageDialog(null, "X coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (!isNumeric(txtStartY.getText())) {
							JOptionPane.showMessageDialog(null, "Y coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (!isNumeric(txtEndX.getText())) {
							JOptionPane.showMessageDialog(null, "X coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (!isNumeric(txtEndY.getText())) {
							JOptionPane.showMessageDialog(null, "Y coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (isNumeric(txtStartX.getText()) && isNumeric(txtStartY.getText())
								&& isNumeric(txtEndX.getText()) && isNumeric(txtEndY.getText())) {
							int x1 = Integer.parseInt(txtStartX.getText());
							int y1 = Integer.parseInt(txtStartY.getText());
							int x2 = Integer.parseInt(txtEndX.getText());
							int y2 = Integer.parseInt(txtEndY.getText());
							if (x1 < 0) {
								JOptionPane.showMessageDialog(null, "X coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (y1 < 0) {
								JOptionPane.showMessageDialog(null, "Y coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (x2 < 0) {
								JOptionPane.showMessageDialog(null, "X coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (y2 < 0) {
								JOptionPane.showMessageDialog(null, "Y coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else {
								Point startPoint = new Point(x1, y1);
								Point endPoint = new Point(x2, y2);
								
								clr = btnPickAColor.getBackground();
								line = new Line(startPoint, endPoint, clr);
								//line.setColor(clr);
								dispose();
							}
						}
					}
							
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public JTextField getTxtStartX() {
		return txtStartX;
	}

	public JTextField getTxtStartY() {
		return txtStartY;
	}

	public JTextField getTxtEndX() {
		return txtEndX;
	}

	public JTextField getTxtEndY() {
		return txtEndY;
	}

	public JButton getBtnPickAColor() {
		return btnPickAColor;
	}

	public Line getLine() {
		return this.line;
	}
	public Color getColor() {
		return this.clr;
	}
	private static boolean isNumeric(String str) {
		if(str == "" || str == null) {
			return false;
		}
		try {
			int number = Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	

}
