

import java.util.ArrayList;

public class TreeWithNode {

	
	//FIELDS
	
	private TreeNode raiz;

	
	//CONSTRUCTOR
	
	public TreeWithNode() {
		this.raiz = null;
	}
	
	
	//EXTRA
	
	/*Carga los valores dados en un array a un árbol binario de búsqueda.*/
	
	//Complejidad: O(n*h)  con h <= n, donde n es la cantidad de elementos 
	//del array que se insertarán en el árbol, y h la altura del árbol.
	//En el peor caso, debe insertar siempre los n elementos en la última posición,
	//recorriendo tantos arcos como la altura definida actual del árbol
	//por cada recorrido.
	
	public TreeWithNode(int[] valoresIniciales){
		
		createTreeByArray(valoresIniciales);	//O(n*h)
		
	}
	
	/*Carga los valores dados en un array a un árbol binario de búsqueda.*/
	
	//Complejidad: O(n*h)  con h <= n, donde n es el tamaño del arreglo
	//(equivalente a la cantidad de nodos que integraran el árbol,
	//y h es la altura del árbol. En el peor caso, por cada elemento a insertar del
	//array debe colocarlo como último nodo en la rama de mayor longitud,
	//es decir h pasos hasta llegar al lugar. Este proceso lo repite n veces,
	//por cada elemento a agregar.
	
	private void createTreeByArray(int[] valoresIniciales){
		
		for(int i=0; i < valoresIniciales.length; i++){	//O(n)
			this.add(valoresIniciales[i]); 		//O(h)
		}
		
	}
	//METHODS

	//SKELETON
	
	//---------------------------------void insert(Object)---------------------------------
	
	
	/*Este método permite no romper encapsulamiento (no revelar cómo está construido el arbol)
	 * al insertarle un elemento desde el main*/
	
	//Complejidad: O(h) donde h es la altura del arbol (la longitud de la rama mas larga)
	//En el peor caso tiene que llegar al nodo de la rama más larga.
	
	public void add(int value) {
		if (this.raiz == null)
			this.raiz = new TreeNode(value);
		else
			this.insert(this.raiz,value);	//O(h)
	}
	
	
	/*Inserta un elemento en el arbol de búsqueda binaria, colocando los elementos menores
	 *a su izquierda y los mayores a la derecha. Recorre los nodos verificando si el valor
	 *dado es menor o mayor al actual; según cual sea el lugar (como hijo) correspondiente,
	 *si está ocupado repite el procedimiento desde este nodo (hijo), y sino lo inserta.*/
	
	//Complejidad: O(h) donde h es la altura del arbol (la longitud de la rama mas larga)
	//En el peor caso tiene que llegar al nodo de la rama más larga.
	
	private void insert(TreeNode actual, int valor) {
		if (actual.getValor() > valor) {
			
			if (actual.getIzq() == null) { 
				TreeNode temp = new TreeNode(valor);
				actual.setIzq(temp);
			} else {
				insert(actual.getIzq(),valor);	//O(h)
			}
			
		} else {
			
			if (actual.getDer() == null) { 
				TreeNode temp = new TreeNode(valor);
				actual.setRight(temp);
			} else {
				insert(actual.getDer(),valor);	//O(h)
			}
			
		}
	}
	
	
	//---------------------------------int getHeight()---------------------------------
	
	
	/*Retorna la altura del arbol, es decir, la cantidad de arcos que tiene la rama más larga.
	 *sin romper encapsulamiento*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para saber cuál es la altura.
	
	public int getHeight() {
		if (this.raiz == null)
			return 0;
		else
			return this.getHeight(this.raiz); // O(n)
	}
	
	
	/*Recorre todo el arbol de izquierda a derecha hasta llegar a null de las mismas.
	 * Acumula la cantidad de arcos por los que pasa de las ramas izquierda
	 * y derecha del nodo actual, y se queda con el valor mayor. 
	 * Tanto una raiz sin hijos como las hojas tienen altura 0, ya que no presentan arcos.*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para saber cuál es la altura.
	
	private int getHeight(TreeNode cursor) {
		
		if (cursor.getIzq() == null && cursor.getDer() == null) { // Si es una hoja
			return 0; // Soy chato, tengo altura 0
		} else {
			// Si tengo al menos un hijo
			
			int alturaIzq = 0;
			int alturaDer = 0;
			
			if (cursor.getIzq() != null)
				alturaIzq = 1 + this.getHeight(cursor.getIzq()); //O(n)
			
			if (cursor.getDer() != null)
				alturaDer = 1 + this.getHeight(cursor.getDer()); //O(n)
			
			int mayor = Math.max(alturaIzq, alturaDer);
			
			return mayor;	
		}
		
	}
	
	
	//EXTRA
	
	
	//---------------------------------Object getRoot()---------------------------------
	
	
	//Retorna el dato de la raíz del arbol.
	
	//Complejidad: O(1)
	
	public Integer getRoot(){
		if(this.raiz == null)
			return null;
		else
			return this.raiz.getValor();
	}
	
	
	//---------------------------------boolean hasElem(Object)---------------------------------
	
	
	/*Permite buscar un elemento sin romper encapsulamiento.*/
	
	//Complejidad: O(h) donde h es la altura del arbol (la cantidad de arcos)
	
	public boolean hasElem(int valorBuscado){
		if (this.isEmpty())	//O(1)
			return false;
		else
			return this.hasElem(this.raiz, valorBuscado);	//O(h)
	}
	
	
	/*Recorre el arbol por izquierda si el valor buscado es menor o por derecha 
	 * si es mayor al dato del nodo actual. Deja de recorrer cuando hay coincidencia
	 *de valores o bien, cuando no existe nodo en el lugar donde debería estar.*/
	
	//Complejidad: O(h) donde h es la altura del arbol (la cantidad de arcos)
	
	private boolean hasElem(TreeNode actual, int valorBuscado) {
		
		if(valorBuscado == actual.getValor())
			return true;
		else{
			if (valorBuscado < actual.getValor()) {
				
				if (actual.getIzq() == null){ //Le correspondería estar a la izq y no lo encontró.
					return false;
				}
				else 	//Sigue buscando por izquierda
					return hasElem(actual.getIzq(), valorBuscado);	//O(h)
				
			} else {	
				
				if (actual.getDer() == null){ //Debería estar en ese lugar y no hay nodo.
					return false;
				}
				else 	//Sigue buscando por derecha
					return hasElem(actual.getDer(),valorBuscado);	//O(h)
				
			}
		}
	}
	
	
	//---------------------------------boolean isEmpty()---------------------------------
	
	
	/*Retorna si el arbol posee al menos un nodo (la raiz)*/
	
	//Complejidad: O(1)
	
	public boolean isEmpty(){
		return this.raiz == null;
	}
	
	
	//---------------------------------boolean delete(Object)---------------------------------
	
	
	/*Retorna si pudo eliminar un elemento o no del árbol, sin revelar su estructura interna.*/
	
	//Complejidad: O(h) donde h es la altura del árbol (la rama más larga)
	//En el peor caso tiene que ir al último nodo de la rama más larga.
	
	public boolean delete(int valorBuscado){
		if (this.isEmpty())	//O(1)
			return false;
		else
			return this.delete(this.raiz, null, valorBuscado);	//O(h)
	}
	
	
	/* Retorna si pudo eliminar un elemento del árbol o no: recorre los nodos del árbol,
	 * por izquierda o derecha dependiendo si el valor buscado es menor o mayor al del nodo
	 * actual. Si coinciden los valores, se procede a eliminar según cuál sea el caso:
	 * si el nodo es hoja, si tiene un hijo, o si el nodo tiene 2 hijos, y a su vez considera
	 * el caso de ser la raíz*/
	
	//Complejidad: O(4h) => O(h) donde h es la altura del árbol (la rama más larga)
	//En el peor caso tiene que ir al último nodo de la rama más larga.
	
	private boolean delete(TreeNode actual, TreeNode padre, int valorBuscado){
		
		
		if(valorBuscado == actual.getValor()){	//Si encontró el valor
			
			if(padre == null)	//es la raíz
				borrarRaiz();	//O(h)
			else{
				
				if(actual.getIzq() != null && actual.getDer() != null){
					borrarPadreConHijos(padre, actual);	//O(h)
				}
				else{
					if(actual.getIzq() == null && actual.getDer() == null){
						borrarHoja(padre, actual);	//O(1)
					}
					else{
						borrarPadreConUnHijo(padre, actual);	//O(1)
					}
				}
				
			}
			
			return true;
			
		} else {	//Si todavía no encontró el valor
			
				if(valorBuscado < actual.getValor()){	//quiero buscar por izquierda si es menor
					
					if (actual.getIzq() == null)	//no hay más por buscar en esa rama
						return false;
					else //busco por la izquierda del nodo
						return delete(actual.getIzq(), actual, valorBuscado);	//O(h)
					
				}
				else{	//quiero buscar por derecha si es mayor
					
					if (actual.getDer() == null)	//no hay más por buscar en esa rama
						return false;
					else //busco por la derecha del nodo
						return delete(actual.getDer(), actual, valorBuscado);	//O(h)
					
				}
			}
	}

	
	/*En este método se analizan los 3 casos posibles de la situación de la raíz
	 *para eliminarla: en el caso que tenga dos hijos, busca el elemento más
	 *a la derecha de su subárbol izquierdo (el mayor de sus menores) e intercambia
	 *el valor de la raíz con este último, haciendo los cambios necesarios para
	 *mantener la integridad del árbol. En el caso que no tenga hijos, setea al nodo
	 *raíz a null, y sino la raíz pasa apuntar al único hijo que tenga.*/
	
	//Complejidad: O(h) => O(h) donde h es la altura del árbol (la rama más larga)
	//En el peor caso la rama más larga del árbol es la que se encuentra más
	//más a la derecha del subárbol izquierdo y debe llegar hasta su último árco.
	
	private void borrarRaiz() {
		
		if(this.raiz.getIzq() != null && this.raiz.getDer() != null){	//tiene dos hijos
			//busco el valor mayor del nodo del subarbol izquierdo de la raíz
			int valorAIntercambiar = getValorMayorDeMenores(this.raiz.getIzq(), this.raiz); //O(h)
			this.raiz.setValor(valorAIntercambiar);
		}
		else{	//es hoja
			if(this.raiz.getIzq() == null && this.raiz.getDer() == null){
				this.raiz = null;
			}
			else{	//tiene algun hijo
				if(this.raiz.getIzq() != null){	//tiene subarbol izquierdo
					this.raiz = this.raiz.getIzq();	//ahora la raiz apunta al subarbol izquierdo
				}
				else{	//tiene subarbol derecho
					this.raiz = this.raiz.getDer();	//ahora la raiz apunta al subarbol derecho
				}
			}
		}
		
	}


	/*Busca el valor mayor de la rama menor (el más a la derecha de la rama izquierda)
	 * del nodo a eliminar, para luego pisar el valor de ese nodo.*/
	
	//Complejidad: O(h), donde h es la altura del árbol.
	//En el peor caso la rama más larga del árbol es la que se encuentra más
	//más a la derecha del subárbol izquierdo y debe llegar hasta su último árco.
	
	private void borrarPadreConHijos(TreeNode padre, TreeNode nodoAEliminar) {
		
		//busco el valor mayor del nodo del subarbol izquierdo al nodo
		int valorAIntercambiar = getValorMayorDeMenores(nodoAEliminar.getIzq(), nodoAEliminar); //O(h)

			if(padre.getIzq() == nodoAEliminar){	//si el nodo a eliminar es el izquierdo
				TreeNode izq = padre.getIzq();
				izq.setValor(valorAIntercambiar);	
			}
			if(padre.getDer() == nodoAEliminar){	//si el nodo a eliminar es el derecho
				TreeNode der = padre.getDer();
				der.setValor(valorAIntercambiar);
				
			}
		
	}
	
	
	/*Devuelve el valor mayor de la rama menor del nodo y elimina del árbol
	 * al nodo que contiene el valor que va a retornar. Busca el mayor elemento
	 * del subarbol derecho. Si este nodo tiene algun hijo a su izquierda,
	 * intercambia los valores consigo mismo para eliminar el nodo del árbol.**/
	
	//Complejidad: O(h), donde h es la altura del árbol.
	//En el peor caso la rama más larga del árbol es la que se encuentra más
	//más a la derecha del subárbol izquierdo y debe llegar hasta su último árco.
	
	private int getValorMayorDeMenores(TreeNode actual, TreeNode padre){
		
		if(actual.getDer() == null){	//no hay nodo a mi derecha, soy el nodo más a la derecha del árbol
			
			int valor = actual.getValor();
			
			if(actual.getIzq() != null){	//tiene un hijo a la izquierda, intercambio valores y elimino al hijo izq
				borrarPadreConUnHijo(padre, actual);	//O(1)
			}else
				borrarHoja(padre, actual);	//lo borro porque su valor va a reemplazar al nodo que se busca eliminar
			return valor;
			
		}
		else	//busco por derecha
			return getValorMayorDeMenores(actual.getDer(), actual);	//O(h)
		
	}

	
	/* Borra a un hijo de un nodo, sea derecho o izquierdo.*/
	
	//Complejidad: O(1)
	
	private void borrarHoja(TreeNode padre, TreeNode nodoAEliminar) {
		
		if(padre.getIzq() == nodoAEliminar)
			padre.setIzq(null);
		else
			padre.setRight(null);
		
	}

	
	/* Desvincula al nodo a eliminar del árbol haciendo que el padre
	 * del mismo pase a apuntar a su hijo (izq o der según corresponda)
	 * del nodo a borrar, manteniendo la vinculación del lado izq o derecho,
	 * según donde este posicionado el nodo a eliminar con respecto al padre.*/
	
	//Complejidad: O(1)
	
	private void borrarPadreConUnHijo(TreeNode padre, TreeNode nodoAEliminar) {
		
		if(padre.getIzq() == nodoAEliminar){	//si el nodo a borrar es el de la izq
			
			if(nodoAEliminar.getDer() != null){	// tiene hijo a la izquierda
				padre.setIzq(nodoAEliminar.getDer());	//el padre pasa a apuntar al único hijo derecho del nodo a borrar
			}
			else{	//tiene hijo a la derecha
				padre.setIzq(nodoAEliminar.getIzq());	//el padre pasa a apuntar al único hijo izquierdo del nodo a eliminar
			}
		}
		else{	//el nodo a borrar es el derecho
			if(nodoAEliminar.getIzq() != null){	// tiene hijo a la derecha
				padre.setRight(nodoAEliminar.getIzq());	//el padre pasa a apuntar al único hijo izquierdo del nodo a borrar
			}
			else{	//tiene hijo a la derecha
				padre.setRight(nodoAEliminar.getDer());	//desvincula al nodo del arbol
			}
		}
		
	}
	//---------------------------------void printPosOrder()---------------------------------
	
	
	/*Imprime los elementos en orden: primero hijos y luego el padre. Finaliza el llamado al
	 * método en caso de ser un arbol sin nodos. Permite que no se rompa encapsulamiento.*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para proyectarlos.
	
	public void printPosOrder(){
		
		if (this.isEmpty())	//O(1)
			return;	//Termina el llamado del método en la pila de ejecución.
		else
			printPosOrder(this.raiz);	//O(n)

		System.out.println(" ");
		
	}
	
	
	/*Imprime los valores en orden: hijo izquierdo - padre - hijo derecho. En caso de ser
	 * null termina el llamado de la pila de ejecución.*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para proyectarlos.
	
	private void printPosOrder(TreeNode actual){
		
		if (actual == null)
			return;
		
		printPosOrder(actual.getIzq());	//O(n)
		
		printPosOrder(actual.getDer());	//O(n)
		
		System.out.print(actual.getValor() + " ");
		
		
	}
	
	//---------------------------------void printPreOrder()---------------------------------

	
	/*Imprime los elementos en orden: primero padre y luego hijos de mayor a menor (de izq
	 * a derecha). Finaliza el llamado al método en caso de ser un arbol sin nodos.
	 * Permite que no se rompa encapsulamiento.*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para proyectarlos.
	
	public void printPreOrder(){
		
		if (this.isEmpty())	//O(1)
			return;	//Termina el llamado del método en la pila de ejecución.
		else
			printPreOrder(this.raiz);	//O(n)
		
		System.out.println(" ");
		
	}
	
	
	/*Imprime los valores en orden: padre- hijo izquierdo- hijo derecho. En caso de ser
	 * null imprime un " - " indicando que el padre no posee hijo(izquierdo o derecho
	 * según corresponda)*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para proyectarlos.
	
	private void printPreOrder(TreeNode actual){
		
		if (actual == null){
			System.out.print(" - ");
		}
		else {
			
			System.out.print(actual.getValor() + " ");
			
			printPreOrder(actual.getIzq());	//O(n)
			
			printPreOrder(actual.getDer());	//O(n)
			
		}
	}
	
	//---------------------------------void printInOrder()---------------------------------

	
	/*Imprime los elementos en orden: primero hijo menor (izquierdo), el padre, y
	 *  luego el hijo mayor(de la derecha). Finaliza el llamado al método en caso de
	 *  ser un arbol sin nodos. Permite que no se rompa encapsulamiento.*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para proyectarlos.
	
	public void printInOrder(){
		
		if (this.isEmpty())	//O(1)
			return;	//Termina el llamado del método en la pila de ejecución.
		else
			printInOrder(this.raiz);	//O(n)
		
		System.out.println(" ");
		
	}
	
	
	/*Imprime los valores en orden: hijo izquierdo - padre - hijo derecho. En caso de ser
	 * null termina el llamado de la pila de ejecución.*/
	
	// Complejidad: O(n) donde n es la cantidad de nodos del arbol
	//En el peor y mejor caso tiene que recorrer todos los nodos para proyectarlos.
	
	private void printInOrder(TreeNode actual){
		
		if (actual == null)
			return;
		
		printInOrder(actual.getIzq());	//O(n)
		
		System.out.print(actual.getValor() + " ");
		
		printInOrder(actual.getDer());	//O(n)
		
	}
	
	//---------------------------------List getLongestBranch()--------------------------------- 
	
	
	/*Retorna la lista con los elementos de la rama más larga sin romper encapsulamiento.*/
	
	//Complejidad: O(n) donde n es la cantidad de nodos del árbol
	//Debe recorrer todo el árbol para saber cuál es la rama más larga.
	
	public ArrayList<Integer> getLongestBranch(){
		if (this.isEmpty())	//O(1)
			return new ArrayList<Integer>();	//Si no hay elementos retorna lista vacía
		else
			return this.getLongestBranch((this.raiz));	//O(n)
	}
	
	
	/*Devuelve la lista con los elementos de la rama mas larga.
	 * Recorre todo el árbol por izquierda y derecha mientras agrega los nodos actuales.
	 * Cuando llega a una hoja, solo se agrega y retorna un arraylist con este único
	 * elemento, que se anexa al arraylist correspondiente del nodo padre del anterior
	 * llamado al método. Este compara sus arraylists de izquierda y derecha, y retorna el
	 * de mayor tamaño. Al volver al primer llamado de la pila de ejecución, siempre termina
	 * retornando el arraylist que contiene la rama más larga de la der o izq de la raíz.**/
	
	//Complejidad: O(n) donde n es la cantidad de nodos del árbol
	//Debe recorrer todo el árbol para saber cuál es la rama más larga.
	
	private ArrayList<Integer> getLongestBranch(TreeNode actual){
		
		//inicializo las listas
		ArrayList<Integer> listaDerecha = new ArrayList<Integer>();
		ArrayList<Integer> listaIzquierda = new ArrayList<Integer>();
		
		if(actual.getIzq() != null){	//me agrego + agrego todos los elementos de mi izquierda
			
			listaIzquierda.add(actual.getValor());
			listaIzquierda.addAll(getLongestBranch(actual.getIzq()));	//O(n)
			
		}else
			listaIzquierda.add(actual.getValor());	//soy hoja, solo me agrego
		
		if(actual.getDer() != null){	//me agrego + todos los elementos de mi derecha
			
			listaDerecha.add(actual.getValor());
			listaDerecha.addAll(getLongestBranch(actual.getDer()));	//O(n)
			
		}else
			listaDerecha.add(actual.getValor());	//soy hoja, solo me agrego
		
		if(listaIzquierda.size() >= listaDerecha.size())
			return listaIzquierda;
		else 
			return listaDerecha;

	}
	
	
	//---------------------------------List getFrontera()---------------------------------
	
	
	/*Retorna la lista de elementos que son hoja sin romper encapsulamiento.*/
	
	//Complejidad: O(n) donde n es la cantidad de nodos del árbol
	//En el peor de los casos y mejor de los casos, debe retornar todas las hojas;
	//debe recorrer todo el árbol.
	
	public ArrayList<Integer> getFrontera(){
		if (this.isEmpty())	//O(1)
			return new ArrayList<Integer>();	//Si no hay elementos retorna lista vacía
		else
			return this.getFrontera((this.raiz));	//O(n)
	}
	
	
	/*Recorre los nodos que tengan algún hijo, por izquierda o por derecha según
	 * corresponda, y solo agrega aquellos nodos que son hojas.*/
	
	//Complejidad: O(n) donde n es la cantidad de nodos del árbol
	//En el peor de los casos y mejor de los casos, debe retornar todas las hojas;
	//debe recorrer todo el árbol.
	
	private ArrayList<Integer> getFrontera(TreeNode actual){
		
		//inicializo las listas
		ArrayList<Integer> listaHojas = new ArrayList<Integer>();
		
		if(actual.getDer() == null && actual.getIzq() == null)
			listaHojas.add(actual.getValor());
		else{
			
			if(actual.getIzq() != null)
				listaHojas.addAll(getFrontera(actual.getIzq()));	//O(n)
			
			if(actual.getDer() != null)
				listaHojas.addAll(getFrontera(actual.getDer()));	//O(n)
			
		}
		
		return listaHojas;
	}
	
	
	//---------------------------------getMaxElem()---------------------------------
	
	
	/*Devuelve el valor mayor del árbol sin romper encapsulamiento.*/
	
	//Complejidad: O(h) donde h es la altura del arbol (la longitud de la rama mas larga)
	//En el peor caso el árbol es sólo una rama y debe llegar al último nodo(hoja).
	
	public Integer getMaxElem(){
		if (this.isEmpty())	//O(1)
			return null;	//Si no tiene nodos retorna null
		else{
			return this.getMaxElem(this.raiz);	//O(h)
		}
	}
	
	
	/*Retorna el valor del elemento que se encuentra más a la derecha de la rama derecha
	 * en relación al nodo. Considero que si solo existe la raíz, este es el elemento
	 * de mayor valor en el árbol.*/
	
	//Complejidad: O(h) donde h es la altura del arbol (la longitud de la rama mas larga)
	//En el peor caso el árbol es sólo una rama y debe llegar al último nodo(hoja).
	
	private Integer getMaxElem(TreeNode actual) {

		if(actual.getDer() == null)	//no hay nodo a mi derecha, soy el nodo más a la derecha del árbol
			return actual.getValor();
		else	//busco por derecha
			return getMaxElem(actual.getDer());	//O(h)
	}
		
		
	//---------------------------------List getElemAtLevel(int)---------------------------------
	
	
	/*Retorna los elementos en determindo nivel sin la revelar estructura del árbol.*/
	
	//Complejidad: O(n) donde n es la cantidad de nodos
	//En el peor caso debe ir al último nivel del árbol, si el mismo está balanceado
	//deberá imprimir todas las hojas, lo que implica recorrerlo completamente.
	
	public ArrayList<Integer> getElementAtLevel(int nivel){
		if (this.isEmpty())	//O(1)
			return null;	//Si no tiene nodos retorna null
		else
			return this.getElemAtLevel(this.raiz, nivel, 0);	//O(h)
	}

	
	/*Busca tanto por izquierda y derecha. Si los elementos se encuentran en el nivel,
	 * los agrega a la lista. Si superó el nivel retorna un arraylist vacío y no
	 * continúa con la búsqueda.*/
	
	//Complejidad: O(n) donde n es la cantidad de nodos
	//En el peor caso debe ir al último nivel del árbol, si el mismo está balanceado
	//deberá imprimir todas las hojas, lo que implica recorrerlo completamente.
	
	private ArrayList<Integer> getElemAtLevel(TreeNode actual, int nivel, int nivelActual) {
		
		ArrayList<Integer> listaNivel = new ArrayList<Integer>();
		
		if (nivelActual == nivel)
			listaNivel.add(actual.getValor());
		else{
			
			if(nivelActual < nivel){

				if(actual.getIzq() != null)	//busco si puedo agregar por izquierda
					listaNivel.addAll(getElemAtLevel(actual.getIzq(), nivel, nivelActual + 1));
				
				if(actual.getDer() != null)	//busco si puedo agregar por derecha
					listaNivel.addAll(getElemAtLevel(actual.getDer(), nivel, nivelActual + 1));
				
			}
			else	//si me pasé de nivel no sigo recorriendo el árbol
				return listaNivel;
		
		}
		
		return listaNivel;
	}
}