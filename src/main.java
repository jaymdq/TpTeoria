import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import algoritmos.Huffman;
import algoritmos.function;

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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTabbedPane;

import java.awt.Choice;

import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JComboBox;

import java.awt.FlowLayout;

import com.jgoodies.forms.layout.Sizes;


public class main {

	private JFrame frmTpTeora;
	private JList<String> list;
	private JList<String> ej1Resultado;
	private JList<String> ej1TemasComparar;
	private Choice ej1TemaReferencia;
	private JButton ej1Procesar;
	private JLabel txtRes1;
	private Choice ej2TemaMasParecido;
	private Choice ej2TemaReferencia;
	private Choice ej2TemaMenosParecido;
	private JLabel BarraDeEstado;
	private JLabel ej2MediaMenos;
	private JLabel ej2MediaMas;
	private JLabel ej2MediaReferencia;
	private JLabel ej2DesvioMenos;
	private JLabel ej2DesvioMas;
	private JLabel ej2DesvioRef;
	private JLabel ej2Histograma;
	
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
		
		JPanel Barra = new JPanel();
		Barra.setBackground(new Color(255, 255, 153));
		FlowLayout flowLayout = (FlowLayout) Barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		Barra.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmTpTeora.getContentPane().add(Barra, BorderLayout.SOUTH);
		
		BarraDeEstado = new JLabel("Ejecute el ejercicio 1 para definir el tema de referencia y los m\u00E1s y menos parecidos.");
		BarraDeEstado.setForeground(new Color(255, 0, 0));
		BarraDeEstado.setHorizontalAlignment(SwingConstants.LEADING);
		BarraDeEstado.setFont(new Font("Verdana", Font.BOLD, 16));
		Barra.add(BarraDeEstado);
		
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
				ColumnSpec.decode("220dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				ColumnSpec.decode("30dlu"),},
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
				RowSpec.decode("5dlu"),
				RowSpec.decode("max(6dlu;default)"),
				RowSpec.decode("16dlu"),
				RowSpec.decode("max(101dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(13dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(97dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Seleccionar Tema de Referencia");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(lblNewLabel, "2, 2");
		
		JLabel lblSeleccionarTemasA = new JLabel("Seleccionar Temas a comparar (Ctrl + Click)");
		lblSeleccionarTemasA.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(lblSeleccionarTemasA, "6, 2, 4, 1");
		
		ej1TemaReferencia = new Choice();
		ej1TemaReferencia.setForeground(new Color(0, 153, 0));
		ej1TemaReferencia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(ej1TemaReferencia, "2, 4");
		for ( int i = 0 ; i < list.getModel().getSize();i++){
			ej1TemaReferencia.add(list.getModel().getElementAt(i));
		}
		//Dejar seleccionado Game of Thrones
		ej1TemaReferencia.select(1);
		
		ej1TemasComparar = new JList<String>();
		ej1TemasComparar.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (ej1TemasComparar.isSelectionEmpty()){
					ej1Procesar.setEnabled(false);
				}else
					ej1Procesar.setEnabled(true);
			}
		});
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
		
		ej1Procesar = new JButton("Procesar");
		ej1Procesar.setEnabled(false);
		ej1Procesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ej1Procesar();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Coeficientes de Correlaci\u00F3n");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(lblNewLabel_1, "6, 17, default, top");
		ej1Procesar.setIcon(new ImageIcon(main.class.getResource("/imagenes/process.png")));
		ej1Procesar.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(ej1Procesar, "2, 18, default, top");
		ej1Resultado.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"1.", "2.", "3.", "4.", "5.", "6."};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		pan1.add(ej1Resultado, "6, 18, fill, top");
		
		txtRes1 = new JLabel("");
		txtRes1.setFont(new Font("Verdana", Font.BOLD, 16));
		pan1.add(txtRes1, "2, 20, 5, 1");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		pan1.add(separator_2, "1, 22, 9, 1");
		
		JLabel Ejercicio1Texto = new JLabel("<html>Ejercicio 1) Dado el tema de apertura de la serie Game of Thrones (tema de referencia) y una lista de canciones seleccionadas por la empresa, implementar un algoritmo que permita ordenar estas \u00FAltimas seg\u00FAn su parecido con el tema original, utilizando el factor de correlaci\u00F3n como medida de similitud. </html>");
		Ejercicio1Texto.setFont(new Font("Verdana", Font.BOLD, 16));
		Ejercicio1Texto.setVerticalAlignment(SwingConstants.TOP);
		pan1.add(Ejercicio1Texto, "2, 24, 6, 1, default, top");
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 2", null, pan2, null);
		pan2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.MINIMUM, Sizes.constant("35dlu", true), Sizes.constant("70dlu", true)), 0),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.PREFERRED, Sizes.constant("35dlu", true), Sizes.constant("70dlu", true)), 0),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTemaReferencia = new JLabel("Tema Referencia");
		lblTemaReferencia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblTemaReferencia, "2, 2, left, default");
		
		ej2TemaReferencia = new Choice();
		ej2TemaReferencia.setForeground(Color.GREEN);
		ej2TemaReferencia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2TemaReferencia, "4, 2, left, default");
		for ( int i = 0 ; i < list.getModel().getSize();i++){
			ej2TemaReferencia.add(list.getModel().getElementAt(i));
		}
		//Dejar seleccionado Game of Thrones
		ej2TemaReferencia.select(1);
		
		JLabel lblNewLabel_2 = new JLabel("Media: ");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblNewLabel_2, "6, 2");
		
		ej2MediaReferencia = new JLabel("");
		ej2MediaReferencia.setForeground(new Color(51, 204, 0));
		ej2MediaReferencia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2MediaReferencia, "7, 2");
		
		JLabel lblDesvo = new JLabel("Desv\u00EDo: ");
		lblDesvo.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblDesvo, "10, 2");
		
		ej2DesvioRef = new JLabel("");
		ej2DesvioRef.setForeground(new Color(204, 0, 0));
		ej2DesvioRef.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2DesvioRef, "12, 2");
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setFont(new Font("Verdana", Font.BOLD, 16));
		btnProcesar.setIcon(new ImageIcon(main.class.getResource("/imagenes/process.png")));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ej2Procesar();
			}
		});
		pan2.add(btnProcesar, "14, 2, 1, 5, default, fill");
		
		
		JLabel lblTemaMsParecido = new JLabel("Tema m\u00E1s parecido");
		lblTemaMsParecido.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblTemaMsParecido, "2, 4, left, default");
		
		ej2TemaMasParecido = new Choice();
		ej2TemaMasParecido.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2TemaMasParecido, "4, 4, left, default");
		for ( int i = 0 ; i < list.getModel().getSize();i++){
			ej2TemaMasParecido.add(list.getModel().getElementAt(i));
		}
		
		JLabel lblNewLabel_3 = new JLabel("Media: ");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblNewLabel_3, "6, 4");
		
		ej2MediaMas = new JLabel("");
		ej2MediaMas.setForeground(new Color(51, 204, 0));
		ej2MediaMas.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2MediaMas, "7, 4");
		
		JLabel lblNewLabel_4 = new JLabel("Desv\u00EDo: ");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblNewLabel_4, "10, 4");
		
		ej2DesvioMas = new JLabel("");
		ej2DesvioMas.setForeground(new Color(204, 0, 0));
		ej2DesvioMas.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2DesvioMas, "12, 4");
		
		JLabel lblTemaMenosParecido = new JLabel("Tema menos parecido");
		lblTemaMenosParecido.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblTemaMenosParecido, "2, 6, left, default");
			
		ej2TemaMenosParecido = new Choice();
		ej2TemaMenosParecido.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2TemaMenosParecido, "4, 6, left, default");
		
		JLabel lblMedia = new JLabel("Media:");
		lblMedia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblMedia, "6, 6");
		
		ej2MediaMenos = new JLabel("");
		ej2MediaMenos.setFont(new Font("Verdana", Font.BOLD, 16));
		ej2MediaMenos.setForeground(new Color(51, 204, 0));
		pan2.add(ej2MediaMenos, "7, 6");
		
		JLabel lblNewLabel_5 = new JLabel("Desv\u00EDo: ");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(lblNewLabel_5, "10, 6");
		
		ej2DesvioMenos = new JLabel("");
		ej2DesvioMenos.setForeground(new Color(204, 0, 0));
		ej2DesvioMenos.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(ej2DesvioMenos, "12, 6");
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(0, 0, 0));
		pan2.add(separator_3, "1, 8, 15, 1, fill, default");
		
		JButton btnHistogramaTemaReferencia = new JButton("Histograma Tema Referencia");
		btnHistogramaTemaReferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ej2HistogramaReferencia();
			}
		});
		btnHistogramaTemaReferencia.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(btnHistogramaTemaReferencia, "2, 10, 3, 1");
		
		JButton btnHistogramaTemaMs = new JButton("Histograma Tema M\u00E1s Parecido");
		btnHistogramaTemaMs.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(btnHistogramaTemaMs, "2, 12, 3, 1");
		
		JButton btnHistogramaTemaMenos = new JButton("Histograma Tema Menos Parecido");
		btnHistogramaTemaMenos.setFont(new Font("Verdana", Font.BOLD, 16));
		pan2.add(btnHistogramaTemaMenos, "2, 14, 3, 1");
		
		ej2Histograma = new JLabel("");
		pan2.add(ej2Histograma, "2, 16, 13, 1, center, default");
		
		for ( int i = 0 ; i < list.getModel().getSize();i++){
			ej2TemaMenosParecido.add(list.getModel().getElementAt(i));
		}
		
		JPanel pan3 = new JPanel();
		pan3.setBackground(new Color(255, 255, 255));
		tabs.addTab("Ej 3", null, pan3, null);
		pan3.setLayout(new BorderLayout(0, 0));
			
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

	//Método que te devuelve el track para la canción pasada por parámetro.
	protected int getTrack(String name){
		if (name.equals("Game Of Thrones.mid")){
			return TRACK_GOF;
		}
		else 
		if (name.equals("Damas Gratis.mid")){
			return TRACK_DG;
		}
		else 
		if (name.equals("Juego De Capos.mid")){
			return TRACK_JDC;
		}
		else
		if (name.equals("The Big Bang Theory.mid")){
		    return TRACK_TBBT;
		}
		else
		if (name.equals("The Simpsons.mid")){
			return TRACK_TS;
	 	}
		else 
			return TRACK_YB;
	}
	
	protected Vector<Pair<String,Double>> insOrdenado(String clave, double value,Vector<Pair<String,Double>> valores){
		boolean insertado=false;
		for (int i = 0; i < valores.size() && !insertado; i++){
			if (valores.elementAt(i).compareTo(value) == -1){
				Pair<String, Double> nuevo = new Pair<String,Double>(clave,value);
				valores.insertElementAt(nuevo, i);
				insertado=true;
			}
		}
		if (!insertado){
			Pair<String, Double> nuevo = new Pair<String,Double>(clave,value);
			valores.add(nuevo);
		}
		return valores;
	}
	
	
	protected Vector<Integer> get250(Vector<Integer> entrada){
		Vector<Integer> salida = new Vector<Integer>();
		for (int i = 0 ; i < 250 ; i++){
			salida.add(entrada.get(i));
		}
		return salida;
	}
	
	protected void ej1Procesar() {
		//Método que compara y obtiene los temas con mayor similitud al tema referencia.
		
		//Modelo utilizado para mostrar los resultados finales
		DefaultListModel<String> modeloRes1 = new DefaultListModel<String>();
		Vector<String> resultados = new Vector<String>();
		Vector<Integer> trackReferencia = null;
		
		//estructuras usadas por comparar
		HashMap<String,Vector<Integer>> songs= new HashMap<String,Vector<Integer>>();
		Vector<Pair<String,Double>> valores= new Vector<Pair<String,Double>>();
		Vector<Pair<String,Double>> valoresSimilitud= new Vector<Pair<String,Double>>();
		
		
		//Tema de Referencia
		try {
			Vector<Integer> track= get250(ReadMIDI.getInstance().getNotes("src/midis/" + ej1TemaReferencia.getSelectedItem(),this.getTrack(ej1TemaReferencia.getSelectedItem())));
			for ( int i = 0 ; i < track.size() ; i++){
				int h = track.elementAt(i) / 10;
				track.set(i, h);
			}
			//songs.put(ej1TemaReferencia.getSelectedItem(), track);
			trackReferencia = track;
		} catch (Exception e) {}
		
		
		//Temas a Comparar
		for(String cancion : ej1TemasComparar.getSelectedValuesList()){
			try {
				Vector<Integer> track = get250(ReadMIDI.getInstance().getNotes("src/midis/" + cancion,this.getTrack(cancion)));
				for ( int i = 0 ; i < track.size() ; i++){
					int h = track.elementAt(i) / 10;
					track.set(i, h);
				}
				songs.put(cancion, track);
			} catch (Exception e) {}
		}
		
		
		//Comparo
		for (String clave : songs.keySet()){
			Vector<Integer> trackSeleccionado = songs.get(clave);
			double res = function.CoeficienteDeCorrelacion(trackReferencia, trackSeleccionado);
			insOrdenado(clave,res,valores);
			insOrdenado(clave,Math.abs(res),valoresSimilitud);
		}

		DecimalFormat df = new DecimalFormat("0.0000");
		//Muestro resultados de haber comparado
		for (int i=0; i < valores.size(); i++){
			resultados.add("["+ (i+1) +"]: " + valores.elementAt(i).getFirst() +" , [Valor]: " + df.format(valores.elementAt(i).getSecond()) );
		}
		
		//Muestro los resultados
		modeloRes1.clear();
		for (String s : resultados){
			modeloRes1.addElement(s);
		}
		ej1Resultado.setModel(modeloRes1);
		BarraDeEstado.setForeground(Color.black);
		BarraDeEstado.setText("Tema más parecido : \"" + valoresSimilitud.elementAt(0).getFirst() + "\" , Tema menos parecido : \"" + valoresSimilitud.elementAt(valoresSimilitud.size() - 1 ).getFirst() + "\".");
		
		//Actualizo campos de demás ejercicios
		ej2TemaMasParecido.select(valoresSimilitud.elementAt(0).getFirst());
		ej2TemaMenosParecido.select(valoresSimilitud.elementAt(valoresSimilitud.size() - 1 ).getFirst());
	}
	
	protected void ej2Procesar(){
		//Método que calcula media y desvio del tema de referencia y de la cancion mas y 
		//menos parecida. Luego genera el histograma
		
		Vector<Integer> trackReferencia = null;
		Vector<Integer> trackMas = null;
		Vector<Integer> trackMenos = null;
		
		
		//Tema de Referencia
		try {
			Vector<Integer> track= get250(ReadMIDI.getInstance().getNotes("src/midis/" + ej2TemaReferencia.getSelectedItem(),this.getTrack(ej2TemaReferencia.getSelectedItem())));
			for ( int i = 0 ; i < track.size() ; i++){
				int h = track.elementAt(i) / 10;
				track.set(i, h);
			}
			//songs.put(ej1TemaReferencia.getSelectedItem(), track);
			trackReferencia = track;
		} catch (Exception e) {}
		
		//Tema Mas Parecido
		try {
			Vector<Integer> track= get250(ReadMIDI.getInstance().getNotes("src/midis/" + ej2TemaMasParecido.getSelectedItem(),this.getTrack(ej2TemaMasParecido.getSelectedItem())));
			for ( int i = 0 ; i < track.size() ; i++){
				int h = track.elementAt(i) / 10;
				track.set(i, h);
			}
			//songs.put(ej1TemaReferencia.getSelectedItem(), track);
			trackMas = track;
		} catch (Exception e) {}
		
		//Tema Menos Parecido
		try {
			Vector<Integer> track= get250(ReadMIDI.getInstance().getNotes("src/midis/" + ej2TemaMenosParecido.getSelectedItem(),this.getTrack(ej2TemaMenosParecido.getSelectedItem())));
			for ( int i = 0 ; i < track.size() ; i++){
				int h = track.elementAt(i) / 10;
				track.set(i, h);
			}
			//songs.put(ej1TemaReferencia.getSelectedItem(), track);
			trackMenos = track;
		} catch (Exception e) {}


		DecimalFormat df = new DecimalFormat("0.0000");
		
		double MediaReferencia = function.Media(trackReferencia);
		double MediaMas = function.Media(trackMas);
		double MediaMenos = function.Media(trackMenos);
		ej2MediaReferencia.setText(df.format(MediaReferencia));
		ej2MediaMas.setText(df.format(MediaMas));
		ej2MediaMenos.setText(df.format(MediaMenos));
		
		double DesvioReferencia = function.Desvio(trackReferencia);
		double DesvioMas = function.Desvio(trackMas);
		double DesvioMenos = function.Desvio(trackMenos);
		ej2DesvioRef.setText(df.format(DesvioReferencia));
		ej2DesvioMas.setText(df.format(DesvioMas));
		ej2DesvioMenos.setText(df.format(DesvioMenos));
	
	}
	
	
	
	protected void ej2HistogramaReferencia() {
	
		//Tema de Referencia
		try {
			Vector<Integer> track= get250(ReadMIDI.getInstance().getNotes("src/midis/" + ej2TemaReferencia.getSelectedItem(),this.getTrack(ej2TemaReferencia.getSelectedItem())));
			for ( int i = 0 ; i < track.size() ; i++){
				int h = track.elementAt(i) / 10;
				track.set(i, h);
			}
			//songs.put(ej1TemaReferencia.getSelectedItem(), track);
			trackReferencia = track;
		} catch (Exception e) {}

				
		Image histograma = Histograma.getInstance().crearPanel(800,300);
		ej2Histograma.setIcon(new ImageIcon(histograma));
		
		
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
