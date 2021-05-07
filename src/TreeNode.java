

public class TreeNode {

	//FIELDS
	
	private int valor;
	private TreeNode izquierda;
	private TreeNode derecha;

	
	//CONSTRUCTOR
	
	public TreeNode(int value) {
		this.valor = value;
		this.izquierda = null;
		this.derecha = null;
	}

	
	//METHODS
	
	//GETTERS & SETTERS
	
	//Complejidad: O(1)
	public TreeNode getIzq() {
		return izquierda;
	}

	//Complejidad: O(1)
	public void setIzq(TreeNode left) {
		this.izquierda = left;
	}

	//Complejidad: O(1)
	public TreeNode getDer() {
		return derecha;
	}

	//Complejidad: O(1)
	public void setRight(TreeNode right) {
		this.derecha = right;
	}

	//Complejidad: O(1)
	public int getValor() {
		return valor;
	}
	
	//EXTRA
	
	//Complejidad: O(1)
	public void setValor(int valor) {
		this.valor = valor;
	}

}
