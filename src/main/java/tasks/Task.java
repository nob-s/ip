package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    private boolean mark;

    private final String description;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * 
     * @return The prefix of the type of Task, e.g. for EventTask, returns "Event"
     */
    public abstract String getTaskType();

    /**
     * 
     * @return Task formatted for saving and easy parsing
     */
    public abstract String getSaveString();
    
    public Task(String description) {
        this.description = description;
        this.mark = false;
    }
    
    /**
     *
     * @param time The LocalDateTime to format
     * @return Formatted to be readable
     */
    public static String getDateTimeAsString(LocalDateTime time) {
        if (time == null) {
            return "NIL";
        } else {
            int h = time.getHour();
            return String.format("%02d/%02d/%04d %s:%02d%s",
                    time.getDayOfMonth(), time.getMonthValue(), time.getYear(),
                    h == 0 ? 12 : h > 12 ? h - 12 : h, time.getMinute(), h > 12 ? "pm" : "am");
        }
    }

    /**
     * 
     * @param time The LocalDateTime to format
     * @return Formatted for all Dates and Times to be of the same length
     * e.g. 05-03-2025 0800 instead of 5-3-2025 800
     */
    public static String getSaveDateTimeAsString(LocalDateTime time) {
        return time == null
                ? "NIL"
                : String.format("%02d-%02d-%04d %02d%02d",
                time.getDayOfMonth(), time.getMonthValue(), time.getYear(),
                time.getHour(), time.getMinute());
    }
    
    public String getDescription() {
        return this.description;
    }

    public boolean getMark() {
        return this.mark;
    }

    /**
     * Set this.mark as true
     */
    public void mark() {
        this.mark = true;
    }
    
    /**
     * Set this.mark as false
     */
    public void unmark() {
        this.mark = false;
    }
    

}
