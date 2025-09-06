package main;

import tasks.TaskList;
import ui.Ui;

public class LittleBabyMan {
    /**
     * gui.Main file
     * @param args
     */
    public static void main(String[] args) {
        TaskList.restoreListFromSave();
        Ui.beginChat();
    }
    public String getResponse(String userInput) {
        
    }
}
