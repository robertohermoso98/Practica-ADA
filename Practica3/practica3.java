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
        for(int e =1; e<linea1.length; e++){
           String [] sigLine=sc.nextLine().split(" ");
            for(int i =0; i<sigLine.length; i++){
               lin[e][i]=Integer.parseInt(sigLine[i]);
            }
        }
         String ss =conjuntoMinimo(lin);
    }
    public static String conjuntoMinimo(Integer[][] matrix){
        String solucion="";
        Boolean [][]matrixBoolean= new Boolean [matrix.length][matrix.length];
         for(int e =0; e<matrix.length; e++){
            for(int i =0; i<matrix.length; i++){
                matrixBoolean[e][i]=true;
            }
         }


               


        return solucion;
    }
    public static String pointGenerator(Integer [] puntos){
        return String.valueOf(puntos[0])+","+String.valueOf(puntos[1])+" "+String.valueOf(puntos[2]);
    }
    public static Integer[] searchPoint(Integer [][] matrix,  Boolean [][]matrixBoolean){
     int valor = 0;
     Integer [] point = new Integer[2];
        for(int e =0; e<matrix.length; e++){
            for(int i =0; i<matrix.length; i++){
                if(matrix[e][i]>valor && matrixBoolean[e][i]=true){
                valor =matrix[e][i];
                point[0]=e;
                point[1]=i;
                
                }
            }
        
        }
        matrixBoolean[point[0]][point[1]]=false;

        return point;
    }


    public static void searchDelete(LinkedList<String> lista, String punto){
        int pos=lista.indexOf(punto);
       
        if(pos!=-1){
            lista.remove(pos);
        }
        
    }
    public static LinkedList<String> makeList(Integer [][] m){
        LinkedList<String> lista = new LinkedList<String> ();
        for(int e =0; e<m.length; e++){
            for(int i =0; i<m.length; i++){
                String punto= new String(String.valueOf(e)+","+String.valueOf(i)+" "+String.valueOf(m[e][i]));
                lista.add(punto);
            }
        }

        return lista;
    }
    public static String searchBiggest(Integer[][] matrix,  Boolean [][] matrixBoolean, int x, int y)
    {
        Integer [] puntoo = new Integer[3];
        int valor=0;
        int antivalor=0;
        antivalor=(matrix[x][y]);
        puntoo[0]=x;
        puntoo[0]=y;
        puntoo[0]=antivalor;
        if(x>0){
            valor = matrix[x-1][y];
            if(valor>=antivalor){
                puntoo[0]=x-1;
                puntoo[1]=y;
                puntoo[2]=valor;
            }
            antivalor=valor;
        }
        if(x<matrix.length-1){
            valor= matrix[x+1][y];
             if(valor>=antivalor){
                puntoo[0]=x+1;
                puntoo[1]=y;
                puntoo[2]=valor;

            }
             antivalor=valor;
        }
        if(y>0){
            valor= matrix[x][y-1];
             if(valor>=antivalor){
                puntoo[0]=x;
                puntoo[1]=y-1;
                puntoo[2]=valor;

            }
             antivalor=valor;
        }
        if(y<matrix.length-1){
            valor=matrix[x][y+1];
             if(valor>=antivalor){
                puntoo[0]=x;
                puntoo[1]=y+1;
                puntoo[2]=valor;

            }
        }
             matrixBoolean[puntoo[0]][puntoo[1]]=false;
        return pointGenerator(puntoo);
    }   
 
}
