package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (array == null || array.length <= 1 || leftIndex >= rightIndex) {
			return;
		}

		int maiorValor = array[leftIndex];

		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if(array[i] > maiorValor){
				maiorValor = array[i];
			}
		}

		int[] C = new int[maiorValor + 1];

		// frequência
		for (int i = leftIndex; i <= rightIndex; i++) {
			C[array[i]] += 1;
		}

		// cumulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}

		int[] B = new int[array.length];

		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[array[i]] -1] = array[i];
			C[array[i]] -= 1;
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			array[i] = B[i];
		}

	}

}
