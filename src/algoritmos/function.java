package algoritmos;

import java.util.HashMap;
import java.util.Vector;


public final class function {

	public static double Media(Vector<Integer> valores){
		//Fijarse que valores no este vacio !!
		
		double result = 0.0;
		for (int i=0; i<valores.size(); i++){
			result += valores.elementAt(i);
		}
		return (double) (result / valores.size());
	}
	
	public static HashMap<Integer, Integer> Frecuencia(Vector<Integer> valores){
		HashMap<Integer,Integer> result= new HashMap<Integer,Integer>();
		for (int i=0; i<valores.size(); i++){
			if (result.containsKey(valores.elementAt(i))) {
				result.put(valores.elementAt(i), result.get(valores.elementAt(i))+1);
			}
			else {
				result.put(valores.elementAt(i), 1);
			}
		}
		return result;
	}
	
	public static HashMap<Integer, Double> Probabilidades(Vector<Integer> valores){
		HashMap<Integer,Integer> frec = Frecuencia(valores);
		HashMap<Integer,Double> result = new HashMap<Integer,Double>();
		for (Integer key : frec.keySet()){
			result.put(key, new Double( (double) frec.get(key) / valores.size()) ); 
		}
		return result;
	}
	
	public static double Varianza(Vector<Integer> valores){
		double media = Media(valores);
		double result=0;
		/*HashMap<Integer,Double> prob= Probabilidades(valores);
		for (int i=0 ; i < valores.length ; i++){
			result +=  Math.pow(valores[i]-media , 2) * prob.get(valores[i]);
		}		*/
		double aux1=0.0;
		for(int n : valores){
			aux1 += n*n;
		}
		result= aux1- Math.pow(media, 2);
		return result;
	}

	
	public static double Desvio(Vector<Integer> valores){
		double varianza=Varianza(valores);
		return (double) Math.sqrt(varianza);
	}
	
	public static double Correlacion(Vector<Integer> valores1, Vector<Integer> valores2){
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
	
	public static double Covarianza(Vector<Integer> valores1, Vector<Integer> valores2){
		double result = 0.0;
		double aux=0.0;
		/*double correlacion = Correlacion(valores1,valores2);
		result = correlacion - ( Media(valores1) * Media(valores2) );*/
		for (int i=0; i < valores1.size() || i<=250; i++){  //los dos tracks son del mismo tamaño
				aux += valores1.elementAt(i)*valores2.elementAt(i);
		}
		result= aux - (Media(valores1) * Media(valores2));
		return result;
	}
	
	public static double  CoeficienteDeCorrelacion(Vector<Integer> valores1, Vector<Integer> valores2){
		return (double) ( (double) Covarianza(valores1,valores2) / (Desvio(valores1) * Desvio(valores2) )  );
	}
}
 