package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (array == null || array.length <= 1 || leftIndex >= rightIndex) {
			return;
		}

		int maiorValor = array[leftIndex];
		int menorValor = array[leftIndex];

		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if(array[i] > maiorValor){
				maiorValor = array[i];
			}
			if(array[i] < menorValor){
				menorValor = array[i];
			}
		}

		int diferenca = maiorValor - menorValor;

		int[] C = new int[diferenca + 1];

		// frequência
		for (int i = leftIndex; i <= rightIndex; i++) {
			C[array[i]-menorValor] += 1;
		}

		// cumulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}

		int[] B = new int[array.length];

		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[array[i] - menorValor] -1] = array[i];
			C[array[i] - menorValor] -= 1;
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			array[i] = B[i];
		}

	}

}
