/**
 * Created by diego on 28/11/16.
 * Atualizado......
 */


public class Simplex {

    private static Matriz identidade;

    private static int[] indicesBase;
    private static int[] indicesNaoBase;
    private static Matriz BMenosUm;
    private static Matriz A;
    private static Matriz B;
    private static double[] b;
    private static double[] c;
    private static double[] x;

    public Simplex(Matriz A, double b[], double c[], int[] indicesBase, int[] indicesNaoBase) {

        Simplex.A = new Matriz(A);
        Simplex.b = b;
        Simplex.c = c;
        Simplex.indicesBase = indicesNaoBase;
        Simplex.indicesNaoBase = indicesNaoBase;
        identidade = Matriz.identidade(A.getNumOfLinhas());
    }

    /**
     * Imprime vetor de inteiros informado
     *
     * @author Samuel
     */
    private void printVetor(int[] vetor, String label) {

        System.out.print(label + " -> [ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i]);
        }
        System.out.println(" ]");
    }

    /**
     * Imprime vetor de doubles informado
     *
     * @author Samuel
     */
    private void printVetor(double[] vetor, String label) {

        System.out.print(label + " -> [ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.printf("%9.2f ", vetor[i]);
        }
        System.out.println(" ]");
    }

    /**
     * Copia determinada coluna de uma matriz, pra outra
     *
     * @author Samuel
     */
    private Matriz copiaColuna(Matriz A, Matriz B, int indice) {
        double x[] = B.getColuna(indice);
        double aux[][] = B.getMatriz();

        for (int i = 0; i < B.getNumOfLinhas(); i++) {
            for (int j = 0; j < B.getNumOfColunas(); j++) {
                if (j == indice)
                    aux[i][j] = x[i];
            }
        }
        return new Matriz(aux);
    }

    /**
     * Metodo para realizar Eliminacao de Gauss para sistemas lineares
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

    private Matriz calculaInversa(Matriz B) {



        return null;
    }

    /* Calculando SBF inicial */
    private void passo1(int iteracao) {

        printVetor(indicesBase, "Indices basicos");
        printVetor(indicesNaoBase, "Indices nao basicos");

        B = new Matriz(A.getNumOfLinhas(), A.getNumOfLinhas()); // nova matriz B[m][m]


        // Calcula a SBF inicial pelo produto da inversa de B com b
        BMenosUm = calculaInversa(B);//solve(B);
        x = BMenosUm.multVetor(b);

        System.out.println("Base^-1:");
        BMenosUm.show();

        System.out.println("SBF # " + iteracao);


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
    public void start() {

        int iteracao = 0;

        while (true) {
            System.out.println("Iteracao: " + iteracao);
            /* Calculando SBF inicial */
            passo1(iteracao);

            /* Calculando os custos reduzidos dos indices nao basicos */
            passo2();

            /* Computa vetor u */
            passo3();

            /* Determina o valor de Theta */
            passo4();

            /* Atualiza variavel basica e nao-basica */
            passo5();


            iteracao++;
        }

    }
}
