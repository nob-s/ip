package tasks;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        String m = getMarked() ? "X" : " ";
        return String.format("[%s][%s] %s", "TODO", m, getDescription());
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s", getTaskType(), getMarked(), getDescription());
    }
}
