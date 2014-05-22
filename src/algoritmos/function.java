package algoritmos;

import java.util.HashMap;


public final class function {

	public static double Media(int[] valores){
		//Fijarse que valores no este vacio !!
		
		double result = 0.0;
		for (int i=0; i<valores.length; i++){
			result += valores[i];
		}
		return (double) (result / valores.length);
	}
	
	public static HashMap<Integer, Integer> Frecuencia(int[] valores){
		HashMap<Integer,Integer> result= new HashMap<Integer,Integer>();
		for (int i=0; i<valores.length; i++){
			if (result.containsKey(valores[i])) {
				result.put(valores[i], result.get(valores[i])+1);
			}
			else {
				result.put(valores[i], 1);
			}
		}
		return result;
	}
	
	public static HashMap<Integer, Double> Probabilidades(int[] valores){
		HashMap<Integer,Integer> frec = Frecuencia(valores);
		HashMap<Integer,Double> result = new HashMap<Integer,Double>();
		for (Integer key : frec.keySet()){
			result.put(key, new Double( (double) frec.get(key) / valores.length) ); 
		}
		return result;
	}
	
	public static double Varianza(int[] valores){
		double media = Media(valores);
		double result=0;
		HashMap<Integer,Double> prob= Probabilidades(valores);
		for (int i=0 ; i < valores.length ; i++){
			result +=  Math.pow(valores[i]-media , 2) * prob.get(valores[i]);
		}		
		return result;
	}

	
	public static double Desvio(int[] valores){
		double varianza=Varianza(valores);
		return (double) Math.sqrt(varianza);
	}
	
	public static double Correlacion(int[] valores1, int[] valores2){
		double result = 0.0;
		HashMap<Integer,Double> p1= Probabilidades(valores1);
		HashMap<Integer,Double> p2= Probabilidades(valores2);
		for (Integer i : p1.keySet()){
			for (Integer j : p2.keySet()){
				result += (double) ( i * j * p1.get(i) * p2.get(j) );
			}  //valores1 y valores 2 son independientes sus probabilidades
		}
		return result;
	}
	
	public static double Covarianza(int[] valores1, int[] valores2){
		double result = 0.0;
		double correlacion = Correlacion(valores1,valores2);
		result = correlacion - ( Media(valores1) * Media(valores2) );
		return result;
	}
	
	public static double  CoeficienteDeCorrelacion(int[] valores1, int[] valores2){
		return (double) ( (double) Covarianza(valores1,valores2) / (Desvio(valores1) * Desvio(valores2) )  );
	}
}
 