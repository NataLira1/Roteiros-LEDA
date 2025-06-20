package problems;

import adt.linkedList.SingleLinkedListNode;

public class test {
    
    public static void main(String[] args){

        SingleLinkedListNode node1 = new SingleLinkedListNode((Object)1, new SingleLinkedListNode<>() );
        SingleLinkedListNode node2 = new SingleLinkedListNode((Object)2, node1);
        SingleLinkedListNode node3 = new SingleLinkedListNode((Object)2, node2);
        SingleLinkedListNode node4 = new SingleLinkedListNode((Object)1, node3);
        SingleLinkedListNode node5 = new SingleLinkedListNode((Object)3, node4);
        SingleLinkedListNode node6 = new SingleLinkedListNode((Object)2, node5);
        SingleLinkedListNode node7 = new SingleLinkedListNode((Object)8, node6);
        SingleLinkedListNode nill = new SingleLinkedListNode(null, node7);

        LinkedListRemoveDuplicatesImpl linkedListRemoveDuplicatesImpl = new LinkedListRemoveDuplicatesImpl<>();

        linkedListRemoveDuplicatesImpl.removeDuplicates(node7);

        System.out.println(node7.toString());
        System.out.println(node6.toString());
        System.out.println(node5.toString());
        System.out.println(node4.toString());
        System.out.println(node3.toString());
        System.out.println(node2.toString());
        System.out.println(node1.toString());

    }

}
