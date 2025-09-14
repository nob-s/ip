package parser;

import java.util.Objects;

import exceptions.EmptyTaskException;
import exceptions.NoCommandArgumentException;
import exceptions.NoSpaceAfterCommandException;
import exceptions.NotACommandException;
import exceptions.UserInputException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;

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
            e.g. deadline work /by 20-05-2025 0800
        event: create an event task
            event [description] /from [date and time] /to [date and time]
            e.g. event bbq /from 20-05-2025 0800 /to 20-05-2025 1800
        """;
    /**
     * Returns proper output based on user input
     * @param input Raw input of the user taken from Ui class.
     * @throws UserInputException If user input doesn't fit format
     **/
    public static String processCommand(String input) throws UserInputException {
        String response = "";
        //Commands without args below
        if (Objects.equals(input.trim().toLowerCase(), "help")) {
            response += COMMAND_LIST;
        } else if (Objects.equals(input, "list")) {
            response = TaskList.getTaskList();
        } else if (checkSpecificCommand(input, "find")) {
            String msg = getMessageOnly(input, "find");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("mark");
            }
            response = TaskList.getSelectiveTaskList(msg);
            
        } else if (checkSpecificCommand(input, "mark")) {
            String msg = getMessageOnly(input, "mark");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("mark");
            }
            int n = Integer.parseInt(msg);
            
            TaskList.markTaskAt(n);
            response = TaskList.getTaskList();

        } else if (checkSpecificCommand(input, "unmark")) {
            String msg = getMessageOnly(input, "unmark");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("unmark");
            }
            int n = Integer.parseInt(msg);
            assert n > 0 : "Task number must be positive";
            
            TaskList.unmarkTaskAt(n);
            response = TaskList.getTaskList();

        } else if (checkSpecificCommand(input, "delete")) {
            String msg = getMessageOnly(input, "delete");
            if (msg.isEmpty()) {
                throw new NoCommandArgumentException("delete");
            }
            int n = Integer.parseInt(msg);
            assert n > 0 : "Task number must be positive";
            
            Task toDelete = TaskList.deleteTaskAt(n);
            if (toDelete == null) {
                response = "YOUR LIST AIN'T THAT LONG BUDDY";
            } else {
                response = String.format("ok ITS GONE:\n Deleted: %s\n", toDelete);
            }
            
        } else if (checkSpecificCommand(input, "todo")) {
            String msg = getMessageOnly(input, "todo");

            Task task = new TodoTask(msg);
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            TaskList.addTask(task);
            response = String.format("OK THEN THERE!!! Added:\n %s", task);

        } else if (checkSpecificCommand(input, "deadline")) {
            String msg = getMessageOnly(input, "deadline").split("/by")[0];

            Task task = new DeadlineTask(msg, getDeadlineTime(input));
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            TaskList.addTask(task);
            response = String.format("YOU BETTER DO IT IN TIME!!!!!!! Added:\n %s", task);

        } else if (checkSpecificCommand(input, "event")) {
            String msg = getMessageOnly(input, "event").split("/from")[0];

            Task task = new EventTask(msg, getFromTime(input), getToTime(input));
            TaskList.addTask(task);
            if (msg.isEmpty()) {
                throw new EmptyTaskException(task);
            }
            response = String.format("BE THERE OR ELSE!!!!! Added:\n %s", task);

        } else {
            throw new NotACommandException();
        }
        TaskList.updateListToSave();
        return response;
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
