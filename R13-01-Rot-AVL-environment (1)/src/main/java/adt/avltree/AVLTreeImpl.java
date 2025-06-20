package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {

		int resultado = 0;

		if(node != null){
			resultado = this.heightRec((BSTNode<T>) node.getLeft(), -1) - this.heightRec((BSTNode<T>) node.getRight(), -1);
		}
		return resultado;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if(balance > 1){
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				leftRotation((BSTNode<T>) node.getLeft());
			}
			rightRotation(node);
		} else if (balance < -1){
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
				rightRotation((BSTNode<T>) node.getRight());
			}
			leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	protected void rightRotation(BSTNode<T> node) {

		if (node.equals(this.getRoot())) {
			root = Util.rightRotation(node);
		} else {

			BSTNode<T> aux = Util.rightRotation(node);

			if (aux.getData().compareTo(aux.getParent().getData()) > 0)
				aux.getParent().setRight(aux);
			else
				aux.getParent().setLeft(aux);
		}
	}

	protected void leftRotation(BSTNode<T> node) {

		if (node.equals(this.getRoot()))
			root = Util.leftRotation(node);
		else {

			BSTNode<T> aux = Util.leftRotation(node);

			if (aux.getData().compareTo(aux.getParent().getData()) < 0)
				aux.getParent().setLeft(aux);
			else
				aux.getParent().setRight(aux);
		}
	}

	@Override
	public void insertRececursivo(BSTNode<T> node, T elemento){
		if(node.isEmpty()){
			node.setData(elemento);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if(node.getData().compareTo(elemento) > 0){
				insertRececursivo((BSTNode<T>)node.getLeft(), elemento);
			} else if(node.getData().compareTo(elemento) < 0){
				insertRececursivo((BSTNode<T>)node.getRight(), elemento);
			}
		}

		this.rebalance(node);
	}

	@Override
	public void remove(T element) {

		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				rebalanceUp(node);

			} else if (hasOneChild(node)) {
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

				rebalanceUp(node);
			} else {
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}
}
