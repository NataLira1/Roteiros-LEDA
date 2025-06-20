package orderStatistic;

import util.Util;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 *
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author adalberto e campelo
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calcular o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os dados em duas partes, baseando-se no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a complexidade de O(n.log n) para O(n).
	 *
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 *
	 *
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento
	 *            este array normalmente nao esta ordenado
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 *
	 */

	private Util util;


	public T quickSelect(T[] array, int k) {

		return quickBinary(array, 0, array.length - 1, k);

	}


	public T quickBinary (T[] array, int leftIndex, int rightIndex, int k){

		if(k > array.length || array.length == 0 || array == null){
			return null;
		}

		int indicePivo = partition(array, leftIndex, rightIndex);

		if(indicePivo+1 == k){
			return array[indicePivo];

		} else if(indicePivo+1 > k){

			return quickBinary(array, leftIndex, indicePivo -1 , k);

		} else{

			return quickBinary(array, indicePivo + 1, rightIndex, k);
		}

	}


	public int partition(T[]array, int left, int right){
		T pivo = array[left];

		int i = left;

		for(int j = left + 1; j <= right; j++){
			if(array[j].compareTo(pivo) <= 0){
				i++;
				util.swap(array, i, j);
			}
		}

		util.swap(array, left, i);
		return i;
	}

}