public class EventTask extends Task{
    private final String from;

    private final String to;

    public EventTask(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
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
        return String.format("[%s][%s] %s (From: %s, To: %s) ", "EVNT", m, getTask(), from, to);
    }
}
