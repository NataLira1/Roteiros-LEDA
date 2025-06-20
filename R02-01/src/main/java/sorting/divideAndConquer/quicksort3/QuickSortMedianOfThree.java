package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {


	Util util;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex){
			int meio = mediana(array, leftIndex, rightIndex);
			util.swap(array, rightIndex - 1, meio);
			//int pivo1 = partition(array, leftIndex, rightIndex);
			//sort(array, leftIndex, pivo1-1);
			//sort(array, pivo1+1, rightIndex);
		}
	}

	public int mediana(T[] array, int left, int right){
		int center = (left + right)/2;

		T[] valoresPivos = (T[])new Comparable[3];
		
		valoresPivos[0] = array[left];
		valoresPivos[1] = array[center];
		valoresPivos[2] = array[right];

		inserctionSort(valoresPivos, 0, 2);


		if(valoresPivos[1].compareTo(array[left]) == 0){
			return left;
		} else if(valoresPivos[1].compareTo(array[center]) == 0){
			return center;
		} else {
			return right;
		}

	}

	public void inserctionSort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			T chave = array[i];
			int aux = i - 1;
			while((aux >= 0) && array[aux].compareTo(chave) > 0){
				array[aux + 1] = array[aux];
				aux = aux - 1;
			}
			array[aux + 1] = chave;
		}
	}

	public int partition(T[] array, int left, int right) {
        
        int pivot = (int)array[left];
        int i = left;

        for (int j = left + 1; j <= right; j++) {
            if (array[j].compareTo(array[left]) <= 0) {
                i+=1;
                util.swap(array, i, j);
            }
        }

        util.swap(array, left , i);
         
		return i;
    }


}
