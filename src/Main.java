import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.next();

            if (command.equals("addN")) {

            } else if (command.equals("addB")) {

            } else if (command.equals("addBr")) {

            } else if (command.equals("delBr")) {

            } else if (command.equals("listB")) {

            } else if (command.equals("listBr")) {

            } else if (command.equals("nearB")) {

            } else if (command.equals("nearBr")) {

            } else if (command.equals("availB")) {

            } else {
                System.out.println("ERROR: Command is not valid");
            }
        } while (!command.equals("exit"));
    }
}
