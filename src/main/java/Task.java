import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    private boolean mark;

    private final String description;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    /*
    Returns the name of the type of Task
     */
    public abstract String getTaskType();

    /*
    Returns info about task used for saving
     */
    public abstract String getSaveString();
    
    public Task(String description) {
        this.description = description;
        this.mark = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getMark() {
        return this.mark;
    }

    public void mark() {
        this.mark = true;
    }

    public void unmark() {
        this.mark = false;
    }
    
    public String getDateTimeAsString(LocalDateTime time) {
        if (time == null) {
            return "NIL";
        } else {
            int h = time.getHour();
            return String.format("%s/%s/%s %s:%02d%s",
                    time.getDayOfMonth(), time.getMonthValue(), time.getYear(),
                    h > 12 ? h - 12 : h, time.getMinute(), h > 12 ? "pm" : "am");
        }
    }
    
    
    public String getSaveDateTimeAsString(LocalDateTime time) {
        return time == null 
            ? "NIL"
            : String.format("%02d-%02d-%04d %02d%02d",
                time.getDayOfMonth(), time.getMonthValue(), time.getYear(),
                time.getHour(), time.getMinute());
    }
}
