import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LittleBabyMan {


    static final String LOGO =  " _        ____     __  __ \n" +
                                "| |      | __ )   |  \\/  |\n" +
                                "| |      |  _ \\   | |\\/| |\n" +
                                "| |___   | |_) |  | |  | |\n" +
                                "|_____|  |____/   |_|  |_|\n";
    static final String SPACER = "\n_________________________________________________________________________\n";

    static final ArrayList<Task> previousMessages = new ArrayList<>();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.printf("Hello I'm LittleBabyMan\n %s \nI'm going to scream. \nWHAT DO YOU WANT??? %s", LOGO, SPACER);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) { /// Exit check
                break;
            }

            if (!checkCommand(input) && !input.trim().isEmpty()) { /// If not command, put in list
                previousMessages.add(new Task(input));
                System.out.printf("added: %s%s", input, SPACER);
            }
        }

        System.out.println("Bye. I'm going to scream again. \nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    }

    /// if command exists, print stuff rtn true, else rtn false
    public static boolean checkCommand(String input) {
        if (Objects.equals(input, "list")) {
            int len = previousMessages.size();
            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                printTaskMessage(i + 1, t.getMark(), t.getTask());
            }
            return true;
        }
        if (input.length() >= 5 && Objects.equals(input.substring(0, 5).toLowerCase(), "mark ")) {
            int n = input.charAt(5) - '0';
            int len = previousMessages.size();

            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                if (n == i + 1) {
                    t.mark();
                }
                printTaskMessage(i + 1, t.getMark(), t.getTask());
            }

            return true;
        }
        if (input.length() >= 7 && Objects.equals(input.substring(0, 7).toLowerCase(), "unmark ")) {
            int n = input.charAt(7) - '0';
            int len = previousMessages.size();

            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                if (n == i + 1) {
                    t.unmark();
                }
                printTaskMessage(i + 1, t.getMark(), t.getTask());
            }

            return true;
        }
        return false;
    }

    private static void printTaskMessage(int number, boolean mark, String message) {
        System.out.printf("%d.[%s] %s\n", number, mark ? "X" : " ", message);
    }
}
