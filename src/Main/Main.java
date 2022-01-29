package Main;

import Data.*;
import Data_Structures.*;

import java.util.Scanner;

public class Main {
    public static BSTree districts = new BSTree();
    public static HashingLinkedList centralBanksList = new HashingLinkedList();
    public static KdTree centralBanks = new KdTree();
    public static KdTree branches = new KdTree();

    public static boolean pointIsFree(Bank bank) {
        if (centralBanks.find(bank) == null && branches.find(bank) == null)
            return true;
        else {
            System.err.println("** ERROR: coordinates is invalid");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;
        do {
            command = scanner.next();

            switch (command) {
                case "addN": {
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

                    break;
                }
                case "addB": {
                    System.out.println("Adding a new Bank...\n");
                    System.out.println("Enter Bank coordinates ( like this pattern: x y ):");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Coordinates coordinates = new Coordinates(x, y);
                    System.out.println("Enter Bank name:");
                    String bn = scanner.next(); // bank name


                    CentralBank cb = new CentralBank(coordinates, bn);

                    if (!pointIsFree(cb)) continue;

                    centralBanks.add(cb);
                    centralBanksList.add(cb);

                    break;
                }
                case "addBr": {
                    System.out.println("Adding a new Branch...\n");
                    System.out.println("Enter Branch coordinates ( like this pattern: x y ):");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Coordinates coordinates = new Coordinates(x, y);
                    System.out.println("Enter Back name:");
                    String bn = scanner.next(); // bank name

                    System.out.println("Enter Branch name:");
                    String brn = scanner.next(); // branch name

                    BankBranch br = new BankBranch(coordinates, bn, brn);
                    if (!pointIsFree(br)) continue;

                    CentralBank cb = (CentralBank) centralBanksList.search(bn);
                    if (cb == null) {
                        System.err.println("** ERROR: this bank does not exist in database");
                        continue;
                    }
                    cb.getBranches().add(br);
                    branches.add(br);

                    break;
                }
                case "delBr": {
                    System.out.println("Deleting a branch...\n");
                    System.out.println("Enter coordinates:");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Coordinates c = new Coordinates(x, y);

                    BankBranch br = (BankBranch) branches.find(new BankBranch(c, "", ""));
                    if (br == null) {
                        System.err.println("** ERROR: In this coordinated there is no branch");
                        continue;
                    }

                    CentralBank cb = (CentralBank) centralBanksList.search(br.getBankName());
                    cb.getBranches().removeNode(br);
                    branches.removeNode(br);

                    break;
                }
                case "listB": {
                    System.out.println("Searching for Banks in a District...\n");
                    System.out.println("Enter District name:");
                    String dn = scanner.next(); // district name

                    District d = districts.find(dn);
                    if (d == null) {
                        System.err.println("** ERROR: this district does not exist in database");
                        continue;
                    }
                    centralBanks.printBanksInDistrict(d);

                    break;
                }
                case "listBr": {
                    System.out.println("Searching for Bank branches...\n");
                    System.out.println("Enter Bank name:");
                    String bn = scanner.next();
                    CentralBank cb = (CentralBank) centralBanksList.search(bn);
                    if (cb == null) {
                        System.err.println("** ERROR: this bank does not exist in database");
                        continue;
                    }
                    System.out.println("Here are " + bn + "branches:\n");
                    cb.getBranches().printTreePreOrder();

                    break;
                }
                case "nearB": {
                    System.out.println("Searching for nearest Bank...\n");
                    System.out.println("Enter your coordinates ( like this pattern : x y) :");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Coordinates c = new Coordinates(x, y);
                    System.out.println("Here is the nearest Bank:");
                    System.out.println(centralBanks.findNearest(c));

                    break;
                }
                case "nearBr": {
                    System.out.println("Searching for nearest Branch...\n");
                    System.out.println("Enter your coordinates ( like this pattern : x y ) :");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Coordinates c = new Coordinates(x, y);
                    System.out.println("Enter Bank name:");
                    String bn = scanner.next();

                    CentralBank cb = (CentralBank) centralBanksList.search(bn);
                    if (cb == null) {
                        System.err.println("** ERROR: this bank does not exist in database");
                        continue;
                    }
                    System.out.println("Here is the nearest Branch:");
                    System.out.println(cb.getBranches().findNearest(c));

                    break;
                }
                case "availB": {
                    System.out.println("Searching for available Banks in radius R...\n");
                    System.out.println("Enter coordinates:");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    Coordinates c = new Coordinates(x, y);
                    System.out.println("Enter R:");
                    double R = scanner.nextDouble();
                    System.out.println("found banks in this area");
                    centralBanks.availableBanksInR(c, R);
                    break;
                }
                default:
                    if (!command.equals("exit"))
                        System.err.println("** ERROR: Command is not valid");
                    break;
            }
        } while (!command.equals("exit"));
    }
}
