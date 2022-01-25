package Data;

import Data_Structures.KdTree;

public class CentralBank extends Bank {
    KdTree branches = new KdTree();

    public CentralBank(Coordinates coordinates, String bankName) {
        super(coordinates, bankName);
    }

    public KdTree getBranches() {
        return branches;
    }

    @Override
    public String toString() {
        return "CentralBank{" +
                "coordinates=" + coordinates +
                ", bankName='" + bankName + '\'' +
                ", number of branches=" + branches.size() +
                '}';
    }
}
