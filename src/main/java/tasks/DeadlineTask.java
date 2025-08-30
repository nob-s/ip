package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private LocalDateTime deadline;
    
    public DeadlineTask(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch(DateTimeParseException e) {
            this.deadline = null;
        }
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
    
    @Override
    public String toString() {
        String m = getMark() ? "X" : " ";
        return String.format("[%s][%s] %s (By: %s)", 
                "DDLN", m, getDescription(), 
                getDateTimeAsString(deadline));
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s|||%s", 
                getTaskType(), getMark(), getDescription(), 
                getSaveDateTimeAsString(deadline));
    }
}
