package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try{
			stack1.push(element);
		} catch(StackOverflowException e){
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		T resultado = null;

		try{
			if(stack2.isEmpty()){
				while(!stack1.isEmpty()){
					stack2.push(stack1.pop());
				}
			}

			resultado = stack2.pop();

		} catch(StackUnderflowException e){
			throw new QueueUnderflowException();
		} catch(StackOverflowException e){
			throw new QueueUnderflowException();
		}
		
		return resultado;
		
	}

	@Override
	public T head() {
	
		T resultado = null;

		try{
			if(stack2.isEmpty()){
				while(!stack1.isEmpty()){
					stack2.push(stack1.pop());
				}
			}

			resultado = stack2.top();

		} catch(StackUnderflowException e){
			
		} catch(StackOverflowException e){
			
		}

		return resultado;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty() && stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull() && stack2.isFull();
	}

}
