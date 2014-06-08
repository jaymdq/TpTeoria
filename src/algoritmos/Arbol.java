package algoritmos;

public class Arbol extends ArbolAbs {

	@SuppressWarnings("unused")
	private Arbol hijoIzq, hijoDer;
	
	public Arbol(Arbol izq, Arbol der){
		this.hijoDer = der;
		this.hijoIzq = izq;
		this.peso = der.getPeso() + izq.getPeso();
	}
}
