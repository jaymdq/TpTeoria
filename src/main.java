import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTabbedPane;


public class main {

	private JFrame frmTpTeora;
	private JList<String> list;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frmTpTeora.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTpTeora = new JFrame();
		frmTpTeora.getContentPane().setBackground(new Color(255, 255, 153));
		frmTpTeora.setTitle("TP Teor\u00EDa");
		frmTpTeora.setBounds(100, 100, 450, 300);
		frmTpTeora.setResizable(false);
		frmTpTeora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTpTeora.setFont(new Font("Verdana", Font.PLAIN, 14));
		int ancho = frmTpTeora.getToolkit().getScreenSize().width;
		int alto = frmTpTeora.getToolkit().getScreenSize().height;
		frmTpTeora.setBounds(0,0,ancho - 100, alto - 100);
		frmTpTeora.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmTpTeora.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenu mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);
		frmTpTeora.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 153));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmTpTeora.getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("30dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("32px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("32px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("30dlu"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("32px"),
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblMidis = new JLabel("Archivos midis:");
		lblMidis.setHorizontalAlignment(SwingConstants.CENTER);
		lblMidis.setFont(new Font("Verdana", Font.BOLD, 16));
		panel.add(lblMidis, "2, 2, 5, 1, left, center");
		DefaultListModel<String> modelo = new DefaultListModel<String>();

		//obtener canciones
		File dir = new File("src/midis/");
		String[] ficheros = dir.list();
		for (int i=0; i < ficheros.length ; i++)
			for (int j=i+1; j < ficheros.length - 1 ; j++)
				if (ficheros[i].compareTo(ficheros[j]) == 1){
					String aux = ficheros[i];
					ficheros[i] = ficheros[i];
					ficheros[j] = aux;
				}

		for(int i = 0; i<ficheros.length; i++){
		        modelo.addElement(ficheros[i]);
		}
		
		list = new JList<String>();
		list.setBackground(Color.WHITE);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setFont(new Font("Verdana", Font.BOLD, 16));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(modelo);
		panel.add(list, "2, 4, 7, 1, left, center");
		list.setSelectedIndex(0);
		
		JButton btnParar = new JButton("");
		btnParar.setIcon(new ImageIcon("src/imagenes/stop.png"));
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			pausa();
			}
		});
		
		JButton playButton = new JButton("");
		playButton.setIcon(new ImageIcon("src/imagenes/play.png"));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			play();
			}
		});
		
		
		panel.add(playButton, "4, 6, left, top");
		panel.add(btnParar, "6, 6");
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmTpTeora.getContentPane().add(tabs, BorderLayout.CENTER);
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.WHITE);
		tabs.addTab("Ej 1", null, pan1, null);
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 2", null, pan2, null);
		
		JPanel pan3 = new JPanel();
		pan3.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 3", null, pan3, null);
	
		JPanel pan4 = new JPanel();
		pan4.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 4", null, pan4, null);
		
		JPanel pan5 = new JPanel();
		pan5.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 5", null, pan5, null);
		
		JPanel pan6 = new JPanel();
		pan6.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 6", null, pan6, null);
		
	
		
		//frmMeatAnalyzer.setIconImage(Toolkit.getDefaultToolkit().getImage("icono.png"));
		
	}

	protected void pausa() {
		// Método que para la canción que se está reproduciendo.
		MidiPlayer.getInstance().pause();
	}

	protected void play() {
		// Método que reproduce la canción seleccionada.
		
		MidiPlayer.getInstance().play("src/midis/"+list.getSelectedValue());
	}

}
