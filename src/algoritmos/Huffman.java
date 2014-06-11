package algoritmos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class Huffman {
  
	private static Huffman instancia = null;
	
	private Huffman(){
		
	}
	
	public static Huffman getInstance(){
		if (instancia == null){
			instancia = new Huffman();
		}
		return instancia;
	}

	@SuppressWarnings("unchecked")
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
	
	public String codificar(Vector<Integer> valores){
		HashMap<Integer,String> codigo = getCodificacion(valores);
		String salidaMuestra = "";
		
		for (Integer valor : valores){
			salidaMuestra += codigo.get(valor);
		}
			
		return salidaMuestra;			
	}
	
	public void codificarAArchivo(Vector<Integer> valores,String ruta){
		String codigo = codificar(valores);
		BitSet b = new BitSet();

		for (int i = 0; i < codigo.length() ; i++){
			if ( codigo.charAt(i) == '0'){
				b.set(i,false);
			}else{
				b.set(i,true);
			}
		}

		FileOutputStream salida;
		try {
			salida = new FileOutputStream(ruta);
			HashMap<Integer, String> a = getCodificacion(valores);
			//Almaceno la cantidad de simbolos
			salida.write(a.size());
			//Almaceno las codificaciones.
			for(Integer i : a.keySet()){
				//Escribo la nota
				salida.write(i);
				//Escribo el codigo de esa nota
				salida.write(a.get(i).getBytes());
				//Escribo un salto para delimitar (EOT = End Of Transmission = 4)
				salida.write(4);
			}
			//Escribimos lo codificado.
			try {
				salida.write(b.toByteArray());
			} catch (IOException e) {}

		} catch (FileNotFoundException e) {} catch (IOException e) {}
	}

	
	
	public String decodificar(String ruta){
		FileInputStream entrada;
		int simbolos = 0;
		HashMap<Integer, String> a = new HashMap<Integer,String>();
		String aux = "";
		
		
		try {
			entrada = new FileInputStream(ruta);
			//Leo la cantidad de simbolos
			simbolos = entrada.read();
			//Leo las codificaciones.
			for(int i = 0 ; i < simbolos; i++){
				String codif = "";
				Integer simbolo = entrada.read();
				int lectura = entrada.read();
				while (lectura != 4){
					codif += lectura;
					lectura = entrada.read();
				}
				a.put(simbolo, codif);
			}
			//Leemos lo codificado.
			try {
				int lectura = entrada.read();
				while(lectura != -1){
					Byte b = new Byte((byte) lectura);
					BitSet j = new BitSet(lectura);
					for ( int z = 0 ; z < 8 ; z++){
						if (j.get(z)){
							//1
							aux+=1;
						}else
							aux+=0;
			
					}
				}
				
				
			} catch (IOException e) {}

		} catch (FileNotFoundException e) {} catch (IOException e) {}
		
		System.out.println("Dale dios : "+ aux);
		
		return ruta;
	}

}
