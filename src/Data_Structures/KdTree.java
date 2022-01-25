package Data_Structures;

import Data.Bank;
import Data.BankBranch;
import Data.Coordinates;
import Data.District;


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

        @Override
        public String toString() {
            return bank.toString();
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
//        if (bank instanceof BankBranch)
//            add(bank);

    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // just based on coordinates
    private Node findCoordinate(Node root, Bank bank, int level) {
        if (root == null || root.bank.equals(bank)) return root;

        if (level % k == 0) {
            if (root.bank.getCoordinates().getX() > bank.getCoordinates().getX())
                return findCoordinate(root.l, bank, level + 1);
            else
                return findCoordinate(root.r, bank, level + 1);
        }
        // based on y
        else {
            if (root.bank.getCoordinates().getY() > bank.getCoordinates().getY())
                return findCoordinate(root.l, bank, level + 1);
            else
                return findCoordinate(root.r, bank, level + 1);
        }

    }

    public Node find(Bank bank) {
        return findCoordinate(root, bank, 0);
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

    private void printBanksInDistrict(Node root, District district, int dpt) {
        if (root == null) return;
        if (district.isInDistrict(root.bank.getCoordinates())) {
            System.out.println(root.bank);
            printBanksInDistrict(root.l, district, dpt + 1);
            printBanksInDistrict(root.r, district, dpt + 1);
        } else {
            // based on x
            if (dpt % k == 0) {
                int bankX = root.bank.getCoordinates().getX();

                if (bankX < district.getSw().getX()) {
                    printBanksInDistrict(root.r, district, dpt + 1);
                } else if (bankX > district.getNe().getX()) {
                    printBanksInDistrict(root.l, district, dpt + 1);
                } else {
                    printBanksInDistrict(root.l, district, dpt + 1);
                    printBanksInDistrict(root.r, district, dpt + 1);
                }
            }
            // based on y
            else {
                int bankY = root.bank.getCoordinates().getY();

                if (bankY < district.getSe().getY()) {
                    printBanksInDistrict(root.r, district, dpt + 1);
                } else if (bankY > district.getNw().getY()) {
                    printBanksInDistrict(root.l, district, dpt + 1);
                } else {
                    printBanksInDistrict(root.l, district, dpt + 1);
                    printBanksInDistrict(root.r, district, dpt + 1);
                }
            }
        }
    }

    public void printBanksInDistrict(District district) {
        printBanksInDistrict(root, district, 0);
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
        System.out.println(tr.find(new BankBranch(new Coordinates(3, 6), " ", " ")));
        System.out.println(tr.find(new BankBranch(new Coordinates(10, 19), "C", "R")));
        System.out.println(tr.find(new BankBranch(new Coordinates(11, 19), "C", "R")));
        System.out.println(tr.find(new BankBranch(new Coordinates(17, 14), "C", "R")));
    }

}
