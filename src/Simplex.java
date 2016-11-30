/**
 * Created by diego on 28/11/16.
 * Atualizado......
 */


public class Simplex {

    private static double[][] BMenosUm;
    private static double[] indicesBase;
    private static double[] indicesNaoBase;
    private static double[] u;
    private static int jotaEscolhido;  //joTa
    private int L;                // numero de linhas (M)
    private int C;                // numero de colunas (N)

    public Simplex() {

        // inicializar todos os valores no contrutor, para usar no Main deve-se instanaciar um objeto Simplex

        L = 9;      // numero de linhas (M)
        C = 13;     // numero de colunas (N)

        BMenosUm = new double[L][L];
        indicesBase = new double[L];
        indicesNaoBase = new double[C - L];

        u = new double[L];
        jotaEscolhido = 0;  //joTa
    }

    /**
     * Calcula norma relativa para metodo Jacobi
     *
     * @author Samuel
     */
    private void copiaColuna(Matriz A, Matriz B) {
        double x[] = new double[B.getNumOfLinhas()];
    }

    /**
     * Metodo para realizar Aliminacao de Gauss para sistemas lineares
     * @author Samuel
     */
    public double[] gauss(Matriz a, double b[]) {

        int n = a.getMatriz().length;
        double A[][] = a.getMatriz();
        double mult = 0.0;
        double x[] = new double[n];
        double soma = 0.0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                mult = A[j][i] / A[i][i];
                for (int k = 0; k < n; k++) {
                    A[j][k] = -A[i][k] * mult + A[j][k];
                }
                b[j] = -b[i] * mult + b[j];
            }
        }
        x[n - 1] = b[n - 1] / A[n - 1][n - 1];
        for (int i = n - 1; i >= 0; i--) {
            soma = 0;
            for (int j = i + 1; j < n; j++) {
                soma = soma + A[i][j] * x[j];
            }
            x[i] = (b[i] - soma) / A[i][i];
        }
        return x;
    }

    /* Calculando SBF inicial */
    private void passo1() {
    }

    /* Calculando os custos reduzidos dos indices nao basicos */
    private void passo2() {
    }

    /* Computa vetor u */
    private void passo3() {
    }

    /* Determina o valor de Theta */
    private void passo4() {
    }

    /* Atualiza variavel basica e nao-basica */
    private void passo5() {
    }

    /**
     *    Inicia o programa com iteração 0
     */
    public void start(Matriz m, double b[], double c[], int[] indicesBase, int[] indicesNaoBase) {

        int iteracao = 0;

        /* Calculando SBF inicial */
        passo1();

        /* Calculando os custos reduzidos dos indices nao basicos */
        passo2();

        /* Computa vetor u */
        passo3();

        /* Determina o valor de Theta */
        passo4();

        /* Atualiza variavel basica e nao-basica */
        passo5();

    }
}
