/**
 * Created by samuel on 24/11/16.
 */
    public class Main{

        public static void main(String[]rgs){
   ///fksjdçalfkjsadf

            double[][] a = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

            Matriz A = new Matriz(a);

            Matriz B = A.decomposicaoLU(3);

            A.show();
            System.out.println();
            B.show();

            //teste

        }
    }