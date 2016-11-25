/**
 * Created by samuel on 24/11/16.
 */


/*
 METODOS QUE AINDA DEVEM SER CRIADOS:

    OK  (a) Multiplicacao de escalar por matriz
        (b) Produto escalar (apenas para vetores linha ou coluna)
    OK  (c) Produto matricial (considerando a compatibilidade das linhas e colunas)
    OK  (d) Calculo da transposta da matriz
        (e) Calculo da matriz inversa por decomposicao LU para calcular o vetor direção
            factivel d B e para resolver o sistema linear Bx B = b se optar por resolver o sistema por xB = B^−1 b)
        (f) Outras operacoes que surgirem sob demanda durante a implementacao do Simplex

*/

public class Matriz {

    private final int L;                // numero de linhas
    private final int C;                // numero de colunas
    private final double[][] matriz;    // vetor M-por-N que representa a matriz

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

    // return C = A + B
    public Matriz soma(Matriz B) {
        Matriz A = this;
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(L, this.C);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < this.C; j++)
                C.matriz[i][j] = A.matriz[i][j] + B.matriz[i][j];
        return C;
    }

    // A = B ?
    public boolean igual(Matriz B) {
        Matriz A = this;
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                if (A.matriz[i][j] != B.matriz[i][j]) return false;
        return true;
    }

    // return C = A - B
    public Matriz menos(Matriz B) {
        Matriz A = this;
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(this.L, this.C);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < this.C; j++)
                C.matriz[i][j] = A.matriz[i][j] - B.matriz[i][j];
        return C;
    }

    // retorna C = A * B
    public Matriz mult(Matriz B) {
        Matriz A = this;
        if (A.C != B.L) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(A.L, B.C);
        for (int i = 0; i < C.L; i++)
            for (int j = 0; j < C.C; j++)
                for (int k = 0; k < A.C; k++)
                    C.matriz[i][j] += (A.matriz[i][k] * B.matriz[k][j]);
        return C;
    }

    // multiplica a matriz por um escalar
    public Matriz multEscalar(double escalar) {

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                this.matriz[i][j] = this.matriz[i][j] * escalar;
            }
        }

        return null;
    }

    // cria e retorna a matriz transposta
    public Matriz transposta() {
        Matriz A = new Matriz(C, L);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                A.matriz[j][i] = this.matriz[i][j];
        return A;
    }


    // imprime matriz no formato padrao
    public void show() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++)
                System.out.printf("%9.4f ", matriz[i][j]);
            System.out.println();
        }
    }

}
