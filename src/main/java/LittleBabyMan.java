import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LittleBabyMan {


    static final String LOGO = """
             _        ____     __  __\s
            | |      | __ )   |  \\/  |
            | |      |  _ \\   | |\\/| |
            | |___   | |_) |  | |  | |
            |_____|  |____/   |_|  |_|
            """;
    static final String SPACER = "\n_________________________________________________________________________\n";

    static final ArrayList<Task> previousMessages = new ArrayList<>();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.printf("Hello I'm LittleBabyMan\n %s \nI'm going to scream. \nWHAT DO YOU WANT??? %s", LOGO, SPACER);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (Objects.equals(input.toLowerCase(), "bye")) { /// Exit check
                break;
            }

            if (!isValidCommand(input) && !input.trim().isEmpty()) { /// If not command, put in list
                System.out.printf("GIMMME SOMETHING TO DO WAHHHHHHHHHHHHHHHHHHHHHH %s", SPACER);
            }
        }

        System.out.println("Bye. I'm going to scream again. \nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    }

    /// if command exists, print stuff rtn true, else rtn false
    public static boolean isValidCommand(String input) {
        if (Objects.equals(input, "list")) {
            int len = previousMessages.size();
            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                printTaskMessage(i + 1, t);
            }
            return true;
        } else if (checkSpecificCommand(input, "mark")) {
            int commandLen = "mark".length() + 1;
            int n = input.charAt(commandLen) - '0';
            int len = previousMessages.size();

            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                if (n == i + 1) {
                    t.mark();
                }
                printTaskMessage(i + 1, t);
            }

            return true;
        } else if (checkSpecificCommand(input, "unmark")) {
            int commandLen = "unmark".length() + 1;
            int n = input.charAt(commandLen) - '0';
            int len = previousMessages.size();

            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                if (n == i + 1) {
                    t.unmark();
                }
                printTaskMessage(i + 1, t);
            }

            return true;
        } else if (checkSpecificCommand(input, "todo")) {
            int commandLen = "todo".length() + 1;
            String msg = input.substring(commandLen);

            Task task = new TodoTask(msg);
            previousMessages.add(task);
            System.out.printf("OK THEN THERE!!! Added:\n %s %s", task, SPACER);

            return true;
        } else if (checkSpecificCommand(input, "deadline")) {
            int commandLen = "deadline".length() + 1;
            String msg = input.substring(commandLen).split("/by")[0];

            Task task = new DeadlineTask(msg, getDeadlineTime(input));
            previousMessages.add(task);
            System.out.printf("YOU BETTER DO IT IN TIME!!!!!!! Added:\n %s %s", task, SPACER);

            return true;
        } else if (checkSpecificCommand(input, "event")) {
            int commandLen = "event".length() + 1;
            String msg = input.substring(commandLen).split("/from")[0];

            Task task = new EventTask(msg, getFromTime(input), getToTime(input));
            previousMessages.add(task);
            System.out.printf("BE THERE OR ELSE!!!!! Added:\n %s %s", task, SPACER);

            return true;
        } else {
            return false;
        }
    }
    private static boolean checkSpecificCommand(String input, String command) {
        int len = command.length() + 1;
        return input.length() >= len && Objects.equals(input.substring(0, len).toLowerCase(), command + ' ');
    }

    private static void printTaskMessage(int number, Task task) {
        System.out.printf("%d. %s\n", number, task);
    }

    private static String getFromTime(String input) {
        String[] fromSplit = input.split("/from");
        if (fromSplit.length < 2) {
            return "?";
        }
        String[] toSplit = fromSplit[1].split("/to");
        return toSplit[0].trim();
    }

    private static String getToTime(String input) {
        String[] toSplit = input.split("/to");
        if (toSplit.length < 2) {
            return "?";
        }
        return toSplit[1].trim();
    }

    private static String getDeadlineTime(String input) {
        String[] deadlineSplit = input.split("/by");
        if (deadlineSplit.length < 2) {
            return "?";
        }
        return deadlineSplit[1].trim();
    }
}
