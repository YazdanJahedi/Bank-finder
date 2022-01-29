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
    private void printBanksInR(Node root, Coordinates c, double R, int dpt) {
        if (root == null) return;
        double dist = Math.sqrt(Math.pow(root.bank.getCoordinates().getX() - c.getX(), 2) +
                Math.pow(root.bank.getCoordinates().getY() - c.getY(), 2));

        if (dist < R + 0.001) {
            System.out.println(root.bank);
            printBanksInR(root.l, c, R, dpt + 1);
            printBanksInR(root.r, c, R, dpt + 1);
        } else {
            // based on x
            if (dpt % k == 0) {
                if (root.bank.getCoordinates().getX() < c.getX() && R < c.getX() - root.bank.getCoordinates().getX())
                    printBanksInR(root.r, c, R, dpt + 1);
                else if (root.bank.getCoordinates().getX() > c.getX() && R < root.bank.getCoordinates().getX() - c.getX())
                    printBanksInR(root.l, c, R, dpt + 1);
                else {
                    printBanksInR(root.l, c, R, dpt + 1);
                    printBanksInR(root.r, c, R, dpt + 1);
                }
            }
            // based on y
            else {
                if (root.bank.getCoordinates().getY() < c.getY() && R < c.getY() - root.bank.getCoordinates().getY())
                    printBanksInR(root.r, c, R, dpt + 1);
                else if (root.bank.getCoordinates().getY() > c.getY() && R < root.bank.getCoordinates().getY() - c.getY())
                    printBanksInR(root.l, c, R, dpt + 1);
                else {
                    printBanksInR(root.l, c, R, dpt + 1);
                    printBanksInR(root.r, c, R, dpt + 1);
                }
            }
        }
    }

    public void availableBanksInR(Coordinates c, double R) {
        printBanksInR(root, c, R, 0);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node getMin(Node n1, Node n2, Node n3, int xOrY) {
        Node min = n1;
        if (xOrY == 0) {
            if (n2 != null && n1 != null && n2.bank.getCoordinates().getX() < n1.bank.getCoordinates().getX())
                min = n2;
            if (n3 != null && min != null && n3.bank.getCoordinates().getX() < min.bank.getCoordinates().getX())
                min = n3;
        } else {
            if (n2 != null && n1 != null && n2.bank.getCoordinates().getY() < n1.bank.getCoordinates().getY())
                min = n2;
            if (n3 != null && min != null && n3.bank.getCoordinates().getY() < min.bank.getCoordinates().getY())
                min = n3;
        }

        return min;
    }

    private Node searchForMinimum(Node root, int xOrY, int dpt) {
        if (root == null) return null;

        if (dpt % k == xOrY) {
            if (root.l == null)
                return root;
            else
                return searchForMinimum(root.l, xOrY, dpt + 1);
        }

        return getMin(root,
                searchForMinimum(root.l, xOrY, dpt + 1),
                searchForMinimum(root.r, xOrY, dpt + 1),
                xOrY);
    }

    public Node searchForMinimum(int xOrY) {
        return searchForMinimum(root, xOrY, 0);
    }

    public Node searchForMinimum(Node root, int xOrY) {
        return searchForMinimum(root, xOrY, 0);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node removeNode(Node root, Bank bank, int dpt) {
        if (root == null) return null;

        if (root.bank.equals(bank)) {
            if (root.r != null) {
                Node min = searchForMinimum(root.r, dpt % k);
                root.bank = min.bank;
                root.r = removeNode(root.r, min.bank, dpt + 1);
            } else if (root.l != null) {
                Node min = searchForMinimum(root.l, dpt % k);
                root.bank = min.bank;
                root.l = removeNode(root.l, min.bank, dpt + 1);
            } else {
                root = null;
            }

            return root;
        }

        // based on x
        if (dpt % k == 0) {
            if (bank.getCoordinates().getX() > root.bank.getCoordinates().getX())
                root.r = removeNode(root.r, bank, dpt + 1);
            else
                root.l = removeNode(root.l, bank, dpt + 1);
        }
        // based on y
        else {
            if (bank.getCoordinates().getY() > root.bank.getCoordinates().getY())
                root.r = removeNode(root.r, bank, dpt + 1);
            else
                root.l = removeNode(root.l, bank, dpt + 1);
        }

        return root;
    }

    public void removeNode(Bank bank) {
        Node node = removeNode(root, bank, 0);
        if (node != null) size--;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static void main(String[] args) {
        KdTree t = new KdTree();
        t.add(new Bank(new Coordinates(30, 40), "A"));
        t.add(new Bank(new Coordinates(5, 25), "B"));
        t.add(new Bank(new Coordinates(10, 12), "C"));
        t.add(new Bank(new Coordinates(70, 70), "D"));
        t.add(new Bank(new Coordinates(50, 30), "E"));
        t.add(new Bank(new Coordinates(35, 45), "F"));
//        t.add(new Bank(new Coordinates(1, 1), "J"));
//        t.add(new Bank(new Coordinates(3, 2), "G"));
//        t.add(new Bank(new Coordinates(2, 2), "H"));

        t.printTreePreOrder();
        System.out.println(t.size());
        System.out.println("~ ~ ~ ~ ~ ~ ");

        System.out.println(t.searchForMinimum(0));
        System.out.println(t.searchForMinimum(1));

        System.out.println("~ ~ ~ ~ ~ ~ ");
        System.out.println("~ ~ ~ ~ ~ ~ ");
        System.out.println("~ ~ ~ ~ ~ ~ ");
        System.out.println("~ ~ ~ ~ ~ ~ ");

        t.removeNode(new Bank(new Coordinates(345, 45), ""));
        t.printTreePreOrder();
        System.out.println(t.size());


    }

}
