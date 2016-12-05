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
    OK  (f) Outras operacoes que surgirem sob demanda durante a implementacao do Simplex:
            1 - getColuna    ok

    REQUISITO 03 - CALCULO DA SOLUCAO BASICA INICIAL

    REQUISITO 04 - CALCULO DO CUSTO REDUZIDO

    REQUISITO 05 - CALCULO DA DIRECAO FACTIVEL

    REQUISITO 06 - CALCULO DE THETA (Diego)

    REQUISTO 07 - MUDANCA DE BASE

    REQUISTO 08 - INTEGRACAO COM SOFTWARE DO TRABALHO PRATICO 1

*/

import javax.swing.*;
public class Main{

    private static String marca = "                                << GM PNEUS >>";


        public static void main(String[]rgs){

            String func = JOptionPane.showInputDialog(null,
                    marca + " \n============= Funcionários ==============\n" + "Informe quantidade de funcionários. \n");
            String maxX1 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Máxima ==============\n" + "Informe demanda máxima \nProduto Tipo 1 - (Venda direta, pneu em bom estado). \n");
            String maxX2 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Máxima ==============\n" + "Informe demanda máxima \nProduto Tipo 2 - (Necessita de conserto e revendido). \n");
            String maxX3 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Máxima ==============\n" + "Informe demanda máxima \nProduto Tipo 3 - (Só borracha). \n");
            String maxX4 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Máxima ==============\n" + "Informe demanda máxima \nProduto Tipo 4 - (Serviço de concerto). \n");
            String minX1 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Mínima ==============\n" + "Informe demanda mínima \nProduto Tipo 1 - (Venda direta, pneu em bom estado). \n");
            String minX2 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Mínima ==============\n" + "Informe demanda mínima  \nProduto Tipo 2 - (Necessita de conserto e revendido).\n");
            String minX3 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Mínima ==============\n" + "Informe demanda mínima \nProduto Tipo 3 - (Só borracha). \n");
            String minX4 = JOptionPane.showInputDialog(null,
                    marca + "\n============= Demanda Mínima ==============\n" + "Informe demanda mínima \nProduto Tipo 4 - (Serviço de concerto).\n");


            // Matriz A ja com valores de big M
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
            double M = 100 * 320 * Integer.parseInt(func);
            double[] b = {160 * Integer.parseInt(func), Integer.parseInt(maxX1), Integer.parseInt(maxX2), Integer.parseInt(maxX3), Integer.parseInt(maxX4),
                    Integer.parseInt(minX1), Integer.parseInt(minX2), Integer.parseInt(minX3), Integer.parseInt(minX4)};
            double[] c = {-50, -190, -20, -90, 0, 0, 0, 0, 0, 0, 0, 0, 0, M, M, M, M, M, M, M, M, M};
            int[] indicesBase = {13, 14, 15, 16, 17, 18, 19, 20, 21};
            int[] indicesNaoBase = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

            //  Simplex(Matriz A, double b[], double c[], int[] indicesBase, int[] indicesNaoBase)
            Simplex s = new Simplex(A, b, c, indicesBase, indicesNaoBase);

            s.start();


/*
            //Matriz A ja com valores de big M *//*
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
            int[] indicesBase = {13, 14, 15, 16, 17, 18, 19, 20, 21};
            int[] indicesNaoBase = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

            //  Simplex(Matriz A, double b[], double c[], int[] indicesBase, int[] indicesNaoBase)
            Simplex s = new Simplex(A, b, c, indicesBase, indicesNaoBase);

*/
        }
    }

// https://sites.google.com/site/programacaocpp/calculo-numerico/eliminacao-de-gauss-com-pivoteamento-parcial