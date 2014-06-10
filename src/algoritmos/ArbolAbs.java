package algoritmos;

import java.util.BitSet;
import java.util.HashMap;

public abstract class ArbolAbs implements Comparable {
	
	protected Double peso;
	
	public Double getPeso(){
		return peso;
	}
	
	public int compareTo(Object o) {
		ArbolAbs arb = (ArbolAbs) o;
		return this.getPeso().compareTo(arb.getPeso());
	}
	
	public abstract HashMap<Integer,String> getCode();
}
