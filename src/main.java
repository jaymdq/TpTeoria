import java.awt.EventQueue;
import java.awt.Font;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTabbedPane;

import java.awt.Choice;

import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;

import java.awt.FlowLayout;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;


public class main {

	private JFrame frmTpTeora;
	private JList<String> list;
	private JList<String> ej1Resultado;
	private JList<String> ej1TemasComparar;
	
	//Lista de tracks fijos.
	public static final int TRACK_DG = 0;
	public static final int TRACK_GOF = 2;
	public static final int TRACK_JDC = 0;
	public static final int TRACK_TBBT = 3;
	public static final int TRACK_TS = 3;
	public static final int TRACK_YB = 0;
	
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
		tabs.setFont(new Font("Verdana", Font.BOLD, 16));
		tabs.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmTpTeora.getContentPane().add(tabs, BorderLayout.CENTER);
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.WHITE);
		tabs.addTab("Ej 1", null, pan1, null);
		pan1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("8dlu"),
				ColumnSpec.decode("175dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("175dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				ColumnSpec.decode("4dlu:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("16dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("16dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Seleccionar Tema de Referencia");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(lblNewLabel, "2, 2");
		
		JLabel lblSeleccionarTemasA = new JLabel("Seleccionar Temas a comparar (Ctrl + Click)");
		lblSeleccionarTemasA.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(lblSeleccionarTemasA, "6, 2, 4, 1");
		
		Choice ej1TemaReferencia = new Choice();
		ej1TemaReferencia.setForeground(new Color(0, 153, 0));
		ej1TemaReferencia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(ej1TemaReferencia, "2, 4");
		for ( int i = 0 ; i < list.getModel().getSize();i++){
			ej1TemaReferencia.add(list.getModel().getElementAt(i));
		}
		//Dejar seleccionado Game of Thrones
		ej1TemaReferencia.select(1);
		
		ej1TemasComparar = new JList<String>();
		ej1TemasComparar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		ej1TemasComparar.setForeground(new Color(0, 0, 0));
		ej1TemasComparar.setFont(new Font("Verdana", Font.BOLD, 16));
		DefaultListModel<String> modelo1 = new DefaultListModel<String>();
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		pan1.add(separator_1, "4, 1, 1, 13, center, default");
		
		pan1.add(ej1TemasComparar, "6, 4, fill, fill");
		for ( int i = 0 ; i < list.getModel().getSize();i++){
			modelo1.addElement(list.getModel().getElementAt(i));
		}
		ej1TemasComparar.setModel(modelo1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		pan1.add(separator, "1, 14, 9, 1");
		
		ej1Resultado = new JList<String>();
		ej1Resultado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		ej1Resultado.setForeground(new Color(0, 0, 255));
		ej1Resultado.setFont(new Font("Verdana", Font.BOLD, 16));
		DefaultListModel<String> modeloRes1 = new DefaultListModel<String>();
		
		JButton ej1Procesar = new JButton("Procesar");
		ej1Procesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ej1Procesar();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Resultados ordenados seg\u00FAn similitud");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(lblNewLabel_1, "6, 17, default, top");
		ej1Procesar.setIcon(new ImageIcon(main.class.getResource("/imagenes/process.png")));
		ej1Procesar.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(ej1Procesar, "2, 18");
		ej1Resultado.setModel(modeloRes1);
		pan1.add(ej1Resultado, "6, 18, fill, top");
		
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


	protected void ej1Procesar() {
		//Método que compara y obtiene los temas con mayor similitud al tema referencia.
		
		//Modelo utilizado para mostrar los resultados finales
		DefaultListModel<String> modeloRes1 = new DefaultListModel<String>();
		Vector<String> resultados = new Vector<String>();
		
		
		//Procesar
		resultados.add("Aca");
		resultados.add("van");
		resultados.add("los");
		resultados.add("resultados");
		resultados.add("en un formato");
		resultados.add("[#Pos] Nombre [Track]");
		
		//----
		
		
		//Muestro los resultados
		for (String s : resultados){
			modeloRes1.addElement(s);
		}
		ej1Resultado.setModel(modeloRes1);
		
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
