/**
 * Created by diego on 28/11/16.
 * Atualizado......
 */


public class Simplex {

    private final int M;
    private final int N;
    private Matriz identidade;
    private int[] indicesBase;
    private int[] indicesNaoBase;
    private Matriz BMenosUm;
    private Matriz A;
    private Matriz B;
    private double[] b;
    private double[] c;
    private double[] x;
    private double objetivo;
    private int jEscolhido;
    private double u[];
    private double theta;
    private int indiceL;


    /*public Simplex() {
        Matriz cu = new Matriz(0,0);
        this.identidade = cu.identidade(3);
    } // contrutor vazio, tirar depois*/

    public Simplex(Matriz A, double b[], double c[], int[] indicesBase, int[] indicesNaoBase) {

        this.A = new Matriz(A);
        this.b = b;
        this.c = c;
        this.indicesBase = indicesBase;
        this.indicesNaoBase = indicesNaoBase;
        this.identidade = new Matriz(0, 0).identidade(A.getNumOfLinhas());

        this.M = A.getNumOfLinhas();
        this.N = A.getNumOfColunas();
    }

    /**
     * Imprime vetor de inteiros informado
     * @author Samuel
     */
    private void printVetor(int[] vetor, String label) {

        System.out.print(label + " -> [ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(" " + vetor[i] + " ");
        }
        System.out.println(" ]");
    }

    /**
     * Multiplica dois vetores, sendo um em forma de matriz
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
     * @author Samuel
     */
    private void printVetor(double[] vetor, String label) {

        System.out.print(label + " -> [ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.printf(" %9.2f ", vetor[i]);
        }
        System.out.println(" ]");
    }

    /**
     * Copia determinada coluna de uma matriz, pra outra
     * @author Samuel
     */
    private Matriz copiaColuna(Matriz A, Matriz B, int destino, int indice) {

        double a[][] = A.getMatriz();
        double b[][] = B.getMatriz();


        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                a[i][destino] = b[i][indice];

            }
        }
        return new Matriz(a);
    }

    /**
     * Transpoe um vetor, retorna uma matriz Lx1
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
     * Metodo para adicionar uma coluna na posicao 'i' informada
     *
     * @author Samuel
     */
    private Matriz addCol(Matriz A, double[] coluna, int indice) {

        double mat[][] = A.getMatriz();

        for (int i = 0; i < A.getNumOfLinhas(); i++) {
            mat[i][indice] = coluna[i];
        }
        return new Matriz(mat);
    }

    /**
     * Metodo para realizar Eliminacao de Gauss para sistemas lineares
     * @author Samuel
     */
    private double[] gauss(Matriz aa, double b[]) {

        Matriz a = aa.copia();

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
     * @author Samuel
     */
    public Matriz calculaInversa(Matriz Bx) {

        Matriz inversa = new Matriz(Bx.getNumOfLinhas(), Bx.getNumOfLinhas());

        for (int i = 0; i < identidade.getNumOfColunas(); i++) {                     // Bx.getNumOfColunas()

            inversa = addCol(inversa, gauss(Bx, this.identidade.getColuna(i)), i);
        }
        return inversa;
    }

    /**
     * Calculando SBF inicial
     * @author Samuel
     */
    private void passo1(int iteracao) {

        printVetor(indicesBase, "Indices basicos");
        printVetor(indicesNaoBase, "Indices nao basicos");

        B = new Matriz(M, M); // nova matriz B[m][m]

        // Copia as colunas que formam a base inicial
        for (int i = 0; i < B.getNumOfLinhas(); i++) {
            B = copiaColuna(B, A, i, indicesBase[i]);
        }


        // Calcula a SBF inicial pelo produto da inversa de B com b
        BMenosUm = calculaInversa(B);//solve(B);
        x = BMenosUm.multVetor(b);


        // exibe a inversa da mase
        System.out.println("Base^-1:");
        BMenosUm.show();

        // exibe a solucao basica factivel da iteracao corrente, apenas variaveis basicas
        System.out.println("SBF # " + iteracao);
        for (int i = 0; i < M; i++) {                                 // indicesBase.length
            System.out.println("x[" + indicesBase[i] + "] = " + x[i]);
        }

        // exibe o valor da funcao objetivo
        objetivo = 0.0;
        for (int i = 0; i < M; i++) {                               //  indicesBase.length
            objetivo += c[indicesBase[i]] * x[i];
        }
        System.out.println("Valor da funcao objetivo: " + objetivo);

    }

    /**
     * Calculando os custos reduzidos dos indices nao basicos
     * Para cada indice nao base, calcula o custo reduzido correspondente
     * @author Samuel
     */
    private boolean passo2(int iteracao) {

        // Vetor de custo basico, i.e., parte de c que esta relacionado com as variaveis basicas atuais
        double[] custoBase = new double[M];

        for (int i = 0; i < M; i++) {
            custoBase[i] = this.c[this.indicesBase[i]];
        }

        // Exibe vetor de custo basico
        System.out.println("Custo basico: ");
        for (int i = 0; i < M; i++) {
            System.out.println("c_B[" + indicesBase[i] + "] = " + custoBase[i]);
        }

        // Escolhe algum indice nao basico cujo custo reduzido he negativo. Dentre os negativos, escolhe 'mais negativo'
        jEscolhido = -1;
        double custoEscolhido = Double.MAX_VALUE;

        for (int j = 0; j < indicesNaoBase.length; j++) {

            System.out.println(A.getNumOfLinhas());

            printVetor(this.A.getColuna(j), "coluna a_j");

            // Calcula a j-esima direcao factivel pelo produto -B^{-1}A_j, apenas para debug
            double[] direcao = BMenosUm.multVetor(A.getColuna(j));
            direcao = multVetor(direcao, -1);

            // Calcula o custo reduzido
            // Custo = c[j] - t(CustoBase) %*% BMenosUm %*% A[,j];

            printVetor(custoBase, "Custo base: ");

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
            for (int i = 0; i < M; i++) {
                System.out.println("d_B[" + indicesBase[i] + "] = " + direcao[i]);
            }

            // Se nao encontrou nenhum indice com custo reduzido negativo, he porque chegamos no otimo
            if (jEscolhido == -1) {

                // Exibe solucao ótima (apenas debug)
                double valObjetivo = 0;
                for (int i = 0; i < M; i++) {
                    valObjetivo += custoBase[i] * x[i];
                }

                System.out.println("\nObjetivo = " + valObjetivo + "(encontrado na " + iteracao + "a. iteracao)\n");

                double[] solucao = new double[N];
                for (int i = 0; i < M; i++) {
                    solucao[indicesBase[i]] = x[i];
                }
                for (int i = 0; i < N; i++) {
                    System.out.println("x[" + i + "] = " + solucao[i]);
                }
                System.out.println("\n\n");
                //
                //
                return (true);                                            /// VERIFICAR RETORNO
            }
        }
        return true;
    }

    /**
     * Computa vetor u
     * @author Samuel
     */
    private boolean passo3() {

        //Exibe quem entra na base
        System.out.println("Variavel Entra Base: x[" + jEscolhido + "]");

        // Não chegamos em uma solucao otima ainda. Alguma variável basica deve sair da base para dar
        // lugar a entrada de uma variavel não basica. Computa 'u' para verificar se solucao eh ilimitada

        u = BMenosUm.multVetor(A.getColuna(jEscolhido));

        // Verifica se nenhum componente de u he positivo
        boolean existePositivo = false;
        for (int i = 0; i < M; i++) {
            if (u[i] > 0)
                existePositivo = true;
        }

        // Testa. Se nao houver no vetor 'u' (sinal inverso da direcao factivel) nenhum componente
        // positivo, eh porque o valor otimo eh - infinito.
        if (!existePositivo) {
            System.out.println("\n\nCusto Otimo = -Infinito");
            return (false);                                                      // VERIFICAR RETORNO
        }
        return true;
    }

    /**
     * Determina o valor de Theta
     * @author Samuel
     */
    private void passo4() {

        // Chuta um valor alto para o theta, e vai reduzindo de acordo com a razao x_i / u_i
        theta = Double.MAX_VALUE;
        indiceL = -1;

        // Varre indices basicos determinando o valor de theta que garante factibilidade
        for (int i = 0; i < M; i++) {

            // Calcula a razao
            if (u[i] > 0) {
                // Calcula a razao
                double razao = x[i] / u[i];

                // Atualiza a razao, pois encontramos um menor valor de theta
                if (razao < theta) {
                    theta = razao;
                    indiceL = indicesBase[i];
                }
            }
        }
        // Exibe variavel que irá deixar a base
        System.out.println("Variavel  Sai  Base: x[" + indiceL + "], Theta = " + theta);
    }

    /**
     * Atualiza variavel basica e nao-basica
     * @author Samuel
     */
    private void passo5() {

        // Calcula novo valor da nao-basica, e atualiza base
        for (int i = 0; i < M; i++) {

            // Se encontramos o L-esimo indica da variavel basica que deixara a base, substitui-a pela variavel
            // nao-basica correspondente a j-esima direção factivel de reducao de custo
            if (indicesBase[i] == indiceL) {
                x[i] = theta;
                indicesBase[i] = jEscolhido;
            }
        }

        // Para as demais variaveis nao basicas, apenas atualiza o indice de quem saiu da base (e entrou no conjunto
        // das nao-basicas
        for (int i = 0; i < (N - M); i++) {
            if (indicesNaoBase[i] == jEscolhido)
                indicesNaoBase[i] = indiceL;
        }

    }

    /**
     * Laco principal da aplicacao: executa os 5 passos propostos por Bertsimas e Tsiksiklis
     * para realizar uma iteracao completa do metodo Simplex.
     * @author Samuel
     */
    public double[] start() {

        int iteracao = 0;
        boolean checkPasso2 = true;
        boolean checkPasso3 = true;


        while (true) {
            System.out.println("Iteracao: " + iteracao);
            /* Calculando SBF inicial */
            passo1(iteracao);

            /* Calculando os custos reduzidos dos indices nao basicos */
            checkPasso2 = passo2(iteracao);

            /* Computa vetor u */
            checkPasso3 = passo3();

            /* Determina o valor de Theta */
            passo4();

            /* Atualiza variavel basica e nao-basica */
            passo5();

            //iteracao++;

            if (!((checkPasso2) || (checkPasso3)))
                break;
        }
        return x;
    }
}
