package Data_Structures;

import Data.*;


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

    public Bank find(Bank bank) {
        Node node = findCoordinate(root, bank, 0);
        if (node != null)
            return node.bank;
        return null;
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
    private Node findNearestNode(Node root, Coordinates c, Node bestNode, int dpt) {
        if (root == null)
            return bestNode;

        double rootDist = Math.sqrt(Math.pow(root.bank.getCoordinates().getX() - c.getX(), 2) +
                Math.pow(root.bank.getCoordinates().getY() - c.getY(), 2));
        double bestDist = Math.sqrt(Math.pow(bestNode.bank.getCoordinates().getX() - c.getX(), 2) +
                Math.pow(bestNode.bank.getCoordinates().getY() - c.getY(), 2));

        if (rootDist < bestDist)
            bestNode = root;

        Node goodPart, badPart;
        // based on x
        if (dpt % k == 0) {
            if (root.bank.getCoordinates().getX() < c.getX()) {
                goodPart = root.r;
                badPart = root.l;
            } else {
                goodPart = root.l;
                badPart = root.r;
            }

        }
        // based on y
        else {
            if (root.bank.getCoordinates().getY() < c.getY()) {
                goodPart = root.r;
                badPart = root.l;
            } else {
                goodPart = root.l;
                badPart = root.r;
            }
        }
        bestNode = findNearestNode(goodPart, c, bestNode, dpt + 1);

        // based on x
        if (dpt % k == 0) {
            if (bestDist < Math.abs(root.bank.getCoordinates().getX() - c.getX()))
                bestNode = findNearestNode(badPart, c, bestNode, dpt + 1);
        }
        // based on y
        else {
            if (bestDist < Math.abs(root.bank.getCoordinates().getY() - c.getY()))
                bestNode = findNearestNode(badPart, c, bestNode, dpt + 1);
        }

        return bestNode;
    }

    public Bank findNearest(Coordinates c) {
        Node node = findNearestNode(root, c, root, 0);
        if (node != null) {
            return node.bank;
        }
        System.err.println("** ERROR: there is no bank in database");
        return null;
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
        System.out.println("~ ~ ~ ~ ~ ~ ");
        System.out.println(t.findNearest(new Coordinates(4, 4)));
    }

}
