public class Task {
    private boolean mark;

    private final String task;

    public Task(String task) {
        this.task = task;
        this.mark = false;
    }

    public String getTask() {
        return this.task;
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
