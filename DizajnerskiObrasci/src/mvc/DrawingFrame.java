package mvc;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DrawingFrame extends JFrame {
	
	DrawingView view = new DrawingView();
	DrawingController controller;
	
	Color color;
	Color innerColor;
	
	
	private JPanel contentPane = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JToggleButton tglbtnPoint = new JToggleButton("Point");
	JToggleButton tglbtnLine = new JToggleButton("Line");
	JToggleButton tglbtnDonut = new JToggleButton("Donut");
	JToggleButton tglbtnCircle = new JToggleButton("Circle");
	JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
    public JToggleButton tglbtnSelect = new JToggleButton("Select");
    JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
    public JButton btnEdit = new JButton("Edit");
    public JButton btnDelete = new JButton("Delete");
    JButton btnInnerColor = new JButton("Inner color");
    JButton btnColor = new JButton("Color");
    public JButton btnUndo = new JButton("Undo");
    public JButton btnRedo = new JButton("Redo");
    public JButton btnBringToFront = new JButton("Bring to front");
    public JButton btnToFront = new JButton("To front");
    public JButton btnToBack = new JButton("To back");
	public JButton btnBringToBack = new JButton("Bring to back");
	public JTextArea textAreaCommands = new JTextArea();
    
    public DrawingView getView() {
		return view;
	}
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
    
    public DrawingFrame() {
		setTitle("Djordje Vukovic IT48/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1297, 786);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlToolbar = new JPanel();
		pnlToolbar.setBackground(new Color(224, 255, 255));
		pnlToolbar.setBounds(125, 10, 602, 30);
		contentPane.add(pnlToolbar);
		pnlToolbar.setLayout(null);
		
		
		buttonGroup.add(tglbtnSelect);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		tglbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tglbtnSelect.setBounds(0, 0, 90, 30);
		tglbtnSelect.setEnabled(false);
		//tglbtnSelect.setBackground(Color.LIGHT_GRAY);
		pnlToolbar.add(tglbtnSelect);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.editClicked();
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(100, 0, 90, 30);
		btnEdit.setEnabled(false);
		//btnEdit.setBackground(Color.LIGHT_GRAY);
		pnlToolbar.add(btnEdit);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteClicked();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(200, 0, 90, 30);
		btnDelete.setEnabled(false);
//		btnDelete.setBackground(Color.LIGHT_GRAY);
		pnlToolbar.add(btnDelete);
		
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redoClicked();
			}
		});
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRedo.setBounds(477, 0, 120, 30);
		btnRedo.setEnabled(false);
		pnlToolbar.add(btnRedo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undoClicked();
			}
		});
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUndo.setBounds(347, 0, 120, 30);
		btnUndo.setEnabled(false);
		
		pnlToolbar.add(btnUndo);
		view.setBackground(Color.WHITE);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		view.setBounds(125, 50, 817, 599);
		contentPane.add(view);
		
		JPanel pnlShapes = new JPanel();
		pnlShapes.setBackground(new Color(224, 255, 255));
		pnlShapes.setBounds(10, 50, 105, 212);
		contentPane.add(pnlShapes);
		pnlShapes.setLayout(null);
		tglbtnPoint.setBounds(0, 0, 105, 30);
		tglbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlShapes.add(tglbtnPoint);
		
		
		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.shapeClicked();
			}
		});
//		tglbtnPoint.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(tglbtnPoint);
		tglbtnLine.setBounds(0, 36, 105, 30);
		tglbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlShapes.add(tglbtnLine);
		
		
		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.shapeClicked();
			}
		});
//		tglbtnLine.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(tglbtnLine);
		tglbtnRectangle.setBounds(0, 72, 105, 30);
		tglbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlShapes.add(tglbtnRectangle);
		
		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.shapeClicked();
			}
		});
//		tglbtnRectangle.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(tglbtnRectangle);
		tglbtnCircle.setBounds(0, 109, 105, 30);
		tglbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlShapes.add(tglbtnCircle);
		
		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.shapeClicked();
			}
		});
//		tglbtnCircle.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(tglbtnCircle);
		tglbtnDonut.setBounds(0, 145, 105, 30);
		tglbtnDonut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlShapes.add(tglbtnDonut);
		
		
		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.shapeClicked();
			}
		});
//		tglbtnDonut.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(tglbtnDonut);
		tglbtnHexagon.setBounds(0, 181, 105, 30);
//		tglbtnHexagon.setBackground(new Color(255, 228, 225));
		tglbtnHexagon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlShapes.add(tglbtnHexagon);
		
		tglbtnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup.add(tglbtnHexagon);
		btnColor.setForeground(Color.WHITE);
		btnColor.setBounds(10, 295, 105, 70);
		contentPane.add(btnColor);
		
		
		btnColor.setBackground(Color.BLACK);
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color initialColor = Color.BLACK;
			    color = JColorChooser.showDialog(null, "Select color", initialColor);
			    btnColor.setBackground(color);
			}
		});
		btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInnerColor.setBounds(10, 371, 105, 70);
		contentPane.add(btnInnerColor);
		
		
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color initialColor = Color.WHITE;
				innerColor = JColorChooser.showDialog(null, "Select color", initialColor);
			    btnInnerColor.setBackground(innerColor);
			}
		});
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(125, 659, 1000, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFrontClicked();
			}
		});
		
		
		btnBringToFront.setBounds(405, 0, 125, 30);
		btnBringToFront.setEnabled(false);
		btnBringToFront.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btnBringToFront);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFrontClicked();
			}
		});
		
		
		btnToFront.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnToFront.setBounds(270, 0, 125, 30);
		btnToFront.setEnabled(false);
		panel.add(btnToFront);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBackClicked();
			}
		});
		
		
		btnToBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnToBack.setBounds(135, 0, 125, 30);
		btnToBack.setEnabled(false);
		panel.add(btnToBack);
		
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBackClicked();
			}
		});
		btnBringToBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBringToBack.setBounds(0, 0, 125, 30);
		btnBringToBack.setEnabled(false);
		panel.add(btnBringToBack);
		textAreaCommands.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		textAreaCommands.setBounds(1, 1, 114, 579);
		contentPane.add(textAreaCommands);
		
		JScrollPane scrollPane = new JScrollPane(textAreaCommands);
		scrollPane.setBounds(952, 51, 321, 598);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        menuBar.add(fileMenu);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fileMenu.add(saveMenuItem);

        JMenuItem loadBinMenuItem = new JMenuItem("Load bin file");
        loadBinMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fileMenu.add(loadBinMenuItem);
        
        JMenuItem loadTxtMenuItem = new JMenuItem("Load txt file");
        loadTxtMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        loadBinMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fileMenu.add(loadTxtMenuItem);

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveClicked();
            }
        });

        loadBinMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadBinFile();
            }
        });
        loadTxtMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadTxtFile();
            }
        });
	}
}
