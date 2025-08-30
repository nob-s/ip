public class DeadlineTask extends Task{
    private final String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
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
        return String.format("[%s][%s] %s (By: %s)", "DDLN", m, getDescription(), deadline);
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s|||%s", getTaskType(), getMark(), getDescription(), getDeadline());
    }
}
