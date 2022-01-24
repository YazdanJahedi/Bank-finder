package Data_Structures;

import Data.District;

// Implementation of BST
// this tree contains District Nodes comparing their names!
public class BSTree {
    static class Node {
        District district;
        Node r; // right
        Node l; // left

        Node(District district) {
            this.district = district;
        }
    }

    private Node root;
    private int size;

    BSTree() {
        root = null;
        size = 0;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node addNode(Node root, District district) {
        if (root == null) {
            root = new Node(district);
            return root;
        }

        if (district.getName().compareTo(root.district.getName()) < 0)
            root.l = addNode(root.l, district);
        else
            root.r = addNode(root.r, district);

        return root;
    }

    public void add(District district) {
        addNode(root, district);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node findNode(Node root, String district) {
        if (root == null || root.district.getName().equals(district))
            return root;

        if (root.district.getName().compareTo(district) < 0)
            return findNode(root.r, district);

        return findNode(root.l, district);
    }

    public Node find(String districtName) {
        return findNode(root, districtName);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private void printTreePreorder(Node root) {
        if (root == null) return;
        System.out.println(root.district.getName());
        printTreePreorder(root.l);
        printTreePreorder(root.r);
    }

    public void printTreePreorder() {
        printTreePreorder(root);
    }
}
