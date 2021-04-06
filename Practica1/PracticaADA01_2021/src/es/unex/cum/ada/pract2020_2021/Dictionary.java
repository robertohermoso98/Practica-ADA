package es.unex.cum.ada.pract2020_2021;

import es.unex.cum.ada.DataStructures.GTree;
import es.unex.cum.ada.DataStructures.GTreeIF;

import java.util.Objects;

public class Dictionary {

	private GTree<Node> dict; /* El diccionario es un árbol general de nodos */

	/* Constructor de la clase */
	public Dictionary() {
		dict = new GTree<Node>();
		dict.setRoot(new RootNode());
	}

	/**
	 * Método publico para obterner el Dictionary
	 * @return Devuelve el Dictionary
	 */
	public GTree<Node> getDict() {
		return dict;
	}

	/**
	 * Método público para selecionar el Dictionary del objeto instanciado
	 * @param dict
	 */
	public void setDict(GTree<Node> dict) {
		this.dict = dict;
	}

	/**
	 * Método de inserción de una nueva palabra en el diccionario
	 * @param word Palabra a insertar en el Dictionary
	 */
	public void insert(String word) {
		/* Insertamos la palabra a partir del nodo raíz del árbol */
		insertInTree(word,this.dict);
	}

	/**
	 * Método privado llamado por el método insert
	 * @param word Palabra a insertar en el Dictionary
	 * @param node Nodo raiz sobre el que se inicia el método recursivo
	 */
	private void insertInTree(String word, GTreeIF<Node> node) {
		// cantidad de hijos del nodo node
		int tam =  node.getChildren().size();
		// Iterador para recorrer la lista de hijos del nodo node
		int i=1;
		// Variable para saber si la letra de la palabra word esta en el dicionario
		boolean esta=false;
		// si la palabra que le pasamos esta vacia se le añade un nodo hoja
		// en la posicion 1 ( la cero )
		if(word.equals("")){
			GTree dict = new GTree<WordNode>();
			dict.setRoot(new WordNode());
			node.addChild(1,dict);
		}
		// sino se utiliza el metodo recursivo
		else {
			String [] split = word.split("");
			/*
			recorro la lista de hijos y busco si alguno de ellos su letra es igual a la
			primera letra de la palabra que se le pasa( casda vez que se llama al metodo
			se le recorta la primera letra) si esta busco en este nodo de forma recursiva
			 */
			while (i<=tam && !esta){
				if (node.getChild(i).getRoot()!=null) {

					// usar instanceof
					// compruebo que sea un node LetterNode
					if (node.getChild(i).getRoot().getClass().toString().equals("class es.unex.cum.ada.pract2020_2021.LetterNode")) {
						// si lo es saco el hijo para buscar su caracter
						LetterNode hij = (LetterNode) node.getChild(i).getRoot();
						// compruebo si coincide con la primera letra de la palabra a buscar
						if (hij.getCaracter().equals(split[0])) {
							// como la letra se encuetra le paso
							// a la llamada recursiva la palabra sin la primera letra
							// y el arbol hijo de esta letra
							insertInTree(recortarPrimerCaracter(word), node.getChild(i));
							esta = true;
						}
					}
				}
				i++;
			}
			// si la primera letra de la palabra no esta la
			// introudco en el diccionario
			if(!esta){
				GTree dict = new GTree<LetterNode>();
				dict.setRoot(new LetterNode(split[0]));
				node.addChild(i,dict);
				insertInTree(recortarPrimerCaracter(word), node.getChild(i));
			}
		}


	}

	/**
	 * Método público de búsqueda de todas las palabras a partir de una secuencia
	 * @param sequence secuencia sobre la que se realizara la busqueda
	 * @return
	 */
	public WordList search(String sequence) {
		WordList salida = new WordList();
		searchInTree(sequence,"",this.dict,salida); /* Construimos la salida recursivamente */
		return salida;
	}


	/**
	 * Método privado llamado por search(sequence)
	 * @param sequence secuencia sobre la que se realiza la búsquedaSequencia a bu
	 * @param word Palabra que se añadira al la WordListN
	 * @param node Nodo sobre el que se itera
	 * @param salida WordListN en la que se guardara la palabra
	 */
	private void searchInTree(String sequence, String word,
							  GTreeIF<Node> node, WordList salida) {

		String sinRepe=sinRepetir(sequence);
		if(node.getChildren().size()>0){
			int tam = node.getChildren().size();
			int iterator=1;
			// busco en todos los hijos
			while (iterator<=tam){
				// si es un nodo raiz la añado
				if(node.getChild(iterator).getRoot() instanceof WordNode ){
					salida.add(word);
				}
				// sino sigo buscando
				else{
					// si el hijoes un lleternode
					if(node.getChild(iterator).getRoot() instanceof LetterNode ){
						// busco si esta el caracter del hijo en la secuencia sin repetidos
						LetterNode hijo = (LetterNode) node.getChild(iterator).getRoot();
						if(estaCaracterEnSecuencia(sinRepe,hijo.getCaracter())){
							// si esta añado el caracter a la palabra y
							String word2=word+hijo.getCaracter();
							// a la secuencia le quito esa letra
							// para no tener que volver a buscar esa letra
							// en los hijos de este nodo
							// ya que solo hay uno
							sinRepe=eliminarCaracterUnaVez(sinRepe, hijo.getCaracter());
							// ahora se llama a la funcion de forma recursiva
							// sin el caracter en la secuencia pero solo
							// lo elimino una vez
							searchInTree(eliminarCaracterUnaVez(sequence, hijo.getCaracter()),word2,node.getChild(iterator),salida);
						}
					}
				}

				iterator++;
			}

		}

	}



	/**
	 * Método público de búsqueda de todas las palabras de tamaño size a partir de una secuencia
	 * @param sequence secuencia sobre la que se realiza la búsqueda
	 * @param size Tamaño de las palabras a buscar
	 * @return
	 */
	public WordListN search(String sequence, int size) {
		WordListN salida = new WordListN(size);           /* Variable donde construiremos la salida */
		searchInTreeN(sequence,"",this.dict,salida,size); /* Construimos la salida recursivamente */
		return salida;
	}

	/**
	 * Método privado llamado por search(sequence, size)
	 * @param sequence secuencia sobre la que se realiza la búsquedaSequencia a bu
	 * @param word Palabra que se añadira al la WordListN
	 * @param node Nodo sobre el que se itera
	 * @param salida WordListN en la que se guardara la palabra
	 * @param size Tamaño de las palabras a buscar
	 */
	private void searchInTreeN(String sequence, String word,
							   GTreeIF<Node> node, WordListN salida,
							   int size) {

		String [] split = word.split("");
		String sinRepe=sinRepetir(sequence);
		if(node.getChildren().size()>0){
			int tam = node.getChildren().size();
			int iterator=1;
			// busco en todos los hijos solo si la palabra es mas pequeña oquial que el tamaño deseado
			if(split.length<=size) {
				while (iterator <= tam) {
					// si es un nodo raiz la añado
					if (node.getChild(iterator).getRoot() instanceof WordNode && split.length==size) {
						salida.add(word);
					}
					// sino sigo buscando
					else {
						if (split.length < size) {
							// si el hijoes un lleternode
							if (node.getChild(iterator).getRoot() instanceof LetterNode) {
								// busco si esta el caracter del hijo en la secuencia sin repetidos
								LetterNode hijo = (LetterNode) node.getChild(iterator).getRoot();
								if (estaCaracterEnSecuencia(sinRepe, hijo.getCaracter())) {
									// si esta añado el caracter a la palabra y
									String word2 = word + hijo.getCaracter();
									// a la secuencia le quito esa letra
									// para no tener que volver a buscar esa letra
									// en los hijos de este nodo
									// ya que solo hay uno
									sinRepe = eliminarCaracterUnaVez(sinRepe, hijo.getCaracter());
									// ahora se llama a la funcion de forma recursiva
									// sin el caracter en la secuencia pero solo
									// lo elimino una vez
									searchInTreeN(eliminarCaracterUnaVez(sequence, hijo.getCaracter()), word2, node.getChild(iterator), salida, size);
								}
							}
						}
					}

					iterator++;
				}
			}

		}
	}

	/**
	 * Método que devuelve sin el primer caracter la palabra pasada por parametro
	 * @param conPrimera Es la palabra a la cual se le quiere quitar el primer caracter
	 * @return
	 */
	public String recortarPrimerCaracter(String conPrimera){
		// Hacemos un array de String con todos los caracteres de la palabra
		String [] split =conPrimera.split("");
		// Inicializamos la palbra a devolver sin nada
		String sinPrimera="";
		// Recorremos el array pero desde la segunda posición y la concademos
		// con la palabra a devolver
		for (int i=1; i < split.length; i++){
			sinPrimera=sinPrimera+split[i];
		}
		return sinPrimera;
	}

	/**
	 * Método para eliminar una sola vez un caracter de una palabra
	 * @param palabra Palabra de la que se elimara el caracter
	 * @param letra Cacaracter a eliminar
	 * @return
	 */
	private String eliminarCaracterUnaVez(String palabra, String letra){
		// Inicializamos la palabra a devilver vacia
		String sin="";
		// Hacemos un array de String con todos los caracteres de la palabra
		String [] split = palabra.split("");
		// Recorremos el array y añadimos solo una vez el caracter
		boolean anadida=false;
		boolean noAnadida=false;
		for(int i=0; i< split.length;i++){
			// comprobamos sin el caracter de la palbra es igual a que deseamos sacar
			// si lo es:
			if(split[i].equals(letra) ){
				//  si es la primera vez no se añade
				// pero las demas si
				if(anadida){
					sin=sin+split[i];
				}
				// si es la primera vez no se añade pero
				// se inicializa la variable para añadirla la proximas veces
				if(!noAnadida) {
					anadida = true;
					noAnadida = true;
				}
			}
			// si no el es se añade sin más
			else{
				if(!split[i].equals(letra) ){
					sin=sin+split[i];
				}
			}
		}
		return sin;
	}

	/**
	 * Método para saber si un caracter si encuentra en una palabra
	 * @param padre Palabra sobre la que se desea saber si se encuentra el caracter
	 * @param letra Caracter a buscar en la palabra
	 * @return
	 */
	private boolean estaCaracterEnSecuencia(String padre, String letra){
		boolean esta=false;
		String [] split = padre.split("");
		for (int i=0; i< split.length; i++){
			if(split[i].equals(letra)){
				esta=true;
			}
		}
		return esta;
	}

	/**
	 * Este metodo dado un String lo devuelve pero sin que ninguna letra se repita
	 * @param secuencia secuencia sobre la que se va realizar el método
	 * @return String
	 */
	private String sinRepetir(String secuencia){
		String [] split = secuencia.split("");
		String sinRepe=split[0];
		boolean esta=false;
		for (int i = 1; i< split.length; i++){
			String [] split2 =sinRepe.split("");
			esta=false;
			for (int e=0; e< split2.length; e++){
				if(split[i].equals(split2[e])){
					esta=true;
				}
			}
			if(!esta){
				sinRepe=sinRepe+split[i];
			}
		}
		return sinRepe;
	}


	@Override
	public String toString() {
		return "Dictionary{" +
				"dict=" + dict +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dictionary that = (Dictionary) o;
		return Objects.equals(dict, that.dict);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dict);
	}
}

// lo primero que hay que hacer es buscar las palbras cada vez
// que se encuentre una hay que meterla en la wordlist
// para buscar todas las palabras hay que almacenar en una lista todas las
// combinaciones que se pueden hacer con las letras que nos den




	/*int tam= dict.getChildren().size();
	int i =1;
	boolean esta=false;
	String [] split = sinRepetir(sequence).split("");
	int tamSecuancie=split.length;
// aplico el agoritmo a todas las letras dela se cuencia
		for(int e =0; e<tamSecuancie;e++){

		}
		while (!esta && i <= tam){
		LetterNode hij = (LetterNode) node.getChild(i).getRoot();
		if(hij.getCaracter().equals(split[0])){
		// como la letra se encuetra le paso
		// a la llamada recursiva la palabra sin la primera letra
		// y el arbol hijo de esta letra

		word=word+hij.getCaracter();
		searchInTree(sequence,word,node.getChild(i),salida);
		insertInTree(recortar(word), node.getChild(i));
		esta=true;
		}
		i++;
		}

	 */

