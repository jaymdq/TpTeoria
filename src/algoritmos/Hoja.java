package algoritmos;

import java.util.HashMap;

public class Hoja extends ArbolAbs {

	private Integer simbolo = null;
	
	public Hoja(Integer simbolo,Double peso){
		this.peso = peso;
		this.simbolo = simbolo;
	}
	
	public HashMap<Integer,String> getCode() {
		HashMap<Integer,String> h = new HashMap<Integer,String>();
		h.put(simbolo,"");
		return h;
	}
 
}
