package tasks;

import java.util.ArrayList;

import org.apache.commons.text.similarity.LevenshteinDistance;
import storage.Storage;

public class TaskList {
    private static final double SIMILARITY_THRESHOLD = 0.85;
    private static final LevenshteinDistance ld = new LevenshteinDistance(2);

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
     * Returns a formatted list String with tasks that contain String find
     *
     * @param find String to match tasks to
     * @return String of formatted Task list
     */
    public static String getSelectiveTaskList(String find) {
        int idx = 1;
        StringBuilder tl = new StringBuilder();
        for (Task t : taskList) {
            if (t.getDescription().toLowerCase().contains(find.toLowerCase().trim())) {
                tl.append(getTaskMessage(idx++, t));
            }
        }
        if (idx == 1) {
            return String.format("I DIDN'T FIND ANYTHING CONTAINING \"%s\"", find);
        }
        return tl.toString();
    }

    /**
     * Returns a formatted list String with tasks that roughly match String find
     *
     * @param find String to match tasks to
     * @return String of formatted Task list
     */
    public static String getFlexibleSelectiveTaskList(String find) {
        int idx = 1;
        StringBuilder tl = new StringBuilder();
        for (Task t : taskList) {
            if (getStringSimilarity(t.getDescription(), find)) {
                tl.append(getTaskMessage(idx++, t));
            }
        }
        if (idx == 1) {
            return String.format("I DIDN'T FIND ANYTHING CONTAINING \"%s\"", find);
        }
        return tl.toString();
    }

    /**
     *
     * @param description Task description to match
     * @param find Search string to match
     * @return Whether description and keywords similarity are above SIMILARITY_THRESHOLD
     */
    public static boolean getStringSimilarity(String description, String find) {
        String[] keywords = find.toLowerCase().trim().split("\\s+");
        String desc = description.toLowerCase();
        int score;

        for (String key: keywords) {
            if (desc.contains(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets task with respect to numbered list position
     * 
     * @param listNumber Number as reflected on the list upon "list" command
     * @return Task object at the list position
     */
    public static Task getTaskAt(int listNumber) {
        assert listNumber > 0 : "listNumber must be positive";
        if (listNumber > taskList.size()) {
            return null;
        }
        return taskList.get(listNumber - 1);
    }

    /**
     * Set isMarked of Task object at listNumber to true
     * 
     * @param listNumber Task object at the list position
     */
    public static void markTaskAt(int listNumber) {
        assert listNumber > 0 : "listNumber must be positive";
        if (listNumber > taskList.size()) {
            return;
        }
        taskList.get(listNumber - 1).setMarked(true);
    }

    /**
     * Set isMarked of Task object at listNumber to false
     * 
     * @param listNumber Task object at the list position
     */
    public static void unmarkTaskAt(int listNumber) {
        assert listNumber > 0 : "listNumber must be positive";
        if (listNumber > taskList.size()) {
            return;
        }
        taskList.get(listNumber - 1).setMarked(false);
    }

    /**
     * Delete the Task object at listNumber
     * 
     * @param listNumber Task object at the list position
     * @return Task object to be deleted
     */
    public static Task deleteTaskAt(int listNumber) {
        assert listNumber > 0 : "listNumber must be positive";
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
