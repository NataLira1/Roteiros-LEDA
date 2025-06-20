package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		
		int l = left(position);
		int r = right(position);
		int maior = position;

		if(l <= index && comparator.compare(heap[l], heap[parent(l)]) > 0){
			maior = l;
		} else {
			maior = parent(l);
		}

		if(r <= index && comparator.compare(heap[r], heap[maior]) > 0){
			maior = r;
		}

		if(maior != position){
			Util.swap(this.heap, position, maior);
			heapify(maior);
		}
	}

	

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		
		if(element != null){
			this.index++;
			if(this.isEmpty()){
				this.getHeap()[this.index] = element;
			}else{

				int indice = this.index;
			
				while(indice > 0 && this.comparator.compare(this.getHeap()[parent(indice)], element) < 0){
					Util.swap(this.getHeap(), indice, this.parent(indice));
					indice = parent(indice);
				}
				
				this.getHeap()[indice] = element;
			}
				
			
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array.clone();
		this.index = array.length - 1;

		for(int j = (array.length - 1)/2; j >= 0; j--){
			this.heapify(j);
		}
		
	}

	@Override
	public T extractRootElement() {

		if(this.isEmpty()){
			return null;
		} else {

			T max = rootElement();
			Util.swap(heap, 0, index);
			index--;

			heapify(0);

			return max;
		}
	}

	@Override
	public T rootElement() {

		T resultado = null;

		if(!this.isEmpty()){
			resultado = this.heap[0];
		}

		return resultado;
	}

	@Override
	public T[] heapsort(T[] array) {
		
		Comparator<Integer> comparadorAntigo = (Comparator<Integer>) this.comparator;
		Comparator<Integer> comparadorAtual = (Comparator<Integer>) ((o1, o2) -> o2.compareTo(o1));
		this.comparator = (Comparator<T>) comparadorAtual;
		
		buildHeap(array);

		for (int h = 0; h < array.length; h++) {
			array[h] = this.extractRootElement();
		}

		this.comparator = (Comparator<T>) comparadorAntigo;
		
		return array;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
