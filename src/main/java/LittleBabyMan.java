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

    static final ArrayList<String> previousMessages = new ArrayList<>();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.printf("Hello I'm LittleBabyMan\n %s \nI'm going to scream. \nWHAT DO YOU WANT??? %s", LOGO, SPACER);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) { /// Exit check
                break;
            }

            if (!checkCommand(input)) { /// If not command, put in list
                previousMessages.add(input);
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
                System.out.printf("%d. %s\n", i + 1, previousMessages.get(i));
            }
            return true;
        }
        return false;
    }
}
