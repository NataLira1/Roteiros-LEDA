package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	Util util;

	@Override
	public Integer floor(Integer[] array, Integer x) {
		// fazer validações
		quickSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
		System.out.println(" ");
		return floorBinario(array, 0, array.length - 1, x);
	}

	public Integer floorBinario(Integer[] array, int leftIndex, int rigthIndex, int x) {
		int candidato = array[leftIndex];
		if (leftIndex <= rigthIndex) {

			int middle = (leftIndex + rigthIndex) / 2;
			if (array[middle] == x) {
				candidato = array[middle];
			} else if (array[middle] > x) {
				if(rigthIndex-leftIndex<2){
					if(array[leftIndex]>x){
						return null;
					}
				}
				else{
					candidato = floorBinario(array, leftIndex, middle - 1, x);
				}
				candidato = floorBinario(array, leftIndex, middle - 1, x);
			} else if (array[middle] < x) {
				if (rigthIndex-leftIndex<2){
					if(array[rigthIndex]<x){
						candidato = array[rigthIndex];
					}
				}
				else{
					candidato = floorBinario(array, middle, rigthIndex, x);
				}
			}
		


		}
		return candidato;

	}



	private void quickSort (Integer[] array, int left, int right) {
		if (left < right) {
			int indexPivot = this.partition(array, left, right);
			this.quickSort(array, left, indexPivot - 1);
			this.quickSort(array, indexPivot + 1, right);
		}
	}


	public int partition(Integer[]array, int left, int right){
		Integer pivo = array[left];

		int indexPivo = left;

		for(int j = left + 1; j <= right; j++){
			if(array[j] <= pivo){
				indexPivo++;
				util.swap(array, indexPivo, j);
			}
		}

		util.swap(array, left, indexPivo);
		return indexPivo;
	}


}
