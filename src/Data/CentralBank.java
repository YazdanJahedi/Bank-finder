package Data;

import Data_Structures.KdTree;

public class CentralBank extends Bank {
    KdTree branches = new KdTree();

    public CentralBank(Coordinates coordinates, String bankName) {
        super(coordinates, bankName);
    }

}
