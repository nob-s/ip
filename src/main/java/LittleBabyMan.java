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

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.printf("Hello I'm LittleBabyMan\n %s \nI'm going to scream. \nWHAT DO YOU WANT??? %s", LOGO, SPACER);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (Objects.equals(input.toLowerCase(), "bye")) { /// Exit check
                break;
            }
            try {
                CommandProcessor.processCommand(input);
                System.out.println(SPACER);
            } catch (UserInputException e) {
                System.out.printf(e + SPACER);
            } catch (NumberFormatException e) {
                System.out.printf("HOW AM I SUPPOSED TO WORK ON NOT A NUMBER%s", SPACER);
            }
        }

        System.out.println("Bye. I'm going to scream again. \nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    }
}
