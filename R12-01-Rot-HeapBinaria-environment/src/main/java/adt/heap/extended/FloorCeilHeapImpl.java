package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		
		for(int i = 0; i < array.length; i++){
			this.insert(array[i]);
		}

		return this.pisoRecursivo(numero, null);

	}

	public Integer pisoRecursivo (double number, Integer floor) {
		Integer raiz = this.extractRootElement();

		if (raiz != null)
			if (number >= raiz && (floor == null || raiz >= floor))
				floor = this.pisoRecursivo(number, raiz);
			else
				floor = this.pisoRecursivo(number, floor);

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		for(int i = 0; i < array.length; i++){
			this.insert(array[i]);
		}

		return this.ceilRecursivo(numero, null);
	}

	public Integer ceilRecursivo (double numero, Integer ceil) {
		Integer raiz = this.extractRootElement();

		if (raiz != null)
			if (numero <= raiz && (ceil == null || raiz <= ceil))
				ceil = this.ceilRecursivo(numero, raiz);
			else
				ceil = this.ceilRecursivo(numero, ceil);

		return ceil;
	}

}
