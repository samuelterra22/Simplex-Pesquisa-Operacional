/**
 * Created by samuel on 24/11/16.
 */
    public class Main{

        public static void main(String[]rgs){

            double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            double[][] b = {{2}};

            Matriz ma = new Matriz(a);
            Matriz mb = new Matriz(b);

            ma.mult(mb);

            ma.show();

        }
    }