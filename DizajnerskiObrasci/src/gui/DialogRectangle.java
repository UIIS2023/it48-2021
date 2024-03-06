package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DialogRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtXCoordinate;
	protected JTextField txtYCoordinate;
	protected JTextField txtHeight;
	protected JTextField txtWidth;
	protected JButton btnInnerColor;
	protected JButton btnPickAColor;
	Rectangle rec = null;
	private Color clr;
	private Color innerClr;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRectangle dialog = new DialogRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRectangle() {
		setTitle("Edit rectangle");
		setModal(true);
		setBounds(100, 100, 450, 352);
		getContentPane().setLayout(new BorderLayout());
	
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate (upperleft point)");
			lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblXCoordinate.setBounds(22, 29, 200, 14);
			contentPanel.add(lblXCoordinate);
		}
		{
			txtXCoordinate = new JTextField();
			txtXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtXCoordinate.setBounds(239, 22, 174, 30);
			contentPanel.add(txtXCoordinate);
			txtXCoordinate.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate (upperleft point)");
			lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblYCoordinate.setBounds(22, 70, 200, 14);
			contentPanel.add(lblYCoordinate);
		}
		{
			txtYCoordinate = new JTextField();
			txtYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtYCoordinate.setBounds(239, 63, 174, 30);
			contentPanel.add(txtYCoordinate);
			txtYCoordinate.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height");
			lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblHeight.setBounds(22, 111, 177, 14);
			contentPanel.add(lblHeight);
		}
		{
			txtHeight = new JTextField();
			txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtHeight.setBounds(239, 104, 174, 30);
			contentPanel.add(txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("WIdth");
			lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblWidth.setBounds(22, 151, 177, 14);
			contentPanel.add(lblWidth);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtWidth.setBounds(239, 145, 174, 30);
			contentPanel.add(txtWidth);
			txtWidth.setColumns(10);
		}
		{
			btnInnerColor = new JButton("Inner color");
			btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnInnerColor.setBounds(144, 201, 112, 30);
			
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.WHITE;
					Color color = JColorChooser.showDialog(null, "Select color", initialColor);
				    btnInnerColor.setBackground(color);
				}
			});
			contentPanel.add(btnInnerColor);
		}
		{
			btnPickAColor = new JButton("Color");
			btnPickAColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnPickAColor.setBounds(22, 201, 112, 30);
			
			btnPickAColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.BLACK;
					Color color = JColorChooser.showDialog(null, "Select color", initialColor);
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
						if(txtXCoordinate.getText().isEmpty() 
								|| txtYCoordinate.getText().isEmpty()
								|| txtHeight.getText().isEmpty()
								|| txtWidth.getText().isEmpty())
							JOptionPane.showMessageDialog(null, "You haven't entered all data", "Error", JOptionPane.ERROR_MESSAGE);
						else if(!isNumeric(txtXCoordinate.getText())) {
							JOptionPane.showMessageDialog(null, "X coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!isNumeric(txtYCoordinate.getText())) {
							JOptionPane.showMessageDialog(null, "Y coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!isNumeric(txtHeight.getText())) {
							JOptionPane.showMessageDialog(null, "Height must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!isNumeric(txtWidth.getText())) {
							JOptionPane.showMessageDialog(null, "Width must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(isNumeric(txtXCoordinate.getText())
								&& isNumeric(txtYCoordinate.getText())
								&& isNumeric(txtHeight.getText())
								&& isNumeric(txtWidth.getText())) {
							int x = Integer.parseInt(txtXCoordinate.getText());
							int y = Integer.parseInt(txtYCoordinate.getText());
							int width = Integer.parseInt(txtWidth.getText());
							int height = Integer.parseInt(txtHeight.getText());
							if(x<0) {
								JOptionPane.showMessageDialog(null, "X coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else if(y<0) {
								JOptionPane.showMessageDialog(null, "Y coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else if (width <= 0) {
								JOptionPane.showMessageDialog(null, "Width must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
							} 
							else if (height <= 0) {
								JOptionPane.showMessageDialog(null, "Height must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
						    }
							else {
								Point upperLeftPoint = new Point(x, y);
								rec = new Rectangle(upperLeftPoint, height, width);
								clr = btnPickAColor.getBackground();
								innerClr = btnInnerColor.getBackground();
								rec.setColor(clr);
								rec.setInnerColor(innerClr);
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
	public JTextField getTxtHeight() {
		return this.txtHeight;
	}
	public JTextField getTxtWidth() {
		return this.txtWidth;
	}
	public JButton getBtnColor() {
		return this.btnPickAColor;
	}
	public JButton getBtnInnerColor() {
		return this.btnInnerColor;
	}
	public Rectangle getRectangle() {
		return this.rec;
	}
	public Color getColor() {
		return this.clr;
	}
	public Color getInnerColor() {
		return this.innerClr;
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
