package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return  this.verificaBST(this.bst.getRoot()) || this.bst.isEmpty() ;
	}

	private boolean verificaBST (BTNode<T> node){
		boolean verify = true;

		if (!node.isEmpty()) {
			if (this.verificaLeft(node) && this.verificaRight(node)) {
				verify = this.verificaBST(node.getLeft()) && this.verificaBST(node.getRight());
			}else{
				verify = false;
			}

		}return verify;
	}

	private boolean verificaLeft (BTNode<T> node) {
		return this.verificaLeftRecursivo(node.getLeft(), node);
	}

	private boolean verificaLeftRecursivo (BTNode<T> node, BTNode<T> root) {
		boolean verify = true;

		if (!node.isEmpty()){
			if (node.getData().compareTo(root.getData()) < 0){
				verify = this.verificaLeftRecursivo(node.getLeft(), root) && this.verificaLeftRecursivo(node.getRight(), root);
			}else{
				verify = false;

			}
		}
		return verify;

	}

	private boolean verificaRight (BTNode<T> node) {
		return this.verificaRightRecursivo(node.getRight(), node);
	}

	private boolean verificaRightRecursivo (BTNode<T> node, BTNode<T> root) {
		boolean verify = true;

		if (!node.isEmpty()) {
			if (node.getData().compareTo(root.getData()) > 0) {
				verify = this.verificaRightRecursivo(node.getLeft(), root) && this.verificaRightRecursivo(node.getRight(), root);
			}else{
				verify = false;

			}
		}return verify;
	}
	
}
