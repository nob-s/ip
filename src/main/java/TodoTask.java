public class TodoTask extends Task {
    public TodoTask(String task) {
        super(task);
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        String m = getMark() ? "X" : " ";
        return String.format("[%s][%s] %s", "TODO", m, getTask());
    }
}
