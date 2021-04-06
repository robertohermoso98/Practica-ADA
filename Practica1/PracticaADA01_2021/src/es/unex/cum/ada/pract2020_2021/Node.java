package es.unex.cum.ada.pract2020_2021;

public abstract class Node {

	public enum NodeType {
		ROOTNODE, LETTERNODE, WORDNODE
	}
	
	/* Prescribe un getter que devuelve el tipo de nodo */
	public abstract NodeType getNodeType();
}
