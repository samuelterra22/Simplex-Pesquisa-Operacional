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
    private double calculaNormaRelativa(double[] xii, double xi[], int n) {
        double numNorma = 0.0;
        double demNorma = 0.0;
        double diferenca = 0.0;

        for (int i = 0; i < n; i++) {
            diferenca = Math.abs(xii[i] - xi[i]);
            if (diferenca > numNorma)
                numNorma = diferenca;
            if (Math.abs(xii[i]) > demNorma)
                demNorma = Math.abs(xii[i]);
        }
        return (numNorma / demNorma);
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
            if (flag){
                System.out.println("Possue infinitas soluções");
                return;
            }
            iteracao++;
        }
    }
}
