package algoritmos;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;


public final class function {


	//Esto cambiarlo (hacerlo dinamico)
	final static double e = 0.000001;
	final static int min = 100;


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

	public static double[][] getMatrizProbabilidadesCondicionales(Vector<Integer> valores){
		Vector<Integer> entrada = new Vector<Integer>();
		for (Integer i : valores) {
			if (!entrada.contains(i) ) {
				entrada.add(i);
			}
		}
		Collections.sort(entrada);

		int maxsize = entrada.size();
		double[][] retorno=new double[maxsize][maxsize];

		HashMap<Integer,Integer> frec = function.Frecuencia(valores);

		for (int i = 0 ; i < valores.size() - 1; i++) {
			retorno[entrada.indexOf(valores.elementAt(i+1))][entrada.indexOf(valores.elementAt(i))] += 1;
		}

		for (int i = 0 ; i < maxsize; i++) {
			for (int j = 0; j < maxsize; j++) {
				if (valores.lastElement() == entrada.elementAt(j))
					retorno[i][j] /= (frec.get(entrada.elementAt(j)) - 1);
				else
					retorno[i][j] /= frec.get(entrada.elementAt(j));
			}
		}
		return retorno;
	}

	public static boolean converge(HashMap<Integer,Double> A, HashMap<Integer,Double> B) {
		for( Integer i : A.keySet() ){
			if(Math.abs(A.get(i) - B.get(i)) > e)
				return false;
		}
		return true;
	}

	public static int move(double[][]matriz, int col) {
		double r = Math.random();

		for(int i=0; i < matriz.length ; i++) {
			if ( r < matriz[i][col]) {
				return i;
			}
			else {
				r -= matriz[i][col];
			}
		}
		return 0;
	}

	public static HashMap<Integer,Double> getVectorEstacionario(double[][] matriz,Vector<Integer> valores) {
		HashMap<Integer,Double> Retornar = new HashMap<Integer,Double>();
		HashMap<Integer,Double> Anterior = new HashMap<Integer,Double>();

		//Cargamos los valores
		Vector<Integer> entrada = new Vector<Integer>();
		for (Integer i : valores) {
			if ( ! entrada.contains(i) ) {
				entrada.add(i);
			}
		}
		Collections.sort(entrada);

		double [] Visitas = new double[entrada.size()]; 

		for (Integer i : entrada){
			Retornar.put(i, 0.0);
			Anterior.put(i, 0.0);
		}

		int pasos = 0;
		int s = entrada.elementAt(0);


		while (!(converge(Retornar,Anterior)) || (pasos < min)){
			for (Integer i : Retornar.keySet()) {
				Anterior.put(i,Retornar.get(i));
			}
			s = entrada.elementAt(move(matriz,entrada.indexOf(s)));
			pasos++;

			Visitas[entrada.indexOf(s)]++;
			for (Integer i : Retornar.keySet()) {
				Retornar.put(i, Visitas[entrada.indexOf(i)] / pasos);
			}
		}
		return Retornar;
	}
}
