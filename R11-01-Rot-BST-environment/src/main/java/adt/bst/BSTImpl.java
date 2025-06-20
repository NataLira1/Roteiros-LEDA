package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}
		return Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
	}


	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.getRoot());

	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> search = new BSTNode<>();
		if (element != null && !node.isEmpty()) {
			if (element.compareTo(node.getData()) < 0) {
				search = this.search(element, (BSTNode<T>) node.getLeft());
			} else if (element.compareTo(node.getData()) > 0) {
				search = this.search(element, (BSTNode<T>) node.getRight());
			} else {
				search = node;
			}
		}
		return search;
	}


	@Override
	public void insert(T element) {
		if (element != null)
			insert(root, element);
	}

	private void insert(BSTNode<T> node, T elem) {
		if (node.isEmpty()) {
			node.setData(elem);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(elem) > 0)
				insert((BSTNode<T>) node.getLeft(), elem);
			else
				insert((BSTNode<T>) node.getRight(), elem);
		}
	}


	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty())
			return null;
		else
			return maximo(root);

	}

	private BSTNode<T> maximo(BSTNode<T> node) {

		if (!node.getRight().isEmpty()) {
			return maximo((BSTNode<T>) node.getRight());
		} else
			return node;
	}


	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> minimo;

		if (this.isEmpty())
			return null;
		else
			return minimo(root);

	}

	private BSTNode<T> minimo(BSTNode<T> node) {

		if (!node.getLeft().isEmpty()) {
			return minimo((BSTNode<T>) node.getLeft());
		} else
			return node;
	}


	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()){
				
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) < 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}

				return parent;

			}else {
				return minimo((BSTNode<T>) node.getRight());
			}
		}
		return null;

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				return minimo((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) > 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}
				return parent;
			}
		}
		return null;

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);

	}

	private void remove(BSTNode<T> no) {
		if (no != null && !no.isEmpty()) {
			if (no.isLeaf()) {
				no.setData(null);
			} else if (verificaChild(no)) {
				if (no.getParent() != null) {
					if (no.getParent().getData().compareTo(no.getData()) > 0) {
						if (!no.getLeft().isEmpty()) {
							no.getParent().setLeft(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setLeft(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					} else {
						if (!no.getLeft().isEmpty()) {
							no.getParent().setRight(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setRight(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					}
				} else {
					if (no.getLeft().isEmpty()) {
						root = (BSTNode<T>) no.getRight();
						root.setParent(null);
					} else {
						root = (BSTNode<T>) no.getLeft();
						root.setParent(null);
					}
				}
			} else {
				BSTNode<T> next = sucessor(no.getData());
				no.setData(next.getData());
				remove(next);
			}
		}

	}


	private boolean verificaChild(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && !node.getRight().isEmpty()) ||
				(!node.getLeft().isEmpty() && node.getRight().isEmpty());
	}


	@Override
	public T[] preOrder() {
		ArrayList<T> list = new ArrayList<>();
		preOrderAux(list, root);
		T[] array = (T[]) list.toArray(new Comparable[list.size()]);
		return array;

	}

	private void preOrderAux(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			preOrderAux(list, (BSTNode<T>) node.getLeft());
			preOrderAux(list, (BSTNode<T>) node.getRight());
		}
	}


	@Override
	public T[] order() {
		T[] arr = (T[]) new Comparable[this.size()];
		ArrayList<T> aux = new ArrayList<>();

		if (!this.isEmpty()) {
			OrderRec(root, aux);

			for (int index = 0; index < aux.size(); index++)
				arr[index] = aux.get(index);
		}
		return arr;

	}


	private void OrderRec(BSTNode<T> node, ArrayList<T> array) {

		if (!node.isEmpty()) {
			OrderRec((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			OrderRec((BSTNode<T>) node.getRight(), array);
		}
	}


	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<>();
		postOrderRec(root, list);
		T[] array = (T[]) list.toArray(new Comparable[list.size()]);
		return array;

	}

	private void postOrderRec(BSTNode<T> node, List<T> list) {

		if (!node.isEmpty()) {
			postOrderRec((BSTNode<T>) node.getLeft(), list);
			postOrderRec((BSTNode<T>) node.getRight(), list);
			list.add(node.getData());
		}
	}


	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
