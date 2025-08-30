import java.util.Objects;

public class Parser {
    private static final String COMMAND_LIST = """
        ALRIGHT HERE, GIMME SOMETHING TO DO AFTER RAGGGGGHHHHHH!!!
        
        help: see list of commands
        
        list: see all tasks
        
        delete: delete task at position
            delete [list number]
            e.g. delete 3
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
    
    /// if command exists, print stuff rtn true, else rtn false
    public static void processCommand(String input) throws UserInputException {
        //Commands without args below
        if (Objects.equals(input.trim().toLowerCase(), "help")) {
            System.out.printf(COMMAND_LIST);
            return;
        } else if (Objects.equals(input, "list")) {
            TaskList.printTaskList();
            return;
        }
        
        //Commands with args below
        if (checkSpecificCommand(input, "mark")) {
            String msg = getMessageOnly(input, "mark");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("mark");
            }
            int n = Integer.parseInt(msg);
            
            TaskList.markTaskAt(n);
            TaskList.printTaskList();

        } else if (checkSpecificCommand(input, "unmark")) {
            String msg = getMessageOnly(input, "unmark");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("unmark");
            }
            int n = Integer.parseInt(msg);
            
            TaskList.unmarkTaskAt(n);
            TaskList.printTaskList();

        } else if (checkSpecificCommand(input, "delete")) {
            String msg = getMessageOnly(input, "delete");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("delete");
            }
            int n = Integer.parseInt(msg);
            
            Task toDelete = TaskList.deleteTaskAt(n);
            if (toDelete == null) {
                System.out.println("YOUR LIST AIN'T THAT LONG BUDDY");
            } else {
                System.out.printf("ok ITS GONE:\n Deleted: %s\n\n", toDelete);
            }

        } else if (checkSpecificCommand(input, "todo")) {
            String msg = getMessageOnly(input, "todo");

            Task task = new TodoTask(msg);
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            TaskList.addTask(task);
            System.out.printf("OK THEN THERE!!! Added:\n %s", task);

        } else if (checkSpecificCommand(input, "deadline")) {
            String msg = getMessageOnly(input, "deadline").split("/by")[0];

            Task task = new DeadlineTask(msg, getDeadlineTime(input));
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            TaskList.addTask(task);
            System.out.printf("YOU BETTER DO IT IN TIME!!!!!!! Added:\n %s", task);

        } else if (checkSpecificCommand(input, "event")) {
            String msg = getMessageOnly(input, "event").split("/from")[0];

            Task task = new EventTask(msg, getFromTime(input), getToTime(input));
            TaskList.addTask(task);
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            System.out.printf("BE THERE OR ELSE!!!!! Added:\n %s", task);

        } else {
            throw new NotACommandException();
        }
        TaskList.updateListToSave();
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
