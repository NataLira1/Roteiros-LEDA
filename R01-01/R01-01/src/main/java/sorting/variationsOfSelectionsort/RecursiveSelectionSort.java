package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex){
			
				int min = selectionMin(array, leftIndex, rightIndex);
				
				Util.swap(array, leftIndex, min);
			
				sort(array, leftIndex + 1, rightIndex);

		}
	}

	public int selectionMin (T[]array, int leftIndex, int rightIndex){
		int min = leftIndex;

		if(leftIndex < rightIndex){
			
			int nextMin = selectionMin(array, leftIndex + 1, rightIndex);
			if(array[nextMin].compareTo(array[min]) < 0){
				min = nextMin;
			}
			
		}
		return min;
	}

}
