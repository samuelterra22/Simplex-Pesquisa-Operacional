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

    REQUISITO 06 - CALCULO DE THETA (Diego)

    REQUISTO 07 - MUDANCA DE BASE

    REQUISTO 08 - INTEGRACAO COM SOFTWARE DO TRABALHO PRATICO 1

*/

public class Main{

        public static void main(String[]rgs){

            double[][] a = {
                    {0, 4, 0.2, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

            Matriz A = new Matriz(a);
            double M = 100 * 320;
            double[] b = {320, 50, 30, 60, 50, 10, 20, 20, 30};
            double[] c = {-50, -190, -20, -90, 0, 0, 0, 0, 0, 0, 0, 0, 0, M, M, M, M, M, M, M, M, M};

            Simplex s = new Simplex();

        }
    }