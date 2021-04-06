package es.unex.cum.ada.pract2020_2021;

import es.unex.cum.ada.DataStructures.BTree;
import es.unex.cum.ada.DataStructures.List;
import es.unex.cum.ada.DataStructures.Queue;

public class WordListN {
	/* Atributos de la clase con la estructura adecuada */
	public int tamanio; // tamaño de la palabras a encajar
	public List <String> palabras  ; // lsita de palabras a encajar
	/* Atributos de la clase con la estructura adecuada */

	public WordListN(int size) {
		tamanio=size; // tamanio de las palabras a introducir
		palabras= new List<String>();
	}
	
	public void add(String word) {
		// me aseguro de meter solo palabras del tamaño correcto
		if(word.length()==tamanio){
			// si no hay palabras la introduzcto al principio
			if(palabras.size()==0){
				palabras.insert(1,word);
			}else{
				// en caso de que haya palabras
				int i=1;
				boolean fin=false;
				// busco si hay alguna que sea "mayor" que la que hay que insertar
				// y al inserto en su lugar
				while (!fin && i<=palabras.size()){
					if(palabras.get(i).compareTo(word)>0){
						palabras.insert(i,word);
						fin=true;
					}
					i++;
				}
				// si no se ha añadido la añado
				if(!fin){
					palabras.insert(i,word);
				}
			}
		}
	}
    public int getWordSize (){
		return tamanio;
	}

	public void setPalabras(List<String> palabras) {
		this.palabras = palabras;
	}

	public List <String> getPalabras() {
		return palabras;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}


	public String toString() {
		StringBuilder salida = new StringBuilder();
		int numPalabras = palabras.size(); ;// Longitud de la secuencia de palabras */
		salida.append("-Palabras de ");
		salida.append(this.getWordSize());
		salida.append(" letra");
		if ( this.getWordSize() > 1 ) { salida.append('s'); }
		salida.append(": ");
		for (int pos = 1 ; pos <= numPalabras ; pos++) {
			// Estas líneas dependen de la estructura escogida */
			String word = palabras.get(pos);// Obtener la siguiente palabra */
			// Avanzar a la siguiente sin destruir la estructura */
			///
					///////7
					//////
					///////77
			// Estas líneas dependen de la estructura escogida */
			salida.append(word);
			if ( pos < numPalabras ) {
				salida.append(", ");
			}
		}
		salida.append('\n');
		return salida.toString();
	}
}
