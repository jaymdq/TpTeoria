package algoritmos;

import java.util.Collections;
import java.util.Vector;

public class Equivocacion {

	private double error ;

	public Equivocacion(double error){
		this.error = error;
	}

	private boolean converge(double prob, double probAnt) {
		if ( Math.abs(prob - probAnt) < error ){
			return true;
		}
		return false;
	}

	public double simular(Canal c,int tiradasMin) {

		double prob = 0.0;
		double probAnt = -1.0;
		int tiradas = 0;
		int errores = 0;

		while ( !converge(prob,probAnt) | tiradas < tiradasMin ) {
			// OBTENGO UNA ENTRADA RANDOM
			int e = c.getEntrada();

			// SIMULO 3 VECES LA POSIBLE SALIDA PARA ESA ENTRADA
			Vector<Integer> salidas = new Vector<Integer>();
			int s;
			for (int i = 0; i < 3; i++) {
				salidas.add(c.getSalida(e));
			}
			Collections.sort(salidas);
			// VERIFICO LA SUMA DE LAS SALIDAS
			int suma = 0;
			for (Integer i : salidas) {
				suma += i;
			}
			// DECIDO LA SALIDA A CONSIDERAR COMO VERDADERA
			if (suma < 14) 
				s = salidas.elementAt(0);
			else
				s = salidas.elementAt(2);

			//COMPRUEBO SI ES ERROR
			if (e != s){
				errores++;
			}
			tiradas++;
			probAnt = prob;
			prob = (double) errores / tiradas;	
		}
		return prob;
	}


	public Vector<Integer> transmitir(Canal c,Vector<Integer> entrada){
		Vector<Integer> salida = new Vector<Integer>();

		for (Integer nota : entrada){
			Vector<Integer> salidas = new Vector<Integer>();
			Integer suma = 0;
			for (int i = 0 ; i < 3 ; i++){
				salidas.add(c.getSalida(nota));
				suma += salidas.lastElement();
			}
			Collections.sort(salidas);
			if (suma < 14){
				salida.add(salidas.elementAt(0));
			}else{
				salida.add(salidas.elementAt(2));
			}
		}

		return salida;
	}


	public Double errorCuadraticoMedio(Vector<Integer> original, Vector<Integer> recibido){
		Double salida = 0.0;
		for (int i = 0 ; i < original.size(); i++){
			salida += Math.pow((recibido.elementAt(i) - original.elementAt(i)),2);
		}

		salida /= original.size();
		return salida;
	}

}