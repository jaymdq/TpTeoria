package algoritmos;
import java.util.HashMap;

public class Arbol extends ArbolAbs {

	private ArbolAbs hijoIzq, hijoDer;
	
	public Arbol(ArbolAbs izq, ArbolAbs der){
		this.hijoDer = der;
		this.hijoIzq = izq;
		this.peso = der.getPeso() + izq.getPeso();
	}
	
	public HashMap<Integer,String> getCode() {
		HashMap<Integer,String> hi = hijoIzq.getCode();
		HashMap<Integer,String> hd = hijoDer.getCode();
		HashMap<Integer,String> result = new HashMap<Integer,String>();
		for (int k : hi.keySet()) {
			String s = hi.get(k);
			s = 0+s;
			result.put(k, s);
		}
		for (int k : hd.keySet()) {
			String s = hd.get(k);
			s = 1+s;
			result.put(k, s);
		}
		return result;
	}
}
