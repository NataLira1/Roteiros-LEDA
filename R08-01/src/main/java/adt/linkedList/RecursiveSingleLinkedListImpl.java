package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if(isEmpty()){
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()){
			return null;
		} else {
			if(this.data.equals(element)){
				return this.data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(isEmpty()){
			this.data = element;
			next = new RecursiveSingleLinkedListImpl<>();
		} else{
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(isEmpty()){

		} else{
			if(this.data.equals(element)){
				this.data = next.data;
				this.next = next.next;
			} else{
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		List<T> list = new ArrayList<>();
		return this.toArray(list);
	}

	public T[] toArray(List<T> list){
		if(!isEmpty()){
			list.add(this.data);
			this.next.toArray(list);
		} 
		return (T[])list.toArray();
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
