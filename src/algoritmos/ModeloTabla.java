package algoritmos;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isCellEditable (int row, int column)
	   {
	       return false;
	   }
}
