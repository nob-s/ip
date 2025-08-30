public class EventTask extends Task{
    private final String from;

    private final String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        String m = getMark() ? "X" : " ";
        return String.format("[%s][%s] %s (From: %s, To: %s) ", "EVNT", m, getDescription(), from, to);
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s|||%s|||%s", getTaskType(), getMark(), getDescription(), getFrom(), getTo());
    }
}
