package tasks;


import storage.Storage;

import java.util.ArrayList;

public class TaskList {
    /**
     * Prints formatted list for list command
     */
    public static void printTaskList() {
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            Task t = taskList.get(i);
            printTaskMessage(i + 1, t);
        }
    }

    /**
     * 
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
    
    private static void printTaskMessage(int number, Task task) {
        System.out.printf("%d. %s\n", number, task);
    }

    private static final ArrayList<Task> taskList = new ArrayList<>();
}
