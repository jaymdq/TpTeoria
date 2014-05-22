package algoritmos;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Vector;


public class Huffman {
    private HashMap<Integer,BitSet> codif;
    
	public Huffman(Vector<Integer> valores){
		codif=new HashMap<Integer,BitSet>();
	    HashMap<Integer,Double> prob= function.Probabilidades(valores);
	    
	}
	
}
