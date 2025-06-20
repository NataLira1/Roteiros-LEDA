package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		
		if (element != null) {
			if (this.isEmpty()) {
				this.insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> NewNode = new RecursiveDoubleLinkedListImpl<>();
				NewNode.setData(this.data);
				this.data = element;
				NewNode.setNext(this.next);
				this.next = NewNode;
				NewNode.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(NewNode);
			}
		}

	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.next.isEmpty() && this.previous.isEmpty()) {
				this.data = null;
				this.next = null;
				this.previous = null;
			} else {
				this.data = this.next.getData();
				this.next = this.next.getNext();
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
			}
		}

	}

	@Override
	public void removeLast() {

		if(this.isEmpty()){

		}else{
			if(this.previous.isEmpty() && this.next.isEmpty()){
				data = null;
				next = null;
				previous = null;
			} else {
				this.data = next.data;
				this.next = next.next;
				if(this.next != null){
					((RecursiveDoubleLinkedListImpl)this.next).setPrevious(this);
				}
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
