package Data_Structures;

import Data.Bank;
import Data.CentralBank;
import Data.Coordinates;

public class HashingLinkedList {
    static class LinkedList {
        static class Node {
            Bank bank;
            Node next;

            Node(Bank bank) {
                this.bank = bank;
                next = null;
            }
        }

        Node head = null;
        private int size = 0;

        int size() {
            return size;
        }

        void addFirst(Bank bank) {
            Node nNode = new Node(bank); // new node
            nNode.next = null;

            if (head != null) {
                nNode.next = head;
            }
            head = nNode;
            size++;
        }

        public boolean remove(Bank bank) {
            Node q = head, p = null;

            if (q != null && q.bank.equals(bank)) {
                head = q.next;
                return true;
            }

            while (q != null && !q.bank.equals(bank)) {
                p = q;
                q = q.next;
            }

            if (q != null) {
                assert p != null;
                p.next = q.next;
                return true;
            } else {
                return false;
            }
        }

        Node search(Bank bank) {
            Node q = head;
            while (q != null) {
                if (q.bank.equals(bank))
                    return q;
                q = q.next;
            }
            return null;
        }

        Node search(String bankName) {
            Node q = head;
            while (q != null) {
                if (q.bank.getBankName().equals(bankName))
                    return q;
                q = q.next;
            }
            return null;
        }

        @Override
        public String toString() {
            String ans = "[";
            Node q = head;
            if (q == null)
                ans += "]";
            else
                while (q != null) {
                    ans += q.bank + " ";
                    q = q.next;
                }
            return ans;
        }

    }

    // number of English alphabet letters
    private final LinkedList[] hashTable = new LinkedList[26];

    public HashingLinkedList() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new LinkedList();
        }
    }

    public void add(Bank bank) {
        int firstChar = bank.getBankName().toLowerCase().charAt(0);
        int index = firstChar - (int) 'a';
        hashTable[index].addFirst(bank);
    }

    // search is based on bank's coordinates
    public Bank search(Bank bank) {
        int firstChar = bank.getBankName().toLowerCase().charAt(0);
        int index = firstChar - (int) 'a';
        LinkedList.Node ans = hashTable[index].search(bank);
        if (ans != null) return ans.bank;
        return null;
    }

    // search is based on bank's name
    public Bank search(String bankName) {
        int firstChar = bankName.toLowerCase().charAt(0);
        int index = firstChar - (int) 'a';
        LinkedList.Node ans = hashTable[index].search(bankName);
        if (ans != null) return ans.bank;
        return null;
    }

    public void print() {
        for (LinkedList linkedList : hashTable) {
            System.out.println(linkedList.toString());
        }
    }

    public static void main(String[] args) {
        HashingLinkedList a = new HashingLinkedList();
        a.add(new CentralBank(new Coordinates(1, 2), "Abin"));
        a.add(new CentralBank(new Coordinates(3, 4), "Alef"));
        a.add(new CentralBank(new Coordinates(5, 6), "baba"));
        a.add(new CentralBank(new Coordinates(6, 7), "bita"));
        a.add(new CentralBank(new Coordinates(2, 3), "Boostan"));
        a.add(new CentralBank(new Coordinates(1, 7), "cat"));
        a.add(new CentralBank(new Coordinates(1, 7), "XX"));
        a.add(new CentralBank(new Coordinates(1, 7), "categori"));
        a.add(new CentralBank(new Coordinates(1, 7), "tiger"));
        a.add(new CentralBank(new Coordinates(1, 7), "helloman"));
        a.add(new CentralBank(new Coordinates(1, 7), "Zebra"));
        a.print();
        System.out.println("_________________________________________________________________");
        System.out.println("_________________________________________________________________");
        System.out.println(a.search(new CentralBank(new Coordinates(1, 7), "Zebra")));
        System.out.println(a.search(new CentralBank(new Coordinates(1, 3), "Zebra")));
        System.out.println(a.search(new CentralBank(new Coordinates(1, 7), "ali")));
        System.out.println(a.search(new CentralBank(new Coordinates(1, 7), "tiger")));
        System.out.println(a.search(new CentralBank(new Coordinates(1, 7), "tehran")));
    }
}
