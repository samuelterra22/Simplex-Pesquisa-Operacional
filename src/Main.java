/**
 * Created by samuel on 24/11/16.
 */
    public class Main{

        public static void main(String[]rgs){

            double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

            Matriz A = new Matriz(a);

            Matriz B = A.decomposicaoLU(3);

            Matriz C = A.mult(B);

            A.show();
            System.out.println();
            B.show();
            System.out.println();
            C.show();

        }
    }