package algoritmos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class Huffman {

	private static Huffman instancia = null;
	private Integer tamUltChar = 0;

	private Huffman(){

	}

	public static Huffman getInstance(){
		if (instancia == null){
			instancia = new Huffman();
		}
		return instancia;
	}



	public HashMap<Integer, String> getCodificacion (Vector<Integer> valores) {
		HashMap<Integer,Double> probs = function.Probabilidades(valores);
		Vector<ArbolAbs> nodos = new Vector<ArbolAbs>();
		for (int v: probs.keySet()) {
			Hoja h = new Hoja(v,probs.get(v));
			nodos.add(h);
		}
		while (nodos.size() > 1) {
			Collections.sort(nodos);
			ArbolAbs arbizq = nodos.elementAt(0);
			ArbolAbs arbder = nodos.elementAt(1);
			Arbol a = new Arbol(arbizq,arbder);
			nodos.removeElementAt(0);
			nodos.removeElementAt(0);
			nodos.add(a);
		}		
		return nodos.elementAt(0).getCode();
	}


	public HashMap<Integer, String> getCodificacion (Vector<Integer> valores,HashMap<Integer,Double> probs) {
		Vector<ArbolAbs> nodos = new Vector<ArbolAbs>();
		for (int v: probs.keySet()) {
			Hoja h = new Hoja(v,probs.get(v));
			nodos.add(h);
		}
		while (nodos.size() > 1) {
			Collections.sort(nodos);
			ArbolAbs arbizq = nodos.elementAt(0);
			ArbolAbs arbder = nodos.elementAt(1);
			Arbol a = new Arbol(arbizq,arbder);
			nodos.removeElementAt(0);
			nodos.removeElementAt(0);
			nodos.add(a);
		}		
		return nodos.elementAt(0).getCode();
	}



	public HashMap<Integer, Double> extenderFuente (Vector <Integer> valores) {
		Vector<Integer> entrada = new Vector<Integer>();
		for (Integer i : valores) {
			if ( ! entrada.contains(i) ) {
				entrada.add(i);
			}
		}
		Collections.sort(entrada);

		HashMap<Integer,Double> retorno = new HashMap<Integer,Double>();
		double[][] matriz = function.getMatrizProbabilidadesCondicionales(valores);
		HashMap<Integer,Double> vecEstacionario = function.getVectorEstacionario(matriz,valores);

		for (Integer i : vecEstacionario.keySet()) {
			for ( Integer j : vecEstacionario.keySet()) {
				Double valor = vecEstacionario.get(i) * matriz[entrada.indexOf(j)][entrada.indexOf(i)];
				if (valor != 0){
					retorno.put(i*10+j, valor); //en el informe lo explicamos
				}
			}
		}

		return retorno;
	}

	public HashMap<Integer, String> getCodificacionMarkoviana (Vector<Integer> valores, int orden) {
		HashMap<Integer,Double> probs;

		if (orden == 1){
			probs = function.getVectorEstacionario(function.getMatrizProbabilidadesCondicionales(valores),valores);
		}
		else {
			probs = extenderFuente(valores);
		}

		Vector<ArbolAbs> nodos = new Vector<ArbolAbs>();
		for (int v: probs.keySet()) {
			Hoja h = new Hoja(v,probs.get(v));
			nodos.add(h);
		}
		while (nodos.size() > 1) {
			Collections.sort(nodos);
			ArbolAbs arbizq = nodos.elementAt(0);
			ArbolAbs arbder = nodos.elementAt(1);
			Arbol a = new Arbol(arbizq,arbder);
			nodos.removeElementAt(0);
			nodos.removeElementAt(0);
			nodos.add(a);
		}		
		return nodos.elementAt(0).getCode();
	}


	//Método que devuelve en un String la codificacion obtenida
	public String codificar(Vector<Integer> valores){
		HashMap<Integer,String> codigo = getCodificacion(valores);
		String salidaMuestra = "";

		for (Integer valor : valores){
			salidaMuestra += codigo.get(valor);
		}

		return salidaMuestra;			
	}

	public String generaBits(char num){
		String salida = "";
		char mascara = 1 << 15;//desplaza el 1, 15 lugares a la izq (32768)
		for(int i = 0; i<16; i++){
			if((num & mascara)==32768) //si el 1º bit de num es 1
				salida += "1";
			else
				salida += "0";
			num = (char) (num << 1);
		}
		return salida;
	}

	private Vector<String> generarCodificacion (Vector<Integer> valores){
		Vector<String> salida = new Vector<String>();
		HashMap<Integer,String> codigo = getCodificacion(valores);

		//Buffer
		char buffer = 0;
		int cant_digitos = 0;

		for (Integer nota : valores){
			String cod = codigo.get(nota);
			int longitud = cod.length();
			while ( longitud > 0){
				buffer = (char) (buffer << 1);
				if ( cod.charAt(longitud-1) == '1'){
					buffer = (char) (buffer | 1);
				}
				cant_digitos++;
				if (cant_digitos == 16){
					String aux = ""+buffer;
					salida.add(aux);
					buffer = 0;
					cant_digitos = 0;
				}
				longitud--;
			}

		}
		if ( (cant_digitos < 16) && ( cant_digitos !=0 ) ){
			buffer= (char) (buffer<<(16 - cant_digitos));
			String aux = ""+buffer;
			salida.add(aux);
		}
		//Redefino la variable tamUltChar
		tamUltChar = cant_digitos;
		return salida;
	}
	
	private Vector<String> generarCodificacion (Vector<Integer> valores,HashMap<Integer,String> codigo){
		Vector<String> salida = new Vector<String>();
	
		//Buffer
		char buffer = 0;
		int cant_digitos = 0;

		for (Integer nota : valores){
			String cod = codigo.get(nota);
			int longitud = cod.length();
			while ( longitud > 0){
				buffer = (char) (buffer << 1);
				if ( cod.charAt(longitud-1) == '1'){
					buffer = (char) (buffer | 1);
				}
				cant_digitos++;
				if (cant_digitos == 16){
					String aux = ""+buffer;
					salida.add(aux);
					buffer = 0;
					cant_digitos = 0;
				}
				longitud--;
			}

		}
		if ( (cant_digitos < 16) && ( cant_digitos !=0 ) ){
			buffer= (char) (buffer<<(16 - cant_digitos));
			String aux = ""+buffer;
			salida.add(aux);
		}
		//Redefino la variable tamUltChar
		tamUltChar = cant_digitos;
		return salida;
	}

	public String decodificarCodificacion(Vector<String> recibido,int tamUltChar){
		String salida = "";

		for (int i = 0; i < recibido.size() ; i++) {
			salida += generaBits(recibido.elementAt(i).charAt(0));
		}
		salida = salida.substring(0, salida.length() - (16 - tamUltChar));

		return salida;
	}


	public void codificarAArchivo(Vector<Integer> valores,String ruta){

		Vector<String> codigoAGuardar = generarCodificacion(valores); 
		HashMap<Integer,String> codigoSimbolos = getCodificacion(valores);
		HashMap<Integer,Double> probabilidades = function.Probabilidades(valores);

		ObjectOutputStream salida;
		try {

			//Abro el archivo a donse de volcará la información.
			salida = new ObjectOutputStream(new FileOutputStream(ruta));

			//HEADER
			//Tamaño del último char.
			//Cantidad de chars de datos.
			//Cantidad de simbolos.
			// # Simbolos * [NOTA, PROBABILIDAD DE LA NOTA].

			//Almaceno el tamaño del último char.
			salida.writeShort(tamUltChar);

			//Almaceno la cantidad de chars.
			salida.writeShort(codigoAGuardar.size());

			//Almaceno la cantidad de simbolos
			salida.writeShort(codigoSimbolos.size());

			//Almaceno las codificaciones.
			for(Integer i : probabilidades.keySet()){
				//Escribo la nota
				salida.writeShort(i);

				//Escribo la probabilidad de esa nota
				salida.writeDouble(probabilidades.get(i));
			}

			//DATOS
			//Escribimos lo codificado.
			try {

				for (String c : codigoAGuardar){
					for ( int i = 0 ; i < c.length() ; i++)
						salida.writeChar(c.charAt(i));
				}
			} catch (IOException e) {}
			salida.close();
		} catch (FileNotFoundException e) {} catch (IOException e) {}


	}



	public Vector<Integer> decodificar(String ruta){

		ObjectInputStream entrada;
		int tamUltChar = 0;
		int cant_chars = 0;
		int cant_simbolos = 0;
		HashMap<Integer, Double> simbolos = new HashMap<Integer,Double>();
		Vector<Integer> salida = new Vector<Integer>();

		try {
			entrada = new ObjectInputStream(new FileInputStream(ruta));

			//HEADER

			//Leo el tamaño del ultimo char
			tamUltChar =  entrada.readShort();

			//Leo la cantidad de chars.
			cant_chars = entrada.readShort();

			//Leo la cantidad de simbolos
			cant_simbolos = entrada.readShort();

			//Leo las codificaciones.
			for(int i = 0 ; i < cant_simbolos; i++){
				Integer simbolo = (int) entrada.readShort();
				Double probabilidad = entrada.readDouble();
				simbolos.put(simbolo, probabilidad);
			}

			//Generamos la codificación para los simbolos recibidos
			Vector<Integer> simbolosRecibidos = new Vector<Integer>();
			for (Integer simb : simbolos.keySet()){
				simbolosRecibidos.add(simb);
			}
			HashMap<Integer,String> codificacionRecibida = getCodificacion(simbolosRecibidos,simbolos);

			//DATOS
			//Leemos lo codificado.
			Vector<String> lectura = new Vector<String>();
			for (int i = 0 ; i < cant_chars ; i++){
				lectura.add(String.valueOf(entrada.readChar()));

			}

			String binario = decodificarCodificacion(lectura,tamUltChar);
			String aux = "";
			for (int i = 0 ; i < binario.length() ; i++){
				aux += binario.charAt(i);
				for (Integer simbolo : codificacionRecibida.keySet()){
					if (codificacionRecibida.get(simbolo).equals(aux)){
						salida.add(simbolo);
						aux = "";
					}
				}
			}

			//Cerramos el archivo
			entrada.close();

		} catch (FileNotFoundException e) {} catch (IOException e) {}



		return salida;
	}


	private Vector<Integer> juntar(Vector<Integer> entrada){
		Vector<Integer> salida = new Vector<Integer>();

		for (int i = 0 ; i < entrada.size() ; i+=2){
			salida.add(entrada.elementAt(i) * 10 + entrada.elementAt(i+1));
		}
		return salida;
	}
	
	
	public Double getTasa(Vector<Integer> entrada,HashMap<Integer,String> orden1, HashMap<Integer,String> orden2){
		Double salida = 0.0;
		
		Vector<String> cod1 = generarCodificacion(entrada,orden1);
		Vector<String> cod2 = generarCodificacion(juntar(entrada),orden2);
		
		String c1 = "";
		for (String s : cod1){
			c1 += s;
		}
		String c2 = "";
		for (String s : cod2){
			c2 += s;
		}
		
		return (double) c1.length() / c2.length();
	}
	
	
}
