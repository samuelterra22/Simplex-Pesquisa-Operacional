/**
 * Created by samuel on 24/11/16.
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

    // return C = A * B
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

    // cria e retorna a matriz transposta
    public Matriz transposta() {
        Matriz A = new Matriz(C, L);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                A.matriz[j][i] = this.matriz[i][j];
        return A;
    }

    // retornar x = A^-1 b, assumindo que A é quadrada e tem o rank completo
    public Matriz solve(Matriz rhs) {
        if (L != C || rhs.L != C || rhs.C != 1)
            throw new RuntimeException("Dimensoes de matriz ilegais.");

        // cria copia dos dados
        Matriz A = new Matriz(this);
        Matriz b = new Matriz(rhs);

        // eliminacao Gaussiana com "partial pivoting"
        for (int i = 0; i < L; i++) {

            // encontra linha pivo e trocar
            int max = i;
            for (int j = i + 1; j < L; j++)
                if (Math.abs(A.matriz[j][i]) > Math.abs(A.matriz[max][i]))
                    max = j;
            A.swap(i, max);
            b.swap(i, max);

            // singular (matriz singular nao possui inversa)
            if (A.matriz[i][i] == 0.0) throw new RuntimeException("Matriz he singular. Nao possui inversa.");

            // pivo dentro de b
            for (int j = i + 1; j < C; j++)
                b.matriz[j][0] -= b.matriz[i][0] * A.matriz[j][i] / A.matriz[i][i];

            // pivo dentro de A
            for (int j = i + 1; j < C; j++) {
                double m = A.matriz[j][i] / A.matriz[i][i];
                for (int k = i + 1; k < C; k++) {
                    A.matriz[j][k] -= A.matriz[i][k] * m;
                }
                A.matriz[j][i] = 0.0;
            }
        }

        // substituicao
        Matriz x = new Matriz(C, 1);
        for (int j = C - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < C; k++)
                t += A.matriz[j][k] * x.matriz[k][0];
            x.matriz[j][0] = (b.matriz[j][0] - t) / A.matriz[j][j];
        }
        return x;

    }

    // trocas as linhas i e j
    private void swap(int i, int j) {
        double[] temp = matriz[i];
        matriz[i] = matriz[j];
        matriz[j] = temp;
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
