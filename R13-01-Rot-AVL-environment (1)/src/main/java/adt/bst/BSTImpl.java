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
		return heightRec(this.root, -1);
	}

	protected int heightRec(BSTNode<T> node, int currentHeight) {

		if (!node.isEmpty()) {
			int left = heightRec((BSTNode<T>) node.getLeft(), currentHeight + 1);
			int right = heightRec((BSTNode<T>) node.getRight(), currentHeight + 1);

			currentHeight = Math.max(left, right);
		}
		return currentHeight;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (!isEmpty()) {
			return searchRec(this.root, element);
		} else
			return new BSTNode.Builder().build();
	}

	private BSTNode<T> searchRec(BSTNode<T> node, T element) {

		BSTNode<T> nodeResult;

		if (node.isEmpty())
			nodeResult = new BSTNode.Builder().build();

		else if (element.compareTo(node.getData()) == 0)
			nodeResult = node;
		else if (element.compareTo(node.getData()) > 0)
			nodeResult = searchRec((BSTNode<T>) node.getRight(), element);
		else
			nodeResult = searchRec((BSTNode<T>) node.getLeft(), element);

		return nodeResult;
	}

	@Override
	public void insert(T element) {
		insertRececursivo(this.root, element);
	}

	protected void insertRececursivo(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) > 0)
				insertRececursivo((BSTNode<T>) node.getRight(), element);

			else if (element.compareTo(node.getData()) < 0)
				insertRececursivo((BSTNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty())
			return null;
		else
			return maximumRec(this.root);
	}

	private BSTNode<T> maximumRec(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {
			return maximumRec((BSTNode<T>) node.getRight());
		} else
			return node;
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty())
			return null;
		else
			return minimumRec(this.root);
	}

	private BSTNode<T> minimumRec(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			return minimumRec((BSTNode<T>) node.getLeft());
		} else
			return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				return minimumRec((BSTNode<T>) node.getRight());
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) < 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				return maximumRec((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) > 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) { //caso se o nó for vazio
				node.setData(null);

			} else if (hasOneChild(node)) { //caso se o nó possui um filho
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else { // caso que o nó possui dois filhos
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}

	protected boolean hasOneChild(BSTNode<T> node) {

		return ((node.getRight().isEmpty() && !node.getLeft().isEmpty()
				|| node.getLeft().isEmpty() && !node.getRight().isEmpty()));
	}

	@Override
	public T[] preOrder() {
		return this.preOrderRecursive(this.root, new ArrayList<>());
	}

	private T[] preOrderRecursive (BSTNode<T> currentNode, List<T> list) {
		if (!currentNode.isEmpty()) {
			list.add(currentNode.getData());
			this.preOrderRecursive((BSTNode<T>) currentNode.getLeft(), list);
			this.preOrderRecursive((BSTNode<T>) currentNode.getRight(), list);
		}

		return (T[]) list.toArray(new Comparable[0]);
	}

	@Override
	public T[] order() {
		return this.orderRecursive(this.root, new ArrayList<>());
	}

	private T[] orderRecursive (BSTNode<T> currentNode, List<T> list) {
		if (!currentNode.isEmpty()) {
			this.orderRecursive((BSTNode<T>) currentNode.getLeft(), list);
			list.add(currentNode.getData());
			this.orderRecursive((BSTNode<T>) currentNode.getRight(), list);
		}

		return (T[]) list.toArray(new Comparable[0]);
	}

	@Override
	public T[] postOrder() {
		return this.postOrderRecursive(this.root, new ArrayList<>());
	}

	private T[] postOrderRecursive (BSTNode<T> currentNode, List<T> list) {
		if (!currentNode.isEmpty()) {
			this.postOrderRecursive((BSTNode<T>) currentNode.getLeft(), list);
			this.postOrderRecursive((BSTNode<T>) currentNode.getRight(), list);
			list.add(currentNode.getData());
		}

		return (T[]) list.toArray(new Comparable[0]);
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
