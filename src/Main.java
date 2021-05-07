
public class Main {

	public static void main(String[] args) {
		
		// Se construye el árbol de ejemplo utilizando el constructor previamente mencionado
		int[] valoresIniciales = new int[] {15, 4, 1, 25, 50, 6, 7, 20, 5, 30};
		TreeWithNode miArbol = new TreeWithNode(valoresIniciales);
		
		miArbol.printPreOrder();
		System.out.println( "Mayor elemento: " + miArbol.getMaxElem() );
		System.out.println( "Altura: " + miArbol.getHeight() );
		System.out.println( "Rama más larga: " + miArbol.getLongestBranch() );
		System.out.println( "Nivel 2: " + miArbol.getElementAtLevel(2) );
		System.out.println( "Frontera: " + miArbol.getFrontera() );
		
		System.out.println("_______________________________________________________________");
		
		miArbol.add(23);
		miArbol.add(3);
		miArbol.delete(6);
		miArbol.delete(30);
		
		miArbol.printPreOrder();
		System.out.println( "Mayor elemento: " + miArbol.getMaxElem() );
		System.out.println( "Altura: " + miArbol.getHeight() );
		System.out.println( "Rama más larga: " + miArbol.getLongestBranch() );
		System.out.println( "Nivel 2: " + miArbol.getElementAtLevel(2) );
		System.out.println( "Frontera: " + miArbol.getFrontera() );
		
		System.out.println("_______________________________________________________________");
		
		miArbol.add(65);
		miArbol.delete(5);
		miArbol.delete(15);
		miArbol.add(55);
		
		miArbol.printPreOrder();
		System.out.println( "Mayor elemento: " + miArbol.getMaxElem() );
		System.out.println( "Altura: " + miArbol.getHeight() );
		System.out.println( "Rama más larga: " + miArbol.getLongestBranch() );
		System.out.println( "Nivel 2: " + miArbol.getElementAtLevel(2) );
		System.out.println( "Frontera: " + miArbol.getFrontera() );
		
	}

}
