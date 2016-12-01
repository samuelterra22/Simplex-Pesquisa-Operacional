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

    private static double objetivo;
    private static double jEscolhido;

    public Simplex() {
    }

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
     * Multiplica dois vetores, sendo um em forma de matriz
     *
     * @author Samuel
     */
    public double multVetores(Matriz a, double[] x2) {

        double[][] x1 = a.getMatriz();
        double soma = 0;
        for (int i = 0; i < x2.length; i++) {
            soma += x1[0][i] * x2[i];
        }

        return soma;
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
     * Transpoe um vetor, retorna uma matriz Lx1
     *
     * @author Samuel
     */
    private Matriz t(double[] vetor) {
        double[][] m = new double[vetor.length][1];
        for (int i = 0; i < vetor.length; i++) {
            m[0][i] = vetor[i];
        }
        return new Matriz(m);
    }

    /**
     * Mutiplica vetor por escalar
     *
     * @author Samuel
     */
    private double[] multVetor(double[] vetor, double x) {
        double[] mu = new double[vetor.length];
        for (int i = 0; i < mu.length; i++) {
            mu[i] = vetor[i] * x;
        }
        return mu;
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

    /**
     * Metodo para calcular a inversa da matriz informada
     *
     * @author Samuel
     */
    private Matriz calculaInversa(Matriz B) {


        return null;
    }

    /**
     * Calculando SBF inicial
     * @author Samuel
     */
    private void passo1(int iteracao) {

        printVetor(indicesBase, "Indices basicos");
        printVetor(indicesNaoBase, "Indices nao basicos");

        B = new Matriz(A.getNumOfLinhas(), A.getNumOfLinhas()); // nova matriz B[m][m]

        // Copia as colunas que formam a base inicial
        for (int i = 0; i < B.getNumOfLinhas(); i++) {
            copiaColuna(B, A, indicesBase[i]);
        }
        
        // Calcula a SBF inicial pelo produto da inversa de B com b
        BMenosUm = calculaInversa(B);//solve(B);
        x = BMenosUm.multVetor(b);

        // exibe a inversa da mase
        System.out.println("Base^-1:");
        BMenosUm.show();

        // exibe a solucao basica factivel da iteracao corrente, apenas variaveis basicas
        System.out.println("SBF # " + iteracao);
        for (int i = 0; i < indicesBase.length; i++) {
            System.out.println("x[" + indicesBase[i] + "] = " + x[i]);
        }

        // exibe o valor da funcao objetivo
        objetivo = 0.0;
        for (int i = 0; i < indicesBase.length; i++) {
            objetivo += c[indicesBase[i]] * x[i];
        }
        System.out.println("Valor da funcao objetivo: " + objetivo);
    }

    /**
     * Calculando os custos reduzidos dos indices nao basicos
     * Para cada indice nao base, calcula o custo reduzido correspondente
     *
     * @author Samuel
     */
    private void passo2(int iteracao) {

        // Vetor de custo basico, i.e., parte de c que esta relacionado com as variaveis basicas atuais
        double[] custoBase = new double[A.getNumOfLinhas()];
        for (int i = 0; i < A.getNumOfLinhas(); i++) {
            custoBase[i] = c[indicesBase[i]];
        }

        // Exibe vetor de custo basico
        System.out.println("Custo basico: ");
        for (int i = 0; i < A.getNumOfLinhas(); i++) {
            System.out.print("c_B[" + indicesBase[i] + "] = " + custoBase[i]);
        }

        // Escolhe algum indice nao basico cujo custo reduzido he negativo. Dentre os negativos, escolhe 'mais negativo'
        jEscolhido = -1;
        double custoEscolhido = Double.MAX_VALUE;

        for (int j = 0; j < indicesNaoBase.length; j++) {

            // Calcula a j-esima direcao factivel pelo produto -B^{-1}A_j, apenas para debug
            double[] direcao = BMenosUm.multVetor(A.getColuna(j));
            direcao = multVetor(direcao, -1);

            // Calcula o custo reduzido
            // Custo = c[j] - t(CustoBase) %*% BMenosUm %*% A[,j];

            Matriz custoBaseTransposto = t(custoBase);                      // vetor de custo base transposto como uma matriz[L][1]
            double[] BMenosUmXA_j = BMenosUm.multVetor(A.getColuna(j));     // BMenosUm * A[,j]
            double result = multVetores(custoBaseTransposto, BMenosUmXA_j);
            double custo = c[j] - result;

            // Guarda um indice de direcao basica factivel com custo reduzido negativo, se houver
            if (custo < 0) {
                // Atualiza candidata a entrar na base
                if (custo < custoEscolhido) {
                    jEscolhido = j;
                    custoEscolhido = custo;
                }
            }

            // Exibe a j-esima direcao factivel
            System.out.println("Direcao Factivel " + j + ", Custo Reduzido = " + custo);
            for (int i = 0; i < A.getNumOfLinhas(); i++) {
                System.out.println("d_B[" + indicesBase[i] + "] = " + direcao[i]);
            }

            // Se nao encontrou nenhum indice com custo reduzido negativo, he porque chegamos no otimo
            if (jEscolhido == -1) {

                // Exibe solucao ótima (apenas debug)
                double valObjetivo = 0;
                for (int i = 0; i < A.getNumOfLinhas(); i++) {
                    valObjetivo += custoBase[i] * x[i];
                }

                System.out.println("\nObjetivo = " + valObjetivo + "(encontrado na " + iteracao + "a. iteracao)\n");

            }

        }

    }

    /**
     * Computa vetor u
     * @author Samuel
     */
    private void passo3() {
    }

    /**
     * Determina o valor de Theta
     * @author Samuel
     */
    private void passo4() {
    }

    /**
     * Atualiza variavel basica e nao-basica
     * @author Samuel
     */
    private void passo5() {
    }

    /**
     * Laco principal da aplicacao: executa os 5 passos propostos por Bertsimas e Tsiksiklis para realizar
     * uma iteracao completa do metodo Simplex.
     * @author Samuel
     */
    public void start() {

        int iteracao = 0;

        while (true) {
            System.out.println("Iteracao: " + iteracao);
            /* Calculando SBF inicial */
            passo1(iteracao);

            /* Calculando os custos reduzidos dos indices nao basicos */
            passo2(iteracao);

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
