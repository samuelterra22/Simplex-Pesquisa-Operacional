/**
 * Created by samuel on 24/11/16.
 */
/*

    REQUISITO 01 - ENTRADA DE DADOS

    REQUISITO 02 - ESTRUTURA DE DADOS MATRIZZ

    OK  (a) Multiplicacao de escalar por matriz
        (b) Produto escalar (apenas para vetores linha ou coluna)
    OK  (c) Produto matricial (considerando a compatibilidade das linhas e colunas)
    OK  (d) Calculo da transposta da matriz
    ?   (e) Calculo da matriz inversa por decomposicao LU para calcular o vetor direção
        factivel d B e para resolver o sistema linear Bx B = b se optar por resolver o sistema por xB = B^−1 b)
    OK  (f) Outras operacoes que surgirem sob demanda durante a implementacao do Simplex

    REQUISITO 03 - CALCULO DA SOLUCAO BASICA INICIAL

    REQUISITO 04 - CALCULO DO CUSTO REDUZIDO

    REQUISITO 05 - CALCULO DA DIRECAO FACTIVEL

    REQUISITO 06 - CALCULO DE TETA (Diego)

    REQUISTO 07 - MUDANCA DE BASE

    REQUISTO 08 - INTEGRACAO COM SOFTWARE DO TRABALHO PRATICO 1

*/
    public class Main{

        public static void main(String[]rgs){

            double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

            Matriz A = new Matriz(a);

            Matriz B = A.decomposicaoLU(3);

            Matriz C = A.mult(B);

            A.show();
            System.out.println();
            B.show();
            System.out.println();
            C.show();

        }
    }