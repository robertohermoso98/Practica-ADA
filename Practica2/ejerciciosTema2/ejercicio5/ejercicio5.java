import java.io.*;
import java.util.*;

public class ejercicio5 {
  public static void main (String[] args){

    System.out.println("hola mundo");
    Integer [] array= {1,2,3,4,5,6,7,8};
   

    System.out.println( fuction(array,0,7));
  }
  public static  Float fuction(Integer [] ar, int a, int b){
        if(b-a==1){
          return ((float) ar[a]+ar[b]);
        }else{
          System.out.println(String.valueOf(a)+" " +String.valueOf(b));
        int media=(b+a)/2;
        return fuction(ar,a,media)+fuction(ar,media+1,b);
      }
      

  }

}
