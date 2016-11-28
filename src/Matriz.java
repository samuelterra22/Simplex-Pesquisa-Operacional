/**
 * Created by samuel on 24/11/16.
 */

public class Matriz {

    private static double[][] matriz;        // vetor M-por-N que representa a matriz
    private int L;                    // numero de linhas (M)
    private int C;                    // numero de colunas (N)

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
    public Matriz(double[][] data) {
        this.L = data.length;
        this.C = data[0].length;
        matriz = new double[L][C];
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                matriz[i][j] = data[i][j];
    }

    /**
     * Inicializa matriz de acordo com um objeto ja pronto
     *
     * @author Samuel
     */
    private Matriz(Matriz A) {
        this(matriz);
    }

    /**
     * Cria e retorna uma matriz identidade
     *
     * @author Samuel
     */
    public static Matriz identidade(int C) {
        Matriz I = new Matriz(C, C);
        for (int i = 0; i < C; i++) {
            matriz[i][i] = 1;
        }
        return I;
    }

    /**
     * Multiplicação matriz por vetor
     *
     * @author Diego
     */
    public double[] matrixByVector(double m[][], double v[]) {

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
    public double[] matrixByVector(double v[], double m[][]) {

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

    public void Matriz() {
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
                matriz[i][j] = matriz[i][j] + matriz[i][j];
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
                if (matriz[i][j] != matriz[i][j]) return false;
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
                matriz[i][j] = matriz[i][j] - matriz[i][j];
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
                    matriz[i][j] += (matriz[i][k] * matriz[k][j]);
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
                matriz[i][j] = matriz[i][j] * escalar;
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
                matriz[j][i] = matriz[i][j];
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
                matriz[i][j] = matriz[i][j];
            }
        }
        return A;
    }

    /**
     * Imprime matriz no formato padrao
     *
     * @author Samuel
     */
    public double[][] getMatriz() {
        return matriz;
    }

    /**
     * Imprime matriz no formato padrao
     *
     * @author Samuel
     */
    public void show() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.printf("%9.2f ", matriz[i][j]);
            }
            System.out.println();
        }
    }


}
