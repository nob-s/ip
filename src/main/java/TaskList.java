import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void printTaskList() {
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            Task t = taskList.get(i);
            printTaskMessage(i + 1, t);
        }
    } 
    
    public static Task getTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return null;
        }
        return taskList.get(listNumber - 1);
    }
    
    public static void markTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return;
        }
        taskList.get(listNumber - 1).mark();
    }

    public static void unmarkTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return;
        }
        taskList.get(listNumber - 1).unmark();
    }
    
    public static Task deleteTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return null;
        }
        Task toDelete = taskList.get(listNumber - 1);
        taskList.remove(listNumber - 1);
        return toDelete;
    }
    
    public static void addTask(Task task) {
        taskList.add(task);
    }
    
    public static int size() {
        return taskList.size();
    }
    
    public static void updateListToSave() {
        Storage.saveTaskList(taskList);
    }
    
    public static void restoreListFromSave() {
        Storage.restoreTaskList(taskList);
    }
    
    private static void printTaskMessage(int number, Task task) {
        System.out.printf("%d. %s\n", number, task);
    }
}
