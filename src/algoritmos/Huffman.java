package algoritmos;

public class Huffman {
  
	private static Huffman instancia = null;

	private Huffman(){
		
	}
	
	public Huffman getInstance(){
		if (instancia == null){
			instancia = new Huffman();
		}
		return instancia;
	}
	
	public void codificar(){
		
	}
	
	public void decodificar(){
		
	}

}
