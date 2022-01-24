import Data.BankBranch;
import Data.Coordinates;
import Data_Structures.KdTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KdTree centralBanks = new KdTree();
        KdTree branches = new KdTree();
        String command;
        do {
            command = scanner.next();

            if (command.equals("addN")) {

            } else if (command.equals("addB")) {
                System.out.println("Adding a new Bank...\n");
                System.out.println("Enter Bank coordinates ( like this pattern: x y ):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                System.out.println("Enter Bank name:");
                String bn = scanner.next(); // bank name
                System.out.println("Enter number of branches and their names:");
                // todo
                // centralBank.add();
            } else if (command.equals("addBr")) {
                System.out.println("Adding a new Branch...\n");
                System.out.println("Enter Branch coordinates ( like this pattern: x y ):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Coordinates coordinates = new Coordinates(x, y);
                System.out.println("Enter Back name:");
                String bn = scanner.next(); // bank name
                System.out.println("Enter Branch name:");
                String brn = scanner.next(); // branch name
                BankBranch branch = new BankBranch(coordinates, bn, brn);
                branches.add(branch);
                branches.printTreePreOrder();
            } else if (command.equals("delBr")) {

            } else if (command.equals("listB")) {

            } else if (command.equals("listBr")) {

            } else if (command.equals("nearB")) {

            } else if (command.equals("nearBr")) {

            } else if (command.equals("availB")) {

            } else {
                if (!command.equals("exit"))
                    System.out.println("ERROR: Command is not valid");
            }
        } while (!command.equals("exit"));
    }
}
