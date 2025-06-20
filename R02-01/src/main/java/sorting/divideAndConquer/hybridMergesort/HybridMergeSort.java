package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	private int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		if(array.length > SIZE_LIMIT){
			if(leftIndex < rightIndex){
				int middle = (leftIndex + rightIndex) / 2;
				sort(array, leftIndex, middle);
				sort(array, middle + 1, rightIndex);
			
				merge(array, leftIndex, middle, rightIndex);
			}
		} else {
			insertionsort(array, leftIndex, rightIndex);
		}
	}

	public void insertionsort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex; i <= rightIndex; i++){
			T chave = array[i];
			int aux = i - 1;
			INSERTIONSORT_APPLICATIONS++;
			while((aux >= 0) && array[aux].compareTo(chave) > 0){
				array[aux + 1] = array[aux];
				aux = aux - 1;
			}
			array[aux + 1] = chave;
		}
	}

	public void merge(T[] v, int leftIndex, int middle, int rightIndex) {

		T[] arrayAuxiliar = (T[])new Comparable[v.length];
			
		
        for(int i = leftIndex; i <= rightIndex; i++){
			arrayAuxiliar[i] = v[i];
		}

		//INDICES

		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

		while(i <= middle && j <= rightIndex){
			MERGESORT_APPLICATIONS++;
			if(arrayAuxiliar[i].compareTo(arrayAuxiliar[j]) < 0){
				v[k] = arrayAuxiliar[i];
                i++;
			} else {
				v[k] = arrayAuxiliar[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			MERGESORT_APPLICATIONS++;
            v[k] = arrayAuxiliar[i];
            i++;
            k++;
        }
		
		while (j <= rightIndex) {
			MERGESORT_APPLICATIONS++;
            v[k] = arrayAuxiliar[j];
            j++;
            k++;
        }
	}
}
