import tasks.TaskList;
import ui.Ui;

public class LittleBabyMan {
    
    public static void main(String[] args) {
        TaskList.restoreListFromSave();
        
        Ui.beginChat();
    }
}
