package algoritmos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class Canal {
   private Vector<Integer> entrada = new Vector<Integer>();
   private Vector<Integer> salida = new Vector<Integer>();
   private double[][] matrizCanal;
    
   private HashMap<Integer, Double> probIn;
   //private HashMap<Integer, Double> probOut;
   
   public Canal(Vector<Integer> in, Vector<Integer> out) {

   // Probabilidades de entrada y salida
   this.probIn = function.Probabilidades(in);
   //this.probOut = function.Probabilidades(out);
   
   // Elementos de entrada y elementos de salida
   for (Integer i : in) {
    	if ( ! this.entrada.contains(i) ) {
    		this.entrada.add(i);
    	}
    }
    for (Integer i : out) {
    	if ( ! this.salida.contains(i) ) {
    		this.salida.add(i);
    	}
    }
    Collections.sort(this.entrada);
    Collections.sort(this.salida);

    // Matriz Canal
   
    this.matrizCanal = new double[this.salida.size()][this.entrada.size()];
   
    for (int i = 0; i < in.size(); i++) {
    	this.put(in.elementAt(i),out.elementAt(i), 1 + this.get(in.elementAt(i),out.elementAt(i)));
    }
    
    for ( int i = 0; i < this.salida.size() ; i++){
    	for ( int j = 0 ; j < this.entrada.size(); j++){
    		this.matrizCanal[i][j] = (double) this.matrizCanal[i][j] / (in.size() * this.probIn.get(this.entrada.elementAt(j)));
    	}
    }
   }
  
   // COLOCA UN VALOR EN EL CASILLERO DE LA ENTRADA Y LA SALIDA INDICADAS
   public void put (int entrada, int salida, double valor) {
	  this.matrizCanal[this.salida.indexOf(salida)][this.entrada.indexOf(entrada)] = valor;
   }
	 
   // OBTIENE EL VALOR DEL CASILLERO DE LA ENTRADA Y SALIDA INDICADAS
   public double get (int entrada, int salida) {
	  return this.matrizCanal[this.salida.indexOf(salida)][this.entrada.indexOf(entrada)];
   }
	
   // RETORNA LA MATRIS CONJUNTA
   public double[][] getMatrizcanal() {
	  return this.matrizCanal;
   }
  
  // RETORNA LAS ENTRADAS EN ORDEN CRECIENTE (EJE X DE LA MATRIZ)
   public Vector<Integer> getSimbolosEntrada() {
	  return this.entrada;
   }
  
   // RETORNA LAS SALIDAS EN ORDEN CRECIENTE (EJE Y DE LA MATRIZ)
   public Vector<Integer> getSimbolosSalida() {
	  return this.salida;
   }


   // OBTIENE UNA SALIDA RANDOM DADA UNA ENTRADA
   public int getSalida(int entrada) {
	  
	   double r = Math.random();
	   int sal = 0;
	   
	 //  double p = this.probIn.get(entrada);
	  
	   
	   double[][] matAcum = getMatrizcanal();
	   
	   
	   for (int i = 1 ; i < salida.size(); i++){
		   for ( int j = 0 ; j < this.entrada.size();j++){
			   matAcum[i][j] += matAcum[i-1][j];
		   }
	   }
	   
	   
	    for (int i = 0 ; i < salida.size() ; i++) {
	    	if (r < matAcum[i][this.entrada.indexOf(entrada)]){
	    		return salida.elementAt(i);
	    	}
	   }
	   
	
	   return sal;
   }
  
  // OBTIENE UNA ENTRADA RANDOM
  public int getEntrada() {
	  double r = Math.random();
	  int sal = 0;
	  
	  for (Integer k : this.probIn.keySet()) {
		  double p = this.probIn.get(k);
		  if (r < p){
			  sal = k;
			  break;
		  }
		  else
			  r -= p;
	  }
	  return sal;
  }
  
  
}