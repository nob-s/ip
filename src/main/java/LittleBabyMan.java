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

    static final String commandList = """
        ALRIGHT HERE, GIMME SOMETHING TO DO AFTER RAGGGGGHHHHHH!!!
        
        help: see list of commands
        
        list: see all tasks
        
        mark: mark task as complete
            mark [list number]
            e.g. mark 5
        unmark: mark task as incomplete
            unmark [list number]
            e.g. unmark 3
        todo: create a todo task
            todo [description]
            e.g. todo go to the beach
        deadline: create a deadline task
            deadline [description] /by [date and time]
            e.g. deadline work /by Monday 5pm
        event: create an event task
            event [description] /from [date and time] /to [date and time]
            e.g. event bbq /from Tuesday 2pm /to Tuesday 10pm
        """;

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
            try {
                ProcessCommand(input);
            } catch (UserInputException e) {
                System.out.printf(e + SPACER);
            }
        }

        System.out.println("Bye. I'm going to scream again. \nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    }

    /// if command exists, print stuff rtn true, else rtn false
    public static void ProcessCommand(String input) throws UserInputException {
        if (Objects.equals(input.trim().toLowerCase(), "help")) {
            System.out.printf(commandList);
        } else if (Objects.equals(input, "list")) {
            int len = previousMessages.size();
            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                printTaskMessage(i + 1, t);
            }
        } else if (checkSpecificCommand(input, "mark")) {
            int n = Integer.parseInt(getMessageOnly(input, "mark"));
            int len = previousMessages.size();

            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                if (n == i + 1) {
                    t.mark();
                }
                printTaskMessage(i + 1, t);
            }

        } else if (checkSpecificCommand(input, "unmark")) {
            int n = Integer.parseInt(getMessageOnly(input, "unmark"));
            int len = previousMessages.size();

            for (int i = 0; i < len; i++) {
                Task t = previousMessages.get(i);
                if (n == i + 1) {
                    t.unmark();
                }
                printTaskMessage(i + 1, t);
            }

        } else if (checkSpecificCommand(input, "todo")) {
            String msg = getMessageOnly(input, "todo");

            Task task = new TodoTask(msg);
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            previousMessages.add(task);
            System.out.printf("OK THEN THERE!!! Added:\n %s %s", task, SPACER);

        } else if (checkSpecificCommand(input, "deadline")) {
            String msg = getMessageOnly(input, "deadline").split("/by")[0];

            Task task = new DeadlineTask(msg, getDeadlineTime(input));
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            previousMessages.add(task);
            System.out.printf("YOU BETTER DO IT IN TIME!!!!!!! Added:\n %s %s", task, SPACER);

        } else if (checkSpecificCommand(input, "event")) {
            String msg = getMessageOnly(input, "event").split("/from")[0];

            Task task = new EventTask(msg, getFromTime(input), getToTime(input));
            previousMessages.add(task);
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            System.out.printf("BE THERE OR ELSE!!!!! Added:\n %s %s", task, SPACER);

        } else {
            throw new NotACommandException();
        }
    }

    private static boolean checkSpecificCommand(String input, String command) {
        int len = command.length();
        return input.length() >= len && Objects.equals(input.substring(0, len).toLowerCase(), command);
    }

    private static String getMessageOnly(String input, String command) throws NoSpaceAfterCommandException {
        String messageAndSpace = input.substring(command.length());
        if (messageAndSpace.isEmpty() || messageAndSpace.charAt(0) != ' ') {
            throw new NoSpaceAfterCommandException(command);
        }
        return messageAndSpace.substring(1);
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
        String[] fromSplit = toSplit[1].split("/from");
        return fromSplit[0].trim();
    }

    private static String getDeadlineTime(String input) {
        String[] deadlineSplit = input.split("/by");
        if (deadlineSplit.length < 2) {
            return "?";
        }
        return deadlineSplit[1].trim();
    }
}
