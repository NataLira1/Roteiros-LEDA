package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return this.equalsR(tree1.getRoot(), tree2.getRoot());
	}

	private boolean equalsR (BTNode<T> currentNodeTree1, BTNode<T> currentNodeTree2) {
		if (currentNodeTree1.equals(currentNodeTree2)) {
			if (!currentNodeTree1.isEmpty() && !currentNodeTree2.isEmpty()) {
				return
						this.equalsR(currentNodeTree1.getLeft(), currentNodeTree2.getLeft())
								&& this.equalsR(currentNodeTree1.getRight(), currentNodeTree2.getRight());
			}
			return true;
		}
		return false;
	}


	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = false;

		if (node1.isEmpty() && node2.isEmpty()) {
			result = true;
		} else if (!node1.isEmpty() && !node2.isEmpty()) {
			result = isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
					&& isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}

		return result;
	}


	@Override
	public T orderStatistic(BST<T> tree, int k) {
		int treeSize = tree.size();

		if (k >= 1 && k <= treeSize) {
			BTNode<T> treeMinimum = tree.minimum();

			if (k == 1)
				return treeMinimum.getData();
			else if (k == treeSize)
				return tree.maximum().getData();
			else
				return this.orderStatisticR(tree, treeMinimum, k);
		}

		return null;

	}

	private T orderStatisticR (BST<T> tree, BTNode<T> currentNode, int k) {
		return k == 1
				? currentNode.getData()
				: this.orderStatisticR(tree, tree.sucessor(currentNode.getData()), --k);
	}


}
