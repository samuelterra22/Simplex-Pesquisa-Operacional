/**
 * Created by samuel on 24/11/16.
 */

public class Matriz {

    private final int L = 9;                // numero de linhas (M)
    private final int C = 13;                // numero de colunas (N)
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
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
    }

    // inicializa matriz de acordo com um objeto ja pronto
    private Matriz(Matriz A) {
        this(A.matriz);
    }

    // cria e retorna uma matriz identidade C-por-C
    public static Matriz identidade(int C) {
        Matriz I = new Matriz(C, C);
        for (int i = 0; i < C; i++) {
            I.matriz[i][i] = 1;
        }
        return I;
    }

    // return C = A + B
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

    // A = B ?
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

    // return C = A - B
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

    // retorna C = A * B
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

    // multiplica a matriz por um escalar
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

    // cria e retorna a matriz transposta
    public Matriz transposta() {
        Matriz A = new Matriz(C, L);
        for (int i = 0; i < L; i++)
            for (int j = 0; j < C; j++) {
                A.matriz[j][i] = this.matriz[i][j];
            }
        return A;
    }

    // cria uma copia
    public Matriz copia() {
        Matriz A = new Matriz(L, C);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                A.matriz[i][j] = matriz[i][j];
            }
        }
        return A;
    }

    // imprime matriz no formato padrao
    public void show() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.printf("%9.4f ", matriz[i][j]);
            }
            System.out.println();
        }
    }

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

    public Double calcTheta(int m){
        Double theta  = Double.POSITIVE_INFINITY;
        int indiceL = -1;

        for (int i = 0; i <  L ; i++) {
            if ()
        }
    }

    /**
     *
     * 	#
     # Passo 4: Determina o valor de Theta
     #

     # Chuta um valor alto para o theta, e vai reduzindo de acordo com a razao x_i / u_i
     Theta   <- Inf;
     IndiceL <- -1;

     # Varre indices basicos determinando o valor de theta que garante factibilidade
     for(i in 1:m)
     {
     # Calcula a razao
     if(u[i] > 0)
     {
     # Calcula a razao
     Razao <- x[i] / u[i];

     # Atualiza a razao, pois encontramos um menor valor de theta
     if(Razao < Theta)
     {
     Theta   <- Razao;
     IndiceL <- IndicesBase[i];
     }
     }
     }

     # Exibe variavel que irá deixar a base (apenas debug)
     cat('\tVariavel  Sai  Base: x[', IndiceL, '], Theta = ', Theta, '\n');

     #
     *
    */
}
