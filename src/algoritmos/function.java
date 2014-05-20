package algoritmos;

import java.util.HashMap;


public final class function {

	public static double Media(int[] valores){
		//Fijarse que valores no este vacio !!
		
		double result=0;
		for (int i=0; i<valores.length; i++){
			result+=valores[i];
		}
		
		return result/valores.length;
	}
	
	@SuppressWarnings("rawtypes")
	public static HashMap Frecuencia(int[] valores){
		HashMap<Integer,Integer> result= new HashMap<Integer,Integer>();
		for (int i=0; i<valores.length; i++){
			if (result.containsKey(valores[i])) {
				result.put(valores[i], result.get(valores[i]+1));
			}
			else {
				result.put(valores[i], 1);
			}
		}
		return result;
	}
	
	public static HashMap Probabilidades(int[] valores){
		HashMap<Integer,Integer> frec = Frecuencia(valores);
		HashMap<Integer,Double> result = new HashMap<Integer,Double>();
		for (Integer key:frec.keySet()){
			result.put(key,(double) (frec.get(key)/valores.length)); 
		}
		return result;
	}
	
	public static double Varianza(int[] valores){
		double media= Media(valores);
		double result=0;
		HashMap<Integer,Double> prob= Probabilidades(valores);
		for (int i=0; i<valores.length; i++){
			result+=(Math.pow(valores[i]-media,2))*prob.get(valores[i]);
		}		
		return result;
	}

	
	public static double Desvio(int[] valores){
		double varianza=Varianza(valores);
		return Math.sqrt(varianza);
	}
	
	public static double Correlacion(int[] valores1, int[] valores2){
		double result=0;
		HashMap<Integer,Double> p1= Probabilidades(valores1);
		HashMap<Integer,Double> p2= Probabilidades(valores2);
		for (int i=0; i<valores1.length; i++){
			for (int j=0; j<valores2.length; j++){
				result+=valores1[i]*valores2[j]*p1.get(valores1[i])*p2.get(valores2[j]);
			}  //valores1 y valores 2 son independientes sus probabilidades
		}
		return result;
	}
	
	
}
 