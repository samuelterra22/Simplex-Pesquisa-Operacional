/**
 * Created by samuel on 24/11/16.
 */

public class Matriz {

    private final double[][] matriz;        // vetor M-por-N que representa a matriz
    private int L;                    // numero de linhas (M)
    private int C;                    // numero de colunas (N)

    /**
     * Inicializa uma matriz M por N de zeros.
     * @param L     Indicador do numero de linhas.
     * @param C     Indicador do numero de colunas.
     * @author Samuel
     */
    public Matriz(int L, int C) {
        this.L = L;
        this.C = C;
        matriz = new double[L][C];
    }

    /**
     * Inicializa uma matriz baseada num vetor recebido por parametro
     * @param data     Vetores de vetores doubles contendo os valores da matriz
     * @author Samuel
     */
    public Matriz(double[][] data) {
        this.L = data.length;
        this.C = data[0].length;
        this.matriz = new double[L][C];
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++)
                this.matriz[i][j] = data[i][j];
    }

    /**
     * Inicializa matriz de acordo com um objeto ja pronto
     * @param A     Objeto matriz ja referenciado
     * @author Samuel
     */
    public Matriz(Matriz A) {

        double[][] x = A.getMatriz();
        this.L = x.length;
        this.C = x[0].length;

        this.matriz = x;
    }

    /**
     * Cria e retorna uma matriz identidade
     * @author Samuel
     */
    public Matriz identidade(int C) {
        double I[][] = new double[C][C];
        for (int i = 0; i < C; i++) {
            I[i][i] = 1;
        }
        return new Matriz(I);
    }

    public void Matriz() {
    }

    /**
     * Multiplicação  vetor por matriz
     * @param v     Vetor que se deseja multiplicar
     * @author Diego
     */
    public double[] multVetor(double v[]) {

        double m[][] = this.matriz;

        double[] produto = new double[m.length];
        double aux = 0;

        for (int i = 0; i < this.L; i++) {
            for (int j = 0; j < this.C; j++) {
                aux += (m[i][j] * v[j]);
            }
            produto[i] = aux;
            aux = 0;
        }
        return produto;
    }

    /**
     * Realiza soma da matriz
     * @param B     Matriz que se deseja somar
     * @author Samuel
     */
    public Matriz soma(Matriz B) {
        Matriz A = this.copia();
        if (B.L != A.L || B.C != A.C) throw new RuntimeException("Dimensoes de matriz ilegais.");
        Matriz C = new Matriz(this.L, this.C);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < this.C; j++) {
                C.matriz[i][j] = this.matriz[i][j] + B.matriz[i][j];
            }
        }
        return C;
    }

    /**
     * Retorna o numero de linhas da matriz
     * @author Samuel
     */
    public int getNumOfLinhas() {
        return this.L;
    }

    /**
     * Retorna o numero de colunas da matriz
     * @author Samuel
     */
    public int getNumOfColunas() {
        return this.C;
    }

    /**
     * Compara matriz
     * @param B     Matriz que se deseja comparar
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
     * @param B     Matriz que se deseja subtrair
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
     * @param B     Matriz que se deseja multiplicar
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
     * @param escalar   Escalar que se quer multiplicar a matriz
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
     * @author Samuel
     */
    public Matriz transposta() {
        double[][] trans = new double[L][C];
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++) {
                trans[j][i] = matriz[i][j];
            }
        return new Matriz(trans);
    }

    /**
     * Cria uma copia da matriz
     * @author Samuel
     */
    public Matriz copia() {
        Matriz A = new Matriz(this.L, this.C);
        for (int i = 0; i < this.L; i++) {
            for (int j = 0; j < this.C; j++) {
                A.matriz[i][j] = this.matriz[i][j];
            }
        }
        return A;
    }

    /**
     * Retorna a coluna desejada da matriz da matriz
     * @param indice  Indice da coluna que se quer obter
     * @author Samuel
     */
    public double[] getColuna(int indice) {
        double x[] = new double[this.L];
        for (int i = 0; i < this.L; i++) {
            x[i] = this.matriz[i][indice];
        }
        return x;
    }

    /**
     * Retorna a matriz[][]
     * @author Samuel
     */
    public double[][] getMatriz() {
        return this.matriz;
    }

    /**
     * Imprime matriz no formato padrao
     * @author Samuel
     */
    public void show() {
        for (int i = 0; i < this.L; i++) {
            for (int j = 0; j < this.C; j++) {
                System.out.printf("%9.2f ", matriz[i][j]);
            }
            System.out.println();
        }
    }
}
