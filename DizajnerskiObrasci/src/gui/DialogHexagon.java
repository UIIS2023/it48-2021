package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Point;
import hexagon.Hexagon;

public class DialogHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtCenterX;
	protected JTextField txtCenterY;
	protected JTextField txtRadius;
//	private Hexagon hexagon = new Hexagon();
	private HexagonAdapter hexagonAdapter = null;
	private Color clr;
	private Color innerClr;
	protected JButton btnInnerColor;
	protected JButton btnPickAColor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogHexagon dialog = new DialogHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogHexagon() {
		setTitle("Edit hexagon");
		setModal(true);
		setBounds(100, 100, 450, 299);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBackground(new Color(224, 255, 255));
		//contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate (center)");
			lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblXCoordinate.setBounds(32, 27, 143, 14);
			contentPanel.add(lblXCoordinate);
		}
		{
			txtCenterX = new JTextField();
			txtCenterX.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtCenterX.setBounds(235, 20, 174, 30);
			contentPanel.add(txtCenterX);
			txtCenterX.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate (center)");
			lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblYCoordinate.setBounds(32, 76, 143, 14);
			contentPanel.add(lblYCoordinate);
		}
		{
			txtCenterY = new JTextField();
			txtCenterY.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtCenterY.setBounds(235, 69, 174, 30);
			contentPanel.add(txtCenterY);
			txtCenterY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius");
			lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblRadius.setBounds(32, 126, 119, 14);
			contentPanel.add(lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtRadius.setBounds(235, 119, 174, 30);
			contentPanel.add(txtRadius);
			txtRadius.setColumns(10);
		}
		{
			btnPickAColor = new JButton("Color");
			btnPickAColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnPickAColor.setBounds(32, 171, 112, 30);
			contentPanel.add(btnPickAColor);
			
			btnPickAColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.BLACK;
					Color color = JColorChooser.showDialog(null, "Choose color", initialColor);
					btnPickAColor.setBackground(color);
				}
			});
			btnPickAColor.setHorizontalAlignment(SwingConstants.LEFT);
		}
		{
			btnInnerColor = new JButton("Inner color");
			btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnInnerColor.setBounds(154, 171, 112, 30);
			contentPanel.add(btnInnerColor);
			
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.WHITE;
					Color color = JColorChooser.showDialog(null, "Choose color", initialColor);
					btnInnerColor.setBackground(color);
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			//buttonPane.setBackground(new Color(224, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					JButton okButton = new JButton("OK");
					okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (txtCenterX.getText().isEmpty() || txtCenterY.getText().isEmpty()
									|| txtRadius.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "You must enter all data", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (!isNumeric(txtCenterX.getText())) {
								JOptionPane.showMessageDialog(null, "X coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (!isNumeric(txtCenterY.getText())) {
								JOptionPane.showMessageDialog(null, "Y coordinate must be a number", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (!isNumeric(txtRadius.getText())) {
								JOptionPane.showMessageDialog(null, "Radius must be a number", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (isNumeric(txtCenterX.getText()) && isNumeric(txtCenterY.getText())
									&& isNumeric(txtRadius.getText())) {
								int x = Integer.parseInt(txtCenterX.getText());
								int y = Integer.parseInt(txtCenterY.getText());
								int radius = Integer.parseInt(txtRadius.getText());
								if (x < 0) {
									JOptionPane.showMessageDialog(null, "X coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
								} else if (y < 0) {
									JOptionPane.showMessageDialog(null, "Y coordinate must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
								} else if (radius <= 0) {
									JOptionPane.showMessageDialog(null, "Radius must be bigger than 0", "Error", JOptionPane.ERROR_MESSAGE);
								} else {
//									Point center = new Point(x, y);
//									= new Circle(center, radius);
									Hexagon hexagon = new Hexagon(x, y, radius);
									hexagonAdapter = new HexagonAdapter(hexagon);
//									hexagonAdapter.setX(x);
//									hexagonAdapter.setY(y);
//									hexagonAdapter.setRadius(radius);
									clr = btnPickAColor.getBackground();
									hexagonAdapter.setColor(clr);
									innerClr = btnInnerColor.getBackground();
									hexagonAdapter.setInnerColor(innerClr);
									
									dispose();
								}
							}
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
	public JTextField getTxtCenterX() {
		return this.txtCenterX;
	}
	public JTextField getTxtCenterY() {
		return this.txtCenterY;
	}
	public JButton getBtnColor() {
		return this.btnPickAColor;
	}
	public JButton getBtnInnerColor() {
		return this.btnInnerColor;
	}
	
	public JTextField getTxtRadius() {
		return txtRadius;
	}


	public HexagonAdapter getHexagon() {
		return hexagonAdapter;
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
		catch (NumberFormatException ex) {
			return false;
		}
	}

}
