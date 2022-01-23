package Data_Structures;

import Data.Bank;

// Implementation of k-d tree
// this tree contains Bank Nodes
public class KdTree {

    static class Node {
        Bank bank;
        Node r; // right Node
        Node l; // left Node

        Node(Bank bank) {
            this.bank = bank;
            r = l = null;
        }
    }

    private final int k;
    private Node root;
    private int size;

    KdTree() {
        k = 2;
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private Node addNode(Node root, Bank bank, int level) {
        if (root == null) {
            return new Node(bank);
        }

        // based on x
        if (level % k == 0) {
            if (root.bank.getCoordinates().getX() > bank.getCoordinates().getX())
                root.l = addNode(root.l, bank, level + 1);
            else
                root.r = addNode(root.r, bank, level + 1);
        }
        // based on y
        else {
            if (root.bank.getCoordinates().getY() > bank.getCoordinates().getY())
                root.l = addNode(root.l, bank, level + 1);
            else
                root.r = addNode(root.r, bank, level + 1);
        }

        return root;
    }

    public void add(Bank bank) {
        root = addNode(root, bank, 0);
        size++;
    }

    public void printTreePreOrder(Node node) {
        if (node == null) return;
        System.out.println(node.bank);
        printTreePreOrder(node.l);
        printTreePreOrder(node.r);
    }

    public void printTreePreOrder() {
        printTreePreOrder(root);
    }

}
