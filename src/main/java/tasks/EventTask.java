package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventTask extends Task{
    private LocalDateTime from;

    private LocalDateTime to;

    public EventTask(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, formatter);
        } catch (DateTimeParseException e) {
            this.from = null;
        }
        try {
            this.to = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            this.to = null;
        }
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        String m = getMarked() ? "X" : " ";
        return String.format("[%s][%s] %s (From: %s, To: %s) ", 
                "EVNT", m, getDescription(),
                getDateTimeAsString(from), getDateTimeAsString(to));
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s|||%s|||%s", 
                getTaskType(), getMarked(), getDescription(), 
                getSaveDateTimeAsString(from), getSaveDateTimeAsString(to));
    }
}
