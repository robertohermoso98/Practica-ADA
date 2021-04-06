import java.io.*;
import java.util.*;


public class ejercicio7 {

  public static void main (String [] args){

     Integer [] array1={1,2,3,4,5};
    Integer [] array2={1,2,3,4,4};
  if(fuction(array1,array2)){
    System.out.println("son iguales");
  }else{
     System.out.println("no son iguales");
  }
  }
  public static boolean fuction(Integer [] a1, Integer [] a2){
    if (a1.length==a2.length){
      return sonIguales(a1,a2,0,a1.length-1);
    }else{
      return false;
    }
  }
  public static boolean sonIguales(Integer [] a1, Integer [] a2,int prin, int fin){

    if(prin==fin){
      if(a1[prin].equals(a2[prin])){
        return true;
      }else{
        return false;
      }
    }else{
      int medio =(prin+fin)/2;
      return sonIguales(a1,a2,prin,medio) && sonIguales(a1,a2,medio+1,fin);
    }
  }
}
