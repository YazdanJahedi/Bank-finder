package Data_Structures;

import Data.Bank;
import Data.BankBranch;
import Data.Coordinates;

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

    public KdTree() {
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

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node addNode(Node root, Bank bank, int level) {
        if (root == null) return new Node(bank);
//        if(root.bank.equals(bank)) {
//            System.out.println("ERROR: this coordinates can not be used!");
//            return root;
//        }

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
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // just based on coordinates
    private boolean findNode(Node root, Bank bank, int level) {
        if (root == null) return false;
        if (root.bank.equals(bank)) return true;

        if (level % k == 0) {
            if (root.bank.getCoordinates().getX() > bank.getCoordinates().getX())
                return findNode(root.l, bank, level + 1);
            else
                return findNode(root.r, bank, level + 1);
        }
        // based on y
        else {
            if (root.bank.getCoordinates().getY() > bank.getCoordinates().getY())
                return findNode(root.l, bank, level + 1);
            else
                return findNode(root.r, bank, level + 1);
        }

    }

    public boolean find(Bank bank) {
        return findNode(root, bank, 0);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private void printTreePreOrder(Node node) {
        if (node == null) return;
        System.out.println(node.bank);
        printTreePreOrder(node.l);
        printTreePreOrder(node.r);
    }

    public void printTreePreOrder() {
        printTreePreOrder(root);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public static void main(String[] args) {
        KdTree t = new KdTree();
        t.add(new Bank(new Coordinates(3, 6), "A"));
        t.add(new Bank(new Coordinates(2, 7), "B"));
        t.add(new Bank(new Coordinates(17, 15), "C"));
        t.add(new Bank(new Coordinates(6, 12), "D"));
        t.add(new Bank(new Coordinates(9, 1), "E"));
        t.add(new Bank(new Coordinates(13, 15), "F"));
        t.add(new Bank(new Coordinates(10, 19), "G"));

        t.printTreePreOrder();
        System.out.println(t.size());
        System.out.println(t.find(new Bank(new Coordinates(17, 15), "C")));
        System.out.println(t.find(new Bank(new Coordinates(17, 15), "T")));


        System.out.println("! ! ! !! ! ! ! ! ! ! ! ");

        KdTree tr = new KdTree();
        tr.add(new BankBranch(new Coordinates(3, 6), "A", "A"));
        tr.add(new BankBranch(new Coordinates(2, 7), "B", "B"));
        tr.add(new BankBranch(new Coordinates(17, 15), "C", "C"));
        tr.add(new BankBranch(new Coordinates(6, 12), "D", "D"));
        tr.add(new BankBranch(new Coordinates(9, 1), "E", "E"));
        tr.add(new BankBranch(new Coordinates(13, 15), "F", "F"));
        tr.add(new BankBranch(new Coordinates(10, 19), "G", "G"));

        tr.printTreePreOrder();
        System.out.println(tr.size());
        System.out.println(tr.find(new BankBranch(new Coordinates(17, 15), "C", "C")));
        System.out.println(tr.find(new BankBranch(new Coordinates(3, 6), " " , " ")));
        System.out.println(tr.find(new BankBranch(new Coordinates(10, 19), "C" , "R")));
        System.out.println(tr.find(new BankBranch(new Coordinates(11, 19), "C" , "R")));
        System.out.println(tr.find(new BankBranch(new Coordinates(17, 14), "C" , "R")));
    }

}
