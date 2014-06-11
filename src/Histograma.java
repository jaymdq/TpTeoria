import java.awt.Image;
import java.util.HashMap;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import algoritmos.function;


public class Histograma {

	private static Histograma instancia;
	
	private Histograma() {
		
	} 
	
	public static Histograma getInstance(){
		if (instancia == null){
			instancia = new Histograma();
		}
		return instancia;
	}

	 private static DefaultCategoryDataset crearDataset(Vector<Integer> track) {
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		 HashMap<Integer, Double> a = function.Probabilidades(track);
		 
		 for (Integer i : a.keySet() ){	 
			 dataset.addValue((double) a.get(i), "Notas", i);
		 }
		 
		 return dataset;
	 } 

	

	 private static JFreeChart crearChart(DefaultCategoryDataset dataset,String nombre) {

		 JFreeChart chart = ChartFactory.createBarChart(
				 nombre,
				 null,
				 "Probabilidad de Ocurrencia",
				 dataset,
				 PlotOrientation.VERTICAL,
				 true,
				 true,
				 false
				 );
		 return chart;
	 } 
 
	 public Image crearHistograma(int ancho, int alto,Vector<Integer> track,String nombre) {
		 JFreeChart chart = crearChart(crearDataset(track),nombre);
		 
		 return chart.createBufferedImage(ancho, alto);
	 }

}
