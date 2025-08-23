public class DeadlineTask extends Task{
    private final String deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String m = getMark() ? "X" : " ";
        return String.format("[%s][%s] %s (By: %s)", "DDLN", m, getTask(), deadline);
    }
}
