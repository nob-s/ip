package main;

import tasks.TaskList;
import ui.Ui;

public class LittleBabyMan {
    /**
     * Main file
     * @param args
     */
    public static void main(String[] args) {
        TaskList.restoreListFromSave();
        Ui.beginChat();
    }
}
