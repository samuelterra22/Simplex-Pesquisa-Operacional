/**
 * Created by samuel on 24/11/16.

 */
    public class Main{

        public static void main(String[]rgs){

            int linha;
            int coluna;
            int i;
            int somaprod;

            int[][] mat1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] mat2 = new int[][]{{1,0,0},{0,1,0},{0,0,1}};
            int mat3[][] = new int[3][3];

            int M1L=3, M1C=3, M2L=3, M2C=3;

            for(linha=0; linha<M1L; linha++)
                for(coluna=0; coluna<M2C; coluna++){
                    somaprod=0;
                    for(i=0; i<M1L; i++) somaprod+=mat1[linha][i]*mat2[i][coluna];
                    mat3[linha][coluna]=somaprod;
                }
            //
            //imprime mat3
            //
            for(linha=0; linha<M1L; linha++){
                for(coluna=0; coluna<M2C; coluna++)
                    System.out.print(mat3[linha][coluna]+" ");
                System.out.println();
            }
        }
    }