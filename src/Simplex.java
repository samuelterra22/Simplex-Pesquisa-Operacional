/**
 * Created by diego on 28/11/16.
 */

public class Simplex {

    private static final double EPSILON = 1e-10;

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
     * @param vetor   Vetor que deseja printar.
     * @param label   Referencia do print.
     * @author Samuel
     */
    public void printVetor(int[] vetor, String label) {

        System.out.print(label + " -> [ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(" " + vetor[i] + " ");
        }
        System.out.println(" ]");
    }

    /**
     * So imprime uma mensagem
     * @param msg   Mensagem a ser printada na tela
     * @author Samuel
     */
    private void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Multiplica dois vetores, sendo um em forma de matriz
     * @param a     Objeto do tipo Matriz
     * @param x2    Vetor que ira multiplica a matriz
     * @author Samuel
     */
    public double multVetores(Matriz a, double[] x2) {

        double[][] x1 = a.getMatriz();
        double soma = 0;
        for (int i = 0; i < x2.length; i++) {
            soma += x1[i][0] * x2[i];
        }
        return soma;
    }

    /**
     * Imprime vetor de doubles informado
     * @param vetor   Vetor que deseja printar.
     * @param label   Referencia do print.
     * @author Samuel
     */
    public void printVetor(double[] vetor, String label) {

        System.out.print(label + " -> [ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.printf(" %9.2f ", vetor[i]);
        }
        System.out.println(" ]");
    }

    /**
     * Copia determinada coluna de uma matriz, pra outra
     * @param A         Matriz de destino
     * @param B         Matriz de referencia
     * @param destino   indice de destino
     * @param indice    indice de referencia
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
     * @param vetor     Vetor a ser transposto.
     * @author Samuel
     */
    private Matriz t(double[] vetor) {
        double[][] m = new double[vetor.length][1];

        for (int i = 0; i < vetor.length; i++) {
            m[i][0] = vetor[i];
        }

        Matriz M = new Matriz(m);
        return M;
    }

    /**
     * Mutiplica vetor por escalar
     * @param vetor     Vetor que ira ser multiplicado.
     * @param x         Escalar
     * @author Samuel
     */
    private double[] multVetor(double[] vetor, double x) {
        double[] mu = new double[vetor.length];
        for (int i = 0; i < mu.length; i++) {
            if (vetor[i] == 0)
                vetor[i] = 0;
            else
                mu[i] = vetor[i] * x;
        }
        return mu;
    }

    /**
     * Metodo para adicionar uma coluna na posicao 'i' informada.
     * @param A         Matriz que ira receber a coluna.
     * @param coluna    Vetor contendo a coluna.
     * @param  indice   Indice da coluna que onde ira ser adicionada.
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
     * Metodo Gauss com pivoteamento para resolução de sistemas lineares
     *
     * @param indice Indice (iteracao) atual
     * @author Samuel
     */
    public double[] solveGauss(int indice) {
        int N = B.getMatriz().length;

        for (int p = 0; p < N; p++) {

            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(B.getMatriz()[i][p]) > Math.abs(B.getMatriz()[max][p])) {
                    max = i;
                }
            }

            if (max != p) {
                print("Trocando linha " + p + " pela linha " + max);
                double[] temp = B.getMatriz()[p];
                B.getMatriz()[p] = B.getMatriz()[max];
                B.getMatriz()[max] = temp;

                double t = identidade.getColuna(indice)[p];
                identidade.getColuna(indice)[p] = identidade.getColuna(indice)[max];
                identidade.getColuna(indice)[max] = t;
            }

            if (Math.abs(B.getMatriz()[p][p]) <= EPSILON) {
                throw new RuntimeException("Matriz singular");
            }

            for (int i = p + 1; i < N; i++) {
                double alpha = B.getMatriz()[i][p] / B.getMatriz()[p][p];
                identidade.getColuna(indice)[i] -= alpha * identidade.getColuna(indice)[p];
                for (int j = p; j < N; j++) {
                    B.getMatriz()[i][j] -= alpha * B.getMatriz()[p][j];
                }
            }
        }

        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += B.getMatriz()[i][j] * x[j];
            }
            x[i] = (identidade.getColuna(indice)[i] - sum) / B.getMatriz()[i][i];
        }
        return x;
    }

    /**
     * Metodo para calcular a inversa da matriz informada
     * @author Samuel
     */
    public Matriz calculaInversa() {

        Matriz inversa = new Matriz(M, M);

        for (int i = 0; i < identidade.getNumOfColunas(); i++) {
            double[] resultGauss = solveGauss(i);
            inversa = addCol(inversa, resultGauss, i);
        }

        return inversa;
    }

    /**
     * Calculando SBF inicial
     * @param iteracao    Indice (iteracao) atual
     * @author Samuel
     */
    private void passo1(int iteracao) {

        printVetor(indicesBase, "Indices basicos");
        printVetor(indicesNaoBase, "Indices nao basicos");

        identidade = identidade.identidade(M);
        B = null;
        B = new Matriz(M, M); // nova matriz B[m][m]

        // Copia as colunas que formam a base inicial
        for (int i = 0; i < B.getNumOfLinhas(); i++) {
            B = copiaColuna(B, A, i, indicesBase[i]);
        }

        System.out.println("Base:");
        B.show();

        // Calcula a SBF inicial pelo produto da inversa de B com b
        BMenosUm = calculaInversa();//solve(B);

        x = BMenosUm.multVetor(b);

        // exibe a inversa da base
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
     * @param iteracao    Iteracao atual.
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

        for (int j : indicesNaoBase) {

            // Calcula a j-esima direcao factivel pelo produto -B^{-1}A_j, apenas para debug
            double[] col_j = A.getColuna(j);
            double[] direcao = BMenosUm.multVetor(col_j);
            direcao = multVetor(direcao, -1);

            printVetor(col_j, "Culuna Aj: ");

            // Calcula o custo reduzido
            // Custo = c[j] - t(CustoBase) %*% BMenosUm %*% A[,j];
            Matriz custoBaseTransposto = t(custoBase);                      // vetor de custo base transposto como uma matriz[L][1]

            double[] BMenosUmXA_j = BMenosUm.multVetor(A.getColuna(j));     // BMenosUm * A[,j]

            double result = multVetores(custoBaseTransposto, BMenosUmXA_j);
            double custo = c[j] - result;

            print("Custo: " + custo);
            print("Custo Escolhido: " + custoEscolhido);

            // Guarda um indice de direcao basica factivel com custo reduzido negativo, se houver
            if (custo < 0.0) {
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
                    valObjetivo += (custoBase[i] * x[i]);
                    //print(valObjetivo +"+="+custoBase[i] +" * "+x[i]);
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

                return (false);
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
            return (false);
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
                double razao = Math.abs(x[i] / u[i]);

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

        while (true) {
            System.out.println("Iteracao: " + iteracao);
            /* Calculando SBF inicial */
            passo1(iteracao);

            /* Calculando os custos reduzidos dos indices nao basicos */
            if (passo2(iteracao)) {

            /* Computa vetor u */
                if (passo3()) {

                    /* Determina o valor de Theta */
                    passo4();

                    /* Atualiza variavel basica e nao-basica */
                    passo5();

                    iteracao++;
                }
            } else
                break;
        }
        printVetor(x, "X= ");
        return x;
    }
}
