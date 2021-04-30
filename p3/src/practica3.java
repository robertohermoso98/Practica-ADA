import java.util.Scanner;

public class practica3 {
    public static void main(String[] args) {
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
        System.out.println(conjuntoMinimo(lin));
    }

    public static String conjuntoMinimo(Integer[][] matrix) {
        String solucion = "";
        Boolean[][] matrixBoolean = new Boolean[matrix.length][matrix.length];
        for (int e = 0; e < matrix.length; e++) {
            for (int i = 0; i < matrix.length; i++) {
                matrixBoolean[e][i] = true;
            }
        }
        Integer [] a = searchPoint(matrix, matrixBoolean);
        solucion = String.valueOf(a[0])+" "+String.valueOf(a[1]);
        while(!esSolucion(matrixBoolean)){

            Integer [] segundoPunto= searchBiggest(matrix, matrixBoolean, a[0],a[1]);
            while((segundoPunto[0]!=a[0] || segundoPunto[1]!= a[1]) && (!esSolucion(matrixBoolean))){
                a[0]=segundoPunto[0];
                a[1]=segundoPunto[1];
                segundoPunto= searchBiggest(matrix, matrixBoolean, a[0],a[1]);
            }
            if(!esSolucion(matrixBoolean)) {
                a = searchPoint(matrix, matrixBoolean);
                solucion =  solucion+ "\n" + String.valueOf(a[0]) + " " + String.valueOf(a[1]) ;
            }
        }
        return solucion;
    }


    public static boolean esSolucion(Boolean[][] matrixBoolean) {
        boolean solu=true;
        for (int e = 0; e < matrixBoolean.length; e++) {
            for (int i = 0; i < matrixBoolean.length; i++) {
                if (matrixBoolean[e][i] == true) {
                    solu=false;
                }
            }
        }
        return solu;
    }

    public static Integer[] searchPoint(Integer[][] matrix, Boolean[][] matrixBoolean) {
        int valor = 0;
        Integer[] point = new Integer[2];
        for (int e = 0; e < matrix.length; e++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[e][i] >= valor && matrixBoolean[e][i]==true) {
                    valor = matrix[e][i];
                    point[0] = e;
                    point[1] = i;

                }
            }

        }
        matrixBoolean[point[0]][point[1]] = false;

        return point;
    }


    public static Integer[] searchBiggest(Integer[][] matrix, Boolean[][] matrixBoolean, int x, int y) {
        Integer[] puntoo = new Integer[3];
        int valor = 0;
        int antivalor = 0;
        int axu  = (matrix[x][y]);
        puntoo[0] = x;
        puntoo[1] = y;
        puntoo[2] = antivalor;
        if (x > 0) {
            valor = matrix[x - 1][y];
            if (valor >= antivalor && matrixBoolean[x-1][y]==true && valor<=axu)  {
                puntoo[0] = x - 1;
                puntoo[1] = y;
                puntoo[2] = valor;
                antivalor = valor;
            }

        }
        if (x < matrix.length - 1) {
            valor = matrix[x + 1][y];
            if (valor >= antivalor && matrixBoolean[x+1][y]==true && valor<=axu ) {
                puntoo[0] = x + 1;
                puntoo[1] = y;
                puntoo[2] = valor;
                antivalor = valor;
            }

        }
        if (y > 0) {
            valor = matrix[x][y - 1];
            if (valor >= antivalor && matrixBoolean[x][y-1]==true && valor<=axu ) {
                puntoo[0] = x;
                puntoo[1] = y - 1;
                puntoo[2] = valor;
                antivalor = valor;
            }

        }
        if (y < matrix.length - 1) {
            valor = matrix[x][y + 1];
            if (valor >= antivalor && matrixBoolean[x][y+1]==true && valor<=axu ) {
                puntoo[0] = x;
                puntoo[1] = y + 1;
                puntoo[2] = valor;

            }
        }
        matrixBoolean[puntoo[0]][puntoo[1]] = false;
        return puntoo;
    }

}