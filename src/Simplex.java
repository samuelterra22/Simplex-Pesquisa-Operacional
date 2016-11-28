

/**
 * Created by diego on 28/11/16.
 */


import java.lang.Math;

public class Simplex {


    private static  double[][] BMenosUm = new  double[L][L];
    private static  double[] indicesBase = new double[L];
    private static  double[] indicesNaoBase = new double[C-L];
    private static  double[] u = new double[L];
    private static  int jotaEscolhido = 0;  //joTa
    private final int L = 9;                // numero de linhas (M)
    private final int C = 13;                // numero de colunas (N)


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

        Matriz m = new Matriz();

        for (int i = 0; i < L; i++) {
            aux[i] = m.matriz[i][jotaEscolhido];
        }


        u =  m.matrixByVector(BMenosUm,aux);

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
     *
     *
     */
    public void start(m,b,c) {
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
