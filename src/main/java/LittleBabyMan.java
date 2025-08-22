import java.util.Objects;
import java.util.Scanner;

public class LittleBabyMan {
    public static void main(String[] args) {
        final String LOGO =   " _        ____     __  __ \n" +
                        "| |      | __ )   |  \\/  |\n" +
                        "| |      |  _ \\   | |\\/| |\n" +
                        "| |___   | |_) |  | |  | |\n" +
                        "|_____|  |____/   |_|  |_|\n";
        final String SPACER = "\n_________________________________________________________________________\n";

        final Scanner scanner = new Scanner(System.in);


        System.out.printf("Hello I'm LittleBabyMan\n %s \nI'm going to scream. \nWHAT DO YOU WANT??? %s", LOGO, SPACER);

        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                break;
            }
            System.out.println(input);
        }

        System.out.println("Bye. I'm going to scream again. \nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    }
}
