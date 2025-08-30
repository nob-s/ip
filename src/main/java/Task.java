public abstract class Task {

    private boolean mark;

    private final String description;
    
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
}
