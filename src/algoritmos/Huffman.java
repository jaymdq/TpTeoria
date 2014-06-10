package algoritmos;

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

	public void getCodificacion (Vector<Integer> valores) {
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
			nodos.remove(0);
			nodos.remove(1);
			nodos.add(a);
		}
		System.out.println(probs);
		System.out.println(nodos.get(0).getCode());
	}
	
	public void codificar(){
		
	}
	
	public void decodificar(){
		
	}

}
