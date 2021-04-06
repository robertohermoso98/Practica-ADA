package es.unex.cum.ada.pract2020_2021;

import es.unex.cum.ada.DataStructures.List;
import es.unex.cum.ada.DataStructures.ListIF;

public class WordList {
	private ListIF<WordListN> wordList;
	
	public WordList() {
		this.wordList = new List<WordListN>();
	}
	
	public void add(String word) {
		int tam = word.length();
		int init=1;
		boolean esta=false;
		// si hay listas y la primera tiene una tamaño mayor que
		// la de la palabra quiere decir que es la mas pequeña
		// asiqeu la creo una nueva wordlist y la meto en el primer lugar con la palabra
		boolean aLaprimera=false;
		if( wordList.size()>0){
			if( wordList.get(1).getWordSize()<tam){
				wordList.insert(1,new WordListN(tam));
				wordList.get(1).add(word);
				aLaprimera=true;
			}else{
				if( wordList.get(1).getWordSize()==tam){
					wordList.get(1).add(word);
					aLaprimera=true;
				}
			}

		}else{ // si la lista esta vacia se añade sin mas
			wordList.insert(1,new WordListN(tam));
			wordList.get(1).add(word);

			aLaprimera=true;
		}
		// sino busco si hay alguna lista con su tamaño si la hay la añado a esa lista
		if(!aLaprimera) {
			while (init <= wordList.size() && !esta) {
				if (wordList.get(init).getWordSize() == tam) {
					wordList.get(init).add(word);
					esta = true;
				}
				init++;
			}
			// en caso de que no se ha añadido
			if (!esta) {
				int tamañoMaximo = 0;
				int i = 1;
				boolean fin = false;
				// busco si hay alguna lista con un tamaño mayor
				// si es asi añado una nueva lista con esa palaba en la pasocicion
				//de la primera que es mayor
				// asi la primera que es mayor se desplaza
				while (i < wordList.size() && !esta) {
					if (wordList.get(i).getWordSize() < tam) {
						wordList.insert(i, new WordListN(tam));
						wordList.get(i).add(word);
						esta = true;
					}
					i++;
				}
				// si no se ha añadido aun es que no hay ninguna mas pequeña
				// es decir es la menor de las listas
				if (!esta) {
					//if (tam > tamañoMaximo && wordList.size() > 1) {
					WordListN nuevo =new WordListN(tam);
					nuevo.add(word);
						wordList.insert(wordList.size(), nuevo);

				}
			}
		}
	}

	public void setWordList(ListIF<WordListN> wordList) {
		this.wordList = wordList;
	}

	public ListIF<WordListN> getWordList() {
		return wordList;
	}

	public String toString() {
		StringBuilder salida = new StringBuilder();
		for ( int pos = 1 ; pos <= this.wordList.size() ; pos++ ) {
			salida.append(this.wordList.get(pos).toString());
		}
		return salida.toString();
	}
}
