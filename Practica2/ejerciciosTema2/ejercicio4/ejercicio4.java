import java.io.*;
import java.util.*;

public class ejercicio4 {
  public static void main (String[] args){

    System.out.println("hola mundo");
    Integer [] array= {1,2,3,4,5,6,7};
   

    System.out.println( media(array,0,6));
  }
  public static  Float media(Integer [] ar, int a, int b){
      if(b-a==1){
        float dd= ((float) ( ar[a] + ar[b])/2);
        System.out.println(dd);
        return dd;

        
      }else{
          if(b-a==2){

        return ((float) ( ar[a] +ar[a+1]+ar[b])/3);


        
          }
             else{
              int medio=(a+b)/2;  
              return ((media(ar,a,medio) + media(ar,medio+1,b))/2);
             }
     }
      

  }

}
