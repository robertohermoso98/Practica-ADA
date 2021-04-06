package es.unex.cum.ada.pract2020_2021;

import es.unex.cum.ada.DataStructures.GTree;
import es.unex.cum.ada.DataStructures.GTreeIF;
import es.unex.cum.ada.DataStructures.List;
import es.unex.cum.ada.DataStructures.ListIF;

public class Dictionary {

	private GTree<Node> dict; /* El diccionario es un árbol general de nodos */
	public WordList salida ;

	/* Constructor de la clase */
	public Dictionary() {
		dict = new GTree<Node>();
		dict.setRoot(new RootNode());
		salida= new WordList();
	}

	/* Método de inserción de una nueva palabra en el diccionario */
	public void insert(String word) {
		/* Insertamos la palabra a partir del nodo raíz del árbol */
		insertInTree(word,this.dict);
	}

	/* Método privado llamado por el anterior */
	private void insertInTree(String word, GTreeIF<Node> node) {
		int tam =  node.getChildren().size();
		int i=1;
		boolean esta=false;
		// si la lista esta vacioa pasamos y la insertamos directamente
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
			// busco en el arbol la primera letra de la palabra si no esta la encajo
			// si esta busco la segunda letra en el arbol de donde estaba la primera y asi
			// hasta llegar al final
			// para ello llamo al metodo de forma recursiva pasandole
			// la palabra sin la primera letra
			// y el arbol del hijo donde se encontraba
			while (i<=tam && !esta){
				if (node.getChild(i).getRoot()!=null) {
					if (node.getChild(i).getRoot().getClass().toString().equals("class es.unex.cum.ada.pract2020_2021.LetterNode")) {

						LetterNode hij = (LetterNode) node.getChild(i).getRoot();
						if (hij.getCaracter().equals(split[0])) {
							// como la letra se encuetra le paso
							// a la llamada recursiva la palabra sin la primera letra
							// y el arbol hijo de esta letra
							insertInTree(recortar(word), node.getChild(i));
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
				insertInTree(recortar(word), node.getChild(i));
			}
		}


	}



	public String recortar(String conPrimera){
		String [] split =conPrimera.split("");
		String sinPrimera="";
		for (int i=1; i < split.length; i++){
			sinPrimera=sinPrimera+split[i];
		}
		return sinPrimera;
	}

	/* Método público de búsqueda de todas las palabras a partir de una secuencia */
	public WordList search(String sequence) {
		WordList salida = new WordList();
		searchInTree(sequence,"",this.dict,salida); /* Construimos la salida recursivamente */
		return salida;
	}



	/* Método privado llamado por el anterior */
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
						if(esta(sinRepe,hijo.getCaracter())){
							// si esta añado el caracter a la palabra y
							String word2=word+hijo.getCaracter();
							// a la secuencia le quito esa letra
							// para no tener que volver a buscar esa letra
							// en los hijos de este nodo
							// ya que solo hay uno
							sinRepe=eliminar(sinRepe, hijo.getCaracter());
							// ahora se llama a la funcion de forma recursiva
							// sin el caracter en la secuencia pero solo
							// lo elimino una vez
							searchInTree(eliminar(sequence, hijo.getCaracter()),word2,node.getChild(iterator),salida);
						}
					}
				}

				iterator++;
			}

		}

	}

	private String eliminar(String palabra, String letra){

		String sin="";
		String [] split = palabra.split("");
		boolean anadida=false;
		boolean noAnadida=false;
		for(int i=0; i< split.length;i++){
			if(split[i].equals(letra) ){
				//  sin=sin+split[i];
				if(anadida){
					sin=sin+split[i];
				}
				if(!noAnadida) {
					anadida = true;
					noAnadida = true;
				}

			}
			else{
				if(!split[i].equals(letra) ){
					sin=sin+split[i];
				}
			}
		}
		return sin;
	}

	private boolean esta(String padre, String letra){
		boolean esta=false;
		String [] split = padre.split("");
		for (int i=0; i< split.length; i++){
			if(split[i].equals(letra)){
				esta=true;
			}
		}
		return esta;
	}


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

	/* Método público de búsqueda de todas las palabras de tamaño size a partir de una secuencia */
	public WordListN search(String sequence, int size) {
		WordListN salida = new WordListN(size);           /* Variable donde construiremos la salida */
		searchInTreeN(sequence,"",this.dict,salida,size); /* Construimos la salida recursivamente */
		return salida;
	}

	/* Método privado llamado por el anterior */
	private void searchInTreeN(String sequence, String word,
							   GTreeIF<Node> node, WordListN salida,
							   int size) {

		String [] split = word.split("");
		String sinRepe=sinRepetir(sequence);
		if(node.getChildren().size()>0){
			int tam = node.getChildren().size();
			int iterator=1;
			// busco en todos los hijos
			while (iterator<=tam){
				// si es un nodo raiz la añado
				if(node.getChild(iterator).getRoot() instanceof WordNode && split.length==size){
					salida.add(word);
				}
				// sino sigo buscando
				else{
					if(split.length<size) {
						// si el hijoes un lleternode
						if (node.getChild(iterator).getRoot() instanceof LetterNode) {
							// busco si esta el caracter del hijo en la secuencia sin repetidos
							LetterNode hijo = (LetterNode) node.getChild(iterator).getRoot();
							if (esta(sinRepe, hijo.getCaracter())) {
								// si esta añado el caracter a la palabra y
								String word2 = word + hijo.getCaracter();
								// a la secuencia le quito esa letra
								// para no tener que volver a buscar esa letra
								// en los hijos de este nodo
								// ya que solo hay uno
								sinRepe = eliminar(sinRepe, hijo.getCaracter());
								// ahora se llama a la funcion de forma recursiva
								// sin el caracter en la secuencia pero solo
								// lo elimino una vez
								searchInTreeN(eliminar(sequence, hijo.getCaracter()), word2, node.getChild(iterator), salida, size);
							}
						}
					}
				}

				iterator++;
			}

		}
	}

	public GTree<Node> getDict() {
		return dict;
	}
}

// lo primero que hay que hacer es buscar las palbras cada vez
// que se encuentre una hay que meterla en la wordlist
// para buscar todas las palabras hay que almacenar en una lista todas las
// combinaciones que se pueden hacer con las letras que nos den



