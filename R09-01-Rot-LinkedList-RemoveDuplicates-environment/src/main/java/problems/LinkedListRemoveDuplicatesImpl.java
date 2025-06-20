package problems;

import adt.linkedList.SingleLinkedListNode;

public class LinkedListRemoveDuplicatesImpl<T> implements LinkedListRemoveDuplicates<T>{

    /**
     * Restricoes extras:
     * - Você NÃO pode usar recursão
     * - Você pode criar métodos auxiliares se achar necessário, desde que sejam criados
     *   nesta classe
     */
    public void removeDuplicates(SingleLinkedListNode<T> node){
        
        SingleLinkedListNode<T> auxNode = node;
        
        while(!auxNode.isNIL()){

            SingleLinkedListNode<T> auxNode2 = node.getNext();
            
            while(!auxNode2.isNIL()){
                if(auxNode.getData().equals(auxNode2.getData())){
                    auxNode2.setData(auxNode2.getNext().getData());
                    auxNode2.setNext(auxNode2.getNext().getNext());
                }else{
                    auxNode2 = auxNode2.getNext();
                }
               
            }
            auxNode = auxNode.getNext();
        }
    }
}

