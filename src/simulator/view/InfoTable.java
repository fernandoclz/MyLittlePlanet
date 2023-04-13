package simulator.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

public class InfoTable extends JPanel{
	String _title;
	TableModel _tableModel;
	
	InfoTable(String title, TableModel tableModel) {
		_title = title;
		_tableModel = tableModel;
		initGUI();
	}
	private void initGUI() {
	// TODO cambiar el layout del panel a BorderLayout()
		this.setLayout(new BorderLayout());
	// TODO a�adir un borde con t�tulo al JPanel, con el texto _title
		Border title = new TitledBorder(new EtchedBorder(), _title);
		this.setBorder(title);
	// TODO a�adir un JTable (con barra de desplazamiento vertical) que use
	// _tableModel
		JTable table = new JTable(_tableModel);
		JScrollPane s = new JScrollPane(table);
		this.add(s);
	}

}
