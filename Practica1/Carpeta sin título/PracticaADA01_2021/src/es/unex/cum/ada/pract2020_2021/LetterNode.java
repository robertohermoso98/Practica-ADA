package es.unex.cum.ada.pract2020_2021;

public class LetterNode extends Node {

    private String caracter;

    public LetterNode(){
        caracter="";
    }

    public LetterNode(String carac){
        caracter=carac;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.LETTERNODE;
    }
}
