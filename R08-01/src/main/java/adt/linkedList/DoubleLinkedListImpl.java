package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl(){
		super.setHead(new DoubleLinkedListNode<>());
		this.last = new DoubleLinkedListNode<>();
	}

	@Override
	public void insertFirst(T element) {
		
		if(element != null){
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
			newHead.setData(element);
			DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) super.getHead();
			newHead.setNext(head);
			newHead.setPrevious(new DoubleLinkedListNode<T>());
			head.setPrevious(newHead);

			if(head.isNIL()){
				this.last = newHead;
			}
			super.setHead(newHead);
		}
		
	}

	@Override
	public void removeFirst() {
		DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) super.getHead();
		if(!head.isNIL()){
			super.setHead(head.next);
			if(head.isNIL()){
				this.last = head;
			} else {
				head.setPrevious(new DoubleLinkedListNode<>());
			}
		}
	}

	@Override
	public void removeLast() {
		
		DoubleLinkedListNode sentinela = new DoubleLinkedListNode<>();

		if(!this.last.isNIL()){
			this.last = last.getPrevious();
			if(this.last.isNIL()){
				super.setHead(head);
				//this.head = last;
			} else {
				this.last.setNext(sentinela);
			}
		}

	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
