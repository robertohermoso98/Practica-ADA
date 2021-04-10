import java.io.*;
import java.util.*;
public class Practica2
{
	public static void main(String[] args) {

    ArrayList <Integer []> lista = new ArrayList<Integer []>();

Scanner sc = new Scanner(System.in);
		String s=sc.nextLine();
	int n=Integer.parseInt(s);
  for(int i=0;i<n;i++) { 
		
			String [] split = sc.nextLine().split(" ");
            Integer a = Integer.parseInt(split[0]);
            Integer b = Integer.parseInt(split[1]);
            Integer [] ab= {a,b};

      lista.add(ab);
  }
        double result = (double) min(lista,0,lista.size()-1);
  double resultado = redondearDecimales(result,6);
    System.out.println(resultado);
	}
  public static Float min(ArrayList <Integer []> lista,int prin, int fin){
    // primero pruebo a calcular la distancia minima por el lado de las x
    // para ello primero lo ordeno por las x
    lista.sort(new Comparator <Integer []>(){
      @Override
      public int compare(Integer [] a1,Integer [] a2){
        return a1[0]-a2[0];
      }
    }); 
      Float disX = minX(lista, prin,fin);
      // 
      // luego pruebo por el lado de las y
      // y luego lo ordeno por el lado de las y
 lista.sort(new Comparator <Integer []>(){
      @Override
      public int compare(Integer [] a1,Integer [] a2){
        return a1[1]-a2[1];
      }
    }); 
 // devuelvo el lado que menos diantcia me de
      Float disY = minY(lista,prin,fin);
      return Math.min(disX,disY);
  }
  private  static float minX(ArrayList <Integer []> lista,int prin, int fin){
    // si solo hay dos puntos es el caso base
    // entonces retorno su distancia
    if(fin-prin==1){
      return distancia(lista.get(prin),lista.get(fin));
      // en caso contrario calculo la distancia de los puentos que estan en la
      // mitad del array y devulvo la distancia minima entre la que esta a la
      // derecha esta o la que esta a la izquierda
    } else{
          int medio =(fin+prin)/2;
        if(fin-prin==2){
          return Math.min(distancia(lista.get(prin),lista.get(medio)),distancia(lista.get(medio),lista.get(fin)));
        }else{
          float disMedio=distancia(lista.get(medio),lista.get(medio+1));
          float disDere=minX(lista,prin,medio);
          float disIzq=minX(lista, medio+1,fin);
       
          return Math.min(disMedio,Math.min(disIzq,disDere));
        }

    }
}
  
  private static float minY(ArrayList <Integer []> lista,int prin, int fin){
    // si solo hay dos puntos es el caso base
    // entonces retorno su distancia
    if(fin-prin==1){
      return distancia(lista.get(prin),lista.get(fin));
      // en caso contrario calculo la distancia de los puentos que estan en la
      // mitad del array y devulvo la distancia minima entre la que esta a la
      // derecha esta o la que esta a la izquierda
    } else{
          int medio =(fin+prin)/2;
        if(fin-prin==2){
          return Math.min(distancia(lista.get(prin),lista.get(medio)),distancia(lista.get(medio),lista.get(fin)));
        }else{
          float disMedio=distancia(lista.get(medio),lista.get(medio+1));
          float disDere=minY(lista,prin,medio);
          float disIzq=minY(lista, medio+1,fin);
          return Math.min(disMedio,Math.min(disIzq,disDere));
        }
    }
  }
  public static Float distancia(Integer [] a, Integer[] b){
    

    float xx=((a[1]-b[1])*(a[1]-b[1]));
    float yy=((a[0]-b[0])*(a[0]-b[0]));
    float bb= (float) Math.sqrt(xx+yy);
    return Math.abs(bb);
  }

 public static double redondearDecimales(double valorInicial, int numeroDecimales) {
     double parteEntera, resultado;
     resultado = valorInicial;
     parteEntera = Math.floor(resultado);
     resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
     resultado=Math.round(resultado);
     resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
     return resultado;
 }

}
