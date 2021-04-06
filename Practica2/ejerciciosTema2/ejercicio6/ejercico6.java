import java.io.*;
import java.util.*;

public class ejercico6 {

  public static void main(String [] args){
    System.out.println("Hello Word");
    Integer [] array ={1,2,3,4,5,6,7};
    System.out.println(fuction(array,0,6));
  }
  public static Float fuction(Integer [] array, int principio, int fina){
   if(fina-principio==0){
     return  ((float)array[fina]);
   }else{
     int medio=(fina+principio)/2;
     float x=fuction(array,principio,medio);
     float y=fuction(array,medio+1,fina);
     return ((x+y)/2);

  }
  }
}
