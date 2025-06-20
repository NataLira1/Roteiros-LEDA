package orderStatistic;

import java.util.Arrays;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

    Util util;


	@Override
	public T[] getKLargest(T[] array, int k) {
		if (k < 1 || k > array.length) {
            return (T[]) new Comparable[0];
        }


        for (int i = 0; i < k; i++) {
            int maximoIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[maximoIndex]) > 0) {
                    maximoIndex = j;
                }
            }
			Util.swap(array, i, maximoIndex);
        
        }


        T[] resultado = (T[]) new Comparable[k];
        System.arraycopy(array, 0, resultado, 0, k);
        return resultado;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
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
