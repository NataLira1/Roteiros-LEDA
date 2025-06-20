package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int tamanho = 0;

		SingleLinkedListNode<T> auxHead = head;

		while(!auxHead.isNIL()){
			tamanho++;
			auxHead = auxHead.next;
		}
		return tamanho;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = head;
		
		while(!auxHead.isNIL() && !auxHead.data.equals(element)){
			auxHead = auxHead.next;
		}

		return (T)auxHead.getData();
	}

	@Override
	public void insert(T element) {
		
		if(element != null){
			if(isEmpty()){
				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<>());
			} else {
				SingleLinkedListNode auxiliar = this.head;

				while(!auxiliar.isNIL()){
					auxiliar = auxiliar.getNext();
				}
				auxiliar.setData(element);
				auxiliar.setNext(new SingleLinkedListNode<>());
			}
		}
	}

	@Override
	public void remove(T element) {
		 
		if(head.data.equals(element)){
			head = head.next;
		} else {
			SingleLinkedListNode<T> auxHead = head;

			while(!auxHead.isNIL() && !auxHead.data.equals(element)){
				auxHead = auxHead.next;
			}

			if(!auxHead.isNIL()){
				auxHead.data = auxHead.next.data;
				auxHead.next = auxHead.next.next;
			}
		}
/* 
		if (element != null && !this.isEmpty()) {
			SingleLinkedListNode<T> currentNode = this.head;

			while (!currentNode.isNIL() && !currentNode.getData().equals(element))
				currentNode = currentNode.getNext();

			if (!currentNode.isNIL()){
				currentNode.setData(currentNode.getNext().getData());
				currentNode.setNext(currentNode.getNext().getNext());
			}
		}
*/
	}

	@Override
	public T[] toArray() {
		int tamanho = size();
        T[] array = (T[]) new Object[tamanho];

        SingleLinkedListNode<T> auxHead = head;
        int index = 0;
		if(tamanho != 0){
			while (auxHead != null && index < tamanho) {
            array[index] = auxHead.data;
			index++;
            auxHead = auxHead.next;
        	}
		}
        return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
