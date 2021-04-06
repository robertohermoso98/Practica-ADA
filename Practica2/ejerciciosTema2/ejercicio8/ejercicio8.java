import java.io.*;
import java.util.*;


public class ejercicio8 {
  public static void main(String [] args ){
    Integer [] array = {2,3,4,8,0,11,2,9};
    System.out.println(Min(array,0,7));
  }
  public static int Min(Integer [] array, int prin , int fin){

    if(fin-prin==1){
      return Math.min(array[prin], array[fin]);
    }
    else{
      int medio=(prin + fin)/2;
      return Math.min(Min(array,prin,medio), Min(array,medio+1,fin));
    }
  }
}
