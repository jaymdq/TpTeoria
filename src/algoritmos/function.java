package algoritmos;

import java.util.HashMap;
import java.util.Vector;


public final class function {

	public static double Media(Vector<Integer> valores){
		double result = 0.0;
		for (int i = 0; i < valores.size(); i++){
			result += valores.elementAt(i);
		}
		return (double) (result / valores.size());
	}
	
	public static HashMap<Integer, Integer> Frecuencia(Vector<Integer> valores){
		HashMap<Integer,Integer> result= new HashMap<Integer,Integer>();
		for (int i = 0; i < valores.size(); i++){
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
		double result = 0.0;
	
		for(Integer valor : valores){
			result += valor*valor;
		}
		
		result = result / valores.size();
		
		result= result- Math.pow(media, 2);
		
		return result;
	}

	
	public static double Desvio(Vector<Integer> valores){
		double varianza=Varianza(valores);
		return (double) Math.sqrt(varianza);
	}
	
	public static double Correlacion(Vector<Integer> valores1, Vector<Integer> valores2){
		double result = 0.0;
	
		for (int i = 0; i < valores1.size() ; i++){
			result += (double) (valores1.elementAt(i) * valores2.elementAt(i));
		}
	
		result /= valores1.size();
		
		return result;
	}
	
	public static double Covarianza(Vector<Integer> valores1, Vector<Integer> valores2){
		double result = 0.0;
		double correlacion = Correlacion(valores1,valores2);
		result = correlacion - ( Media(valores1) * Media(valores2) );
		
		return result;
	}
	
	public static double  CoeficienteDeCorrelacion(Vector<Integer> valores1, Vector<Integer> valores2){
		return (double) ( (double) Covarianza(valores1,valores2) / (Desvio(valores1) * Desvio(valores2) )  );
	}
}
 