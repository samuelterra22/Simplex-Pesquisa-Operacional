

/**
 * Created by diego on 28/11/16.
 */


public class Simplex {


    private static  double[][] BMenosUm = new  double[L][L];
    private static  double[] indicesBase = new double[L];
    private static  double[] indicesNaoBase = new double[C-L];
    private static  double[] u = new double[L];
    private static  int jotaEscolhido = 0;  //joTa
    private final int L = 9;                // numero de linhas (M)
    private final int C = 13;                // numero de colunas (N)


    /**
     * Metodo Jacobi para resolver sistemas lineares
     *
     * @author Samuel
     */
    public Matriz jacobi(int n) {

        return null;
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
            aux[i] = Matriz.matriz[i][jotaEscolhido];
        }


        u = Matriz.matrixByVector(BMenosUm, aux);

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
