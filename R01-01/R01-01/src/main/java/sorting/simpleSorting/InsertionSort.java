package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex; i <= rightIndex; i++){
			T chave = array[i];
			int aux = i - 1;
			while((aux >= 0) && array[aux].compareTo(chave) > 0){
				array[aux + 1] = array[aux];
				aux = aux - 1;
			}
			array[aux + 1] = chave;
		}
	}
}
