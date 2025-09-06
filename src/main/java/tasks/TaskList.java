package tasks;

import java.util.ArrayList;

import storage.Storage;

public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * @return String of formatted list
     */
    public static String getTaskList() {
        int len = taskList.size();
        StringBuilder tl = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Task t = taskList.get(i);
            tl.append(getTaskMessage(i + 1, t));
        }
        return tl.toString();
    }

    /**
     * @param find ArrayList of listNumbers to be returned
     * @return String of formatted list which only contain tasks with contain substring "find
     */
    public static String getSelectiveTaskList(String find) {
        int idx = 1;
        StringBuilder tl = new StringBuilder();
        for (Task t : taskList) {
            if (t.getDescription().contains(find)) {
                tl.append(getTaskMessage(idx++, t));
            }
        }
        if (idx == 1) {
            return String.format("I DIDN'T FIND ANYTHING CONTAINING \"%s\"", find);
        }
        return tl.toString();
    }
    
    /**
     * Gets task with respect to numbered list position
     * @param listNumber Number as reflected on the list upon "list" command
     * @return Task object at the list position
     */
    public static Task getTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return null;
        }
        return taskList.get(listNumber - 1);
    }

    /**
     * Set isMarked of Task object at listNumber to true
     * @param listNumber Task object at the list position
     */
    public static void markTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return;
        }
        taskList.get(listNumber - 1).setMarked(true);
    }

    /**
     * Set isMarked of Task object at listNumber to false
     * @param listNumber Task object at the list position
     */
    public static void unmarkTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return;
        }
        taskList.get(listNumber - 1).setMarked(false);
    }

    /**
     * Delete the Task object at listNumber
     * @param listNumber Task object at the list position
     * @return
     */
    public static Task deleteTaskAt(int listNumber) {
        if (listNumber > taskList.size()) {
            return null;
        }
        Task toDelete = taskList.get(listNumber - 1);
        taskList.remove(listNumber - 1);
        return toDelete;
    }

    /**
     * Adds Task object to the end of the list
     * @param task Task object to add
     */
    public static void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Writes new list to savedTasks.txt
     */
    public static void updateListToSave() {
        Storage.saveTaskList(taskList);
    }

    /**
     * Restores list from savedTasks.txt
     */
    public static void restoreListFromSave() {
        Storage.restoreTaskList(taskList);
    }
    
    private static String getTaskMessage(int number, Task task) {
        return String.format("%d. %s\n", number, task);
    }
}
