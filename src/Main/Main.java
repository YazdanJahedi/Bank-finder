package Main;

import Data.BankBranch;
import Data.CentralBank;
import Data.Coordinates;
import Data.District;
import Data_Structures.BSTree;
import Data_Structures.KdTree;

import java.util.Scanner;

public class Main {
    public static BSTree districts = new BSTree();
    public static KdTree centralBanks = new KdTree();
    public static KdTree branches = new KdTree();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;
        do {
            command = scanner.next();

            if (command.equals("addN")) {
                System.out.println("Adding a new District...\n");
                System.out.println("Enter coordinates (like this pattern: x1 y1 x2 y2 x3 y3 x4 y4): ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Coordinates c1 = new Coordinates(x, y);
                x = scanner.nextInt();
                y = scanner.nextInt();
                Coordinates c2 = new Coordinates(x, y);
                x = scanner.nextInt();
                y = scanner.nextInt();
                Coordinates c3 = new Coordinates(x, y);
                x = scanner.nextInt();
                y = scanner.nextInt();
                Coordinates c4 = new Coordinates(x, y);
                System.out.println("Enter District name:");
                String dn = scanner.next();
                districts.add(new District(c1, c2, c3, c4, dn));

                districts.printTreePreorder();

            } else if (command.equals("addB")) {
                System.out.println("Adding a new Bank...\n");
                System.out.println("Enter Bank coordinates ( like this pattern: x y ):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Coordinates coordinates = new Coordinates(x, y);
                System.out.println("Enter Bank name:");
                String bn = scanner.next(); // bank name
                System.out.println("Enter number of branches and their names:");
                centralBanks.add(new CentralBank(coordinates, bn));
                centralBanks.printTreePreOrder();
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
