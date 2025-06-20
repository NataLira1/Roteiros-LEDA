package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()){
			throw new StackOverflowException();
		} else if (element != null){
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()){
			throw new StackUnderflowException();
		} else {
			T delete = this.top();
			this.top.removeFirst();
			return delete;
		}
	}

	@Override
	public T top() {
		T resultado = null;

		if(!isEmpty()){
			resultado = ((DoubleLinkedListImpl<T>) this.top).getHead().getData();
		}
		return resultado;
	}

	@Override
	public boolean isEmpty() {
		return this.top == null;
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
