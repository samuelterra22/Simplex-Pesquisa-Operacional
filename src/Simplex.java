/**
 * Created by diego on 28/11/16.
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
     * Metodo Jacobi para resolver sistemas lineares
     * @author Samuel
     */
    private Matriz jacobi(Matriz a, double b[], double toler, int maxInter) {

        int n = a.getMatriz().length;
        double A[][] = a.getMatriz();
        double x[] = new double[n];
        double novoX[] = new double[n];
        int inter;
        double normaRelativa = 0.0;
        double soma = 0.0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    A[i][j] = A[i][j] / A[i][i];
                }
            }
            b[i] = b[i] / A[i][i];
            x[i] = b[i];
        }
        inter = 0;

        while ((normaRelativa <= toler) || (inter >= maxInter)) {
            inter++;
            for (int i = 0; i < n; i++) {
                soma = 0.0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        soma = soma + A[i][j] * x[j];
                    }
                }
                novoX[i] = b[i] - soma;
            }
            normaRelativa =
        }

        return null;
    }

    /**
     * Passo 3: Computa vetor u
     * @author Diego
     */
    private boolean computaVetorU() {

        // Não chegamos em uma solucao ótima ainda.Alguma variável básica deve sair da base para dar
        // lugar a entrada de uma variável não básica.Computa 'u' para verificar se solucao é ilimitada

        // implementa
        double aux[] = new double[C];

        //Matriz m = new Matriz(null, c);   <-  tem methodo que retorna a matriz

        for (int i = 0; i < L; i++) {
            //       aux[i] = m.matriz[i][jotaEscolhido];
        }


        //    u = Matriz.matrixByVector(BMenosUm, aux);

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
    private double calcTheta(double b[]) {

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
    private void atualizaVBandNB() {

        // double theta = calcTheta();// implementar

        /*Calcula novo valor da nao-basica, e atualiza base8 */
        for (int i = 0; i < L; i++) {

            //Se encontramos o L-ésimo indica da variável básica que deixará a base, substitui-a
            //pela variável não-básica correspondente à j-ésima direção factível de redução de custo
            //   if (indicesBase[i] == indiceL) {
            //       x[i] = theta;
                indicesBase[i] = jotaEscolhido;
            //   }
        }

        // Para as demais variáveis não básicas, apenas atualiza o índice de quem saiu da base (e
        // entrou no conjunto das não-básicas
        for (int i = 0; i < C - L; i++) {

            if (indicesNaoBase[i] == jotaEscolhido) {
                //     indicesNaoBase[i] = indiceL;
            }
        }
    }



    /**
     *    Inicia o programa com iteração 0
     *
     *
     */
    public void start(Matriz m, double b[], double c[]) {
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
