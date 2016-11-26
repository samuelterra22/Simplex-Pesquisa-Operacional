/**
 * Created by samuel on 24/11/16.
 */
    public class Main{

        public static void main(String[]rgs){

            //double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            //double[][] a = {{5, 7}, {15, 13}};
            double[][] a = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

            Matriz A = new Matriz(a);

            Matriz B = A.decomposicaoLU(3);

            A.show();
            System.out.println();
            B.show();

            //teste

        }
    }