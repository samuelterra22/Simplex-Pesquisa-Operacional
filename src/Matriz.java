/**
 * Created by samuel on 24/11/16.
 */

public class Matriz {

    private final int L;             // numero de linhas
    private final int C;             // numero de colunas
    private final double[][] matriz;   // vetor M-por-N que representa a matriz

    // inicializa uma matriz M por N de zeros
    public Matriz(int L, int C) {
        this.L = L;
        this.C = C;
        matriz = new double[L][C];
    }

    // inicializa uma matriz baseada num vetor recebido por parametro
    public Matriz(double[][] matriz) {
        L = matriz.length;
        C = matriz[0].length;
        this.matriz = new double[L][C];
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                this.matriz[i][j] = matriz[i][j];
    }

    // inicializa matriz de acordo com um objeto ja pronto
    private Matriz(Matriz A) {
        this(A.matriz);
    }

    // cria e retorna uma matriz identidade C-por-C
    public static Matriz identidade(int C) {
        Matriz I = new Matriz(C, C);
        for (int i = 0; i < C; i++)
            I.matriz[i][i] = 1;
        return I;
    }

    // cria e retorna a matriz transposta
    public Matriz transposta() {
        Matriz A = new Matriz(C, L);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                A.matriz[j][i] = this.matriz[i][j];
        return A;
    }

}
