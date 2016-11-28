/**
 * Created by samuel on 24/11/16.
 */

public class Matriz {

    /**
     * Variáveis Globais
     */
    private static  double[][] BMenosUm = new  double[L][L];
    private static  double[] indicesBase = new double[L];
    private static  double[] indicesNaoBase = new double[C-L];
    private static  double[] u = new double[L];
    private static  int jotaEscolhido = 0;
    private final int L = 9;                // numero de linhas (M)
    private final int C = 13;                // numero de colunas (N)
    private final double[][] matriz;         // vetor M-por-N que representa a matriz


    // inicializa uma matriz M por N de zeros
    public Matriz(int L, int C) {
        this.L = L;
        this.C = C;
        matriz = new double[L][C];
    }

    /**
     * inicializa uma matriz baseada num vetor recebido por parametro
     *
     * @author Samuel
     */
    public Matriz(double[][] matriz) {
        this.matriz = new double[L][C];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
    }

    /**
     * Inicializa matriz de acordo com um objeto ja pronto
     *
     * @author Samuel
     */
    private Matriz(Matriz A) {
        this(A.matriz);
    }

    /**
     * Cria e retorna uma matriz identidade
     *
     * @author Samuel
     */
    public static Matriz identidade(int C) {
        Matriz I = new Matriz(C, C);
        for (int i = 0; i < C; i++) {
            I.matriz[i][i] = 1;
        }
        return I;
    }

    /**
     * Multiplicação matriz por vetor
     *
     * @author Diego
     */
    public static double[] matrixbyVector(double m[][], double v[]) {

        double[] produto = new double[m[0].length];
        double aux = 0;

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                aux = m[i][j] * v[j];
            }
            produto[i] = aux;
            aux = 0;
        }
        return produto;
    }

    /**
     * Multiplicação  vetor por matriz
     *
     * @author Diego
     */
    public static double[] matrixbyVector(double v[], double m[][]) {

        double[] produto = new double[m[0].length];
        double aux = 0;

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                aux = m[i][j] * v[j];
            }
            produto[i] = aux;
            aux = 0;
        }
        return produto;
    }

    /**
     * Realiza soma da matriz
     *
     * @author Samuel
     */
    public Matriz soma(Matriz B) {
        Matriz A = this;
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(L, this.C);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < this.C; j++) {
                C.matriz[i][j] = A.matriz[i][j] + B.matriz[i][j];
            }
        }
        return C;
    }

    /**
     * Compara matriz
     *
     * @author Samuel
     */
    public boolean igual(Matriz B) {
        Matriz A = this;
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                if (A.matriz[i][j] != B.matriz[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * Realiza a subtracao de matriz
     *
     * @author Samuel
     */
    public Matriz menos(Matriz B) {
        Matriz A = this;
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(this.L, this.C);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < this.C; j++) {
                C.matriz[i][j] = A.matriz[i][j] - B.matriz[i][j];
            }
        }
        return C;
    }

    /**
     * Realiza a multiplicacao de matriz
     *
     * @author Samuel
     */
    public Matriz mult(Matriz B) {
        Matriz A = this;
        if (A.C != B.L) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(A.L, B.C);
        for (int i = 0; i < C.L; i++) {
            for (int j = 0; j < C.C; j++) {
                for (int k = 0; k < A.C; k++) {
                    C.matriz[i][j] += (A.matriz[i][k] * B.matriz[k][j]);
                }
            }
        }
        return C;
    }

    /**
     * Multiplica a matriz com um escalar
     *
     * @author Samuel
     */
    public Matriz multEscalar(double escalar) {
        Matriz A = this;
        Matriz C = new Matriz(A.L, A.C);
        for (int i = 0; i < C.L; i++) {
            for (int j = 0; j < C.C; j++) {
                C.matriz[i][j] = A.matriz[i][j] * escalar;
            }
        }
        return C;
    }

    /**
     * Cria e retorna a matriz transposta
     *
     * @author Samuel
     */
    public Matriz transposta() {
        Matriz A = new Matriz(C, L);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++) {
                A.matriz[j][i] = this.matriz[i][j];
            }
        return A;
    }

    /**
     * Cria uma copia da matriz
     *
     * @author Samuel
     */
    public Matriz copia() {
        Matriz A = new Matriz(L, C);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                A.matriz[i][j] = matriz[i][j];
            }
        }
        return A;
    }

    /**
     * Imprime matriz no formato padrao
     *
     * @author Samuel
     */
    public void show() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.printf("%9.4f ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Decomposicao LU
     *
     * @author Samuel
     */
    public Matriz decomposicaoLU(int n) {

        //int n = Math.min(L,C);
        Matriz A = this.copia();
        int pivot[] = new int[n];
        double t, multiplicador;
        int m, p;

        // Inicialização ordenada de Pivot
        for (int i = 0; i < pivot.length; i++) {
            pivot[i] = i;
        }
        for (int j = 0; j < n - 1; j++) {
            // Escolha do pivot
            p = j;
            for (int k = j + 1; k < n; ++k) {
                if (Math.abs(A.matriz[k][j]) > Math.abs(A.matriz[p][j])) {
                    p = k;
                }
            }
            if (p != j) {
                for (int k = 0; k < n; k++) {
                    // Troca das linhas p e j
                    t = A.matriz[j][k];
                    A.matriz[j][k] = A.matriz[p][k];
                    A.matriz[p][k] = t;
                }
                // Armazena permutas de b
                m = pivot[j];
                pivot[j] = pivot[p];
                pivot[p] = m;
            }
            if (Math.abs(A.matriz[j][j]) != 0) {
                for (int i = j + 1; i < n; i++) {
                    // Pivoteamento por eliminacao de Gauss
                    multiplicador = A.matriz[i][j] / A.matriz[j][j];
                    A.matriz[i][j] = multiplicador;
                    // Multiplicacao Mij
                    for (int k = j + 1; k < n; k++) {
                        A.matriz[i][k] = A.matriz[i][k] - (multiplicador * A.matriz[j][k]);
                    }
                }
            }
        }
        return A;
    }

    /**
     * Passo 3: Computa vetor u
     *
     * @author Diego
     *
     *
     */


    public boolean computaVetorU() {

        // Não chegamos em uma solucao ótima ainda.Alguma variável básica deve sair da base para dar
        // lugar a entrada de uma variável não básica.Computa 'u' para verificar se solucao é ilimitada

        // implementa
        double aux[] = new double[C];

        for (int i = 0; i < L; i++) {
            aux[i] = matriz[i][jotaEscolhido];
        }

        u =  matrixbyVector(BMenosUm,aux);

        // Verifica se nenhum componente de u e ' positivo
        boolean existePositivo = false;
        for (int i = 0; i < L; i++) {
            {
                if (u[i] > 0) {
                    existePositivo = true;
                }
            }

            // Testa.Se não houver no vetor 'u' (sinal inverso da direcao factivel)nenhum componente
            // positivo, é porque o valor ótimo é - infinito.
            return existePositivo;

        }
        return false;
    }

    /**
     * Passo 4 : Determina o valor de Theta
     *
     * @author Diego
     */
    public double calcTheta(double b[]) {

        double theta = Double.POSITIVE_INFINITY;
        double indiceL = -1;
        double razao = 0;

        double[] x = new double[C];

        x = b;

        for (int i = 0; i < L; i++) {
            if (u[i] > 0) {
                {
                    //// Calcula a razao
                    razao = x[i] / u[i];

                    //Atualiza a razao, pois encontramos um menor valor de theta
                    if (razao < theta) {
                        theta = razao;
                        indiceL = indicesBase[i];
                    }
                }
            }
        }

        // Exibe variavel que irá deixar a base (apenas debug)
        System.out.println("Variavel  Sai  Base: x[" + indiceL + "], Theta = "+ theta);
        return theta;

    }

    /**
     * Passo 5 : Atualiza variável básica e não-básica
     *
     * @author Diego
     *
     */
    public void atualizaVBandNB() {

        double theta = calcTheta()// implementar

        /*Calcula novo valor da nao-basica, e atualiza base8 */
        for (int i = 0; i < L; i++) {

            //Se encontramos o L-ésimo indica da variável básica que deixará a base, substitui-a
            //pela variável não-básica correspondente à j-ésima direção factível de redução de custo
            if (indicesBase[i] == indiceL) {
                x[i] = theta;
                indicesBase[i] = jotaEscolhido;
            }
        }

        // Para as demais variáveis não básicas, apenas atualiza o índice de quem saiu da base (e
        // entrou no conjunto das não-básicas
        for (int i = 0; i < C - L; i++) {

            if (indicesNaoBase[i] == jotaEscolhido) {
                indicesNaoBase[i] = indiceL;
            }
        }
    }



    /**
     *    Inicia o programa com iteração 0
     */
    public void start() {
        int iteracao = 0;
        boolean flag = true;



        while(true){


            flag = computaVetorU();
            if (!flag){
                System.out.println("Possue infinitas soluções");
            }

            iteracao++;
        }
    }
}
