package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DialogPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtYCoordinate;
	protected JTextField txtXCoordinate;
	protected JButton btnPickAColor = new JButton("Color");
	Point pt = null;
	Color clr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogPoint dialog = new DialogPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogPoint() {
		setTitle("Edit point");
		setModal(true);
		setBounds(100, 100, 450, 263);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate");
			lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblXCoordinate.setBounds(35, 35, 87, 14);
			contentPanel.add(lblXCoordinate);
		}
		{
			txtXCoordinate = new JTextField();
			txtXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtXCoordinate.setBounds(237, 28, 174, 30);
			contentPanel.add(txtXCoordinate);
			txtXCoordinate.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate");
			lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblYCoordinate.setBounds(35, 76, 87, 14);
			contentPanel.add(lblYCoordinate);
		}
		{
			txtYCoordinate = new JTextField();
			txtYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtYCoordinate.setBounds(237, 69, 174, 30);
			contentPanel.add(txtYCoordinate);
			txtYCoordinate.setColumns(10);
		}
		{
		    btnPickAColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    btnPickAColor.setBounds(35, 124, 112, 30);
	
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
						if (txtXCoordinate.getText().isEmpty() || txtYCoordinate.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "You must enter all data", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (!isNumeric(txtXCoordinate.getText())) {
							JOptionPane.showMessageDialog(null, "X coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (!isNumeric(txtYCoordinate.getText())) {
							JOptionPane.showMessageDialog(null, "Y coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						} else if (isNumeric(txtXCoordinate.getText()) && isNumeric(txtYCoordinate.getText())) {
							int x = Integer.parseInt(txtXCoordinate.getText());
							int y = Integer.parseInt(txtYCoordinate.getText());
							if (x < 0) {
								JOptionPane.showMessageDialog(null, "X coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (y < 0) {
								JOptionPane.showMessageDialog(null, "Y coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							} 
							else {
								pt = new Point(x, y);	
					 	     	clr = btnPickAColor.getBackground();
								pt.setColor(clr);
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
	public JTextField getTxtXCoordinate() {
		return this.txtXCoordinate;
	}
	public JTextField getTxtYCoordinate() {
		return this.txtYCoordinate;
	}
	public JButton getBtnColor() {
		return this.btnPickAColor;
	}
	public Color getColor() {
		return this.clr;
	}
	public Point getPoint() {
		return this.pt;
	}
	private static boolean isNumeric(String str) {
		if(str == "" || str == null) {
			return false;
		}
		try {
			int number = Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException ex) {
			return false;
		}
	}
	
	

}
