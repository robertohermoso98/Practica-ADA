import java.io.*;
import java.util.*;

public class practica3 {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String [] linea1=sc.nextLine().split(" ");
        Integer [][] lin=new Integer[linea1.length][linea1.length];
        for(int i = 0; i<linea1.length; i++){
            lin[0][i]=Integer.parseInt(linea1[i]);
        }
        for(int e =0; e<linea1.length-1; e++){
           String [] sigLine=sc.nextLine().split(" ");
            for(int i =0; i<sigLine.length; i++){
               lin[e][i]=Integer.parseInt(sigLine[i]);
            }
        }
         String ss =conjuntoMinimo(lin);
    }
    public static String conjuntoMinimo(Integer[][] matrix){
        String solucion="";



        


        return solucion;
    }

    
}
