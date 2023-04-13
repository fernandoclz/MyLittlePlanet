package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
		InfoTable tableG = new InfoTable("Groups", new GroupsTableModel(_ctrl));
		tableG.setPreferredSize(new Dimension(500,250));
		contentPanel.add(tableG);
		
		// TODO crear la tabla de cuerpos y añadirla a contentPanel.
		// Usa setPreferredSize(new Dimension(500, 250)) para fijar su tamaño
		InfoTable tableC = new InfoTable("Bodies", new BodiesTableModel(_ctrl));
		tableC.setPreferredSize(new Dimension(500, 250));
		contentPanel.add(tableC);
		
		// TODO llama a Utils.quit(MainWindow.this) en el método windowClosing
		
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Utils.quit(MainWindow.this);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}); //cambiar
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //No hace nada cuando el usuario cierra
														//la ventana
		pack();
		setVisible(true);
	}


}
