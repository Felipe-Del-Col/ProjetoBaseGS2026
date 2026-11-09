
package aplicacao;

import arvores.AvlCidades;
import arvores.AbbCidadesBaixaVacinacao;
import entidades.Cidade;

public class Vacinacao {

	public static void main(String[] args) {

		String cidades[] = {"Analandia", "Araraquara", "Dourado", "Ibitinga", "Matao", "Sao Carlos", "Tabatinga"};

		/*
		 * Os vetores criados sao para que realizar testes solicitados
		 */

		double vacinacao[] = {72.5, 88.4, 71.9, 76, 78.8, 96.7, 66};

		int nCasos[] = {1, 0, 2, 0, 0, 1, 1};

		/*
		 * 1) Insere na AVL um objeto da classe Cidade organizada por nome da cidade
		 *
		 * 2) Usando um metodo elaborado na classe AVL gerar vetor de cidades com vacinacao menor do que
		 *    80% e pelo menos 1 caso.
		 * 	  Depois, ordenar vetor usando quicksort, antes alterar metodo para ordenar vetor
		 *    de elementos objetos da classe Cidade
		 *
		 * 3) Gerar ABB percorrendo AVL, usando um metodo elaborado na classe AVL.
		 *    ABB é organizada pela cobertura vacinal.
		 *
		 */

		AvlCidades avl = new AvlCidades();

		for (int i = 0; i < cidades.length; i++) {

			Cidade cidade = new Cidade(cidades[i], vacinacao[i], nCasos[i]);

			avl.inserirAVL(cidade);
		}

		System.out.println("=== CIDADES CADASTRADAS NA AVL ===");

		avl.mostrarEmOrdem();

		Cidade[] vetorAlerta = avl.gerarVetorAlerta();

		System.out.println("Total de cidades em alerta: " + vetorAlerta.length);

		quicksort(vetorAlerta, 0, vetorAlerta.length - 1);

		System.out.println();
		System.out.println("=== CIDADES EM ALERTA - QUICKSORT ===");

		for (int i = 0; i < vetorAlerta.length; i++) {
			vetorAlerta[i].apresentar();
		}

		AbbCidadesBaixaVacinacao abb = new AbbCidadesBaixaVacinacao();

		avl.gerarAbbAlerta(abb);

		System.out.println();
		System.out.println("=== CIDADES EM ALERTA - ABB ===");
		abb.mostrarEmOrdem();

	}


	public static void quicksort(Cidade x[], int li, int ls) {

		int j;

		if (li < ls) {
			j = particiona(x, li, ls);
			quicksort(x, li, j - 1);
			quicksort(x, j + 1, ls);
		}
	}

	public static int particiona(Cidade x[], int li, int ls) {

		int abaixo, acima;
		Cidade pivo, temp;

		pivo = x[ls];
		acima = ls;
		abaixo = li;

		while (abaixo < acima) {

			while (x[abaixo].getPorcentagem() < pivo.getPorcentagem() && abaixo < ls) {

				abaixo++;
			}

			while (x[acima].getPorcentagem() >= pivo.getPorcentagem() && acima > abaixo) {
				acima--;
			}

			if (abaixo < acima) {
				temp = x[abaixo];
				x[abaixo] = x[acima];
				x[acima] = temp;
			}
		}

		x[ls] = x[acima];
		x[acima] = pivo;
		return acima;
	}
}




