package sorting.divideAndConquer;

import java.util.ArrayList;
import java.util.List;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex){
			int middle = (leftIndex + rightIndex) / 2;
    		sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
    	
    		merge(array, leftIndex, middle, rightIndex);
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
            v[k] = arrayAuxiliar[i];
            i++;
            k++;
        }
		
		while (j <= rightIndex) {
            v[k] = arrayAuxiliar[j];
            j++;
            k++;
        }
	}
}
