package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	
	private Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		
		// TODO crear ControlPanel y añadirlo en PAGE_START de mainPanel
		JPanel controlPanel = new ControlPanel(_ctrl);
		mainPanel.add( controlPanel, BorderLayout.NORTH);
		
		
		// TODO crear StatusBar y añadirlo en PAGE_END de mainPanel
		JPanel statusPanel = new StatusBar(_ctrl);
		mainPanel.add( statusPanel, BorderLayout.SOUTH);
		
		// Definición del panel de tablas (usa un BoxLayout vertical)
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		
		// TODO crear la tabla de grupos y añadirla a contentPanel.
		// Usa setPreferredSize(new Dimension(500, 250)) para fijar su tamaño
//		DefaultTableModel dtm = new DefaultTableModel();
//		JTable tableG = new JTable(TableModel dfm);
//		JScrollPane scroll = new JScrollPane(tableG, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
//		
		
		// TODO crear la tabla de cuerpos y añadirla a contentPanel.
		// Usa setPreferredSize(new Dimension(500, 250)) para fijar su tamaño
		
		// TODO llama a Utils.quit(MainWindow.this) en el método windowClosing
		
		addWindowListener(null); //cambiar
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //No hace nada cuando el usuario cierra
														//la ventana
		pack();
		setVisible(true);
	}

//	private JPanel createSuperiorPanel() {
//		// TODO Auto-generated method stub
//		JPanel controlPanel = new JPanel();
//		
//		return controlPanel;
//	}

}
