package exceptions;

public class NoCommandArgumentException extends UserInputException {
    private String message;

    /**
     * Thrown when there are missing arguments to the command
     * @param message The String version of command in question
     */
    public NoCommandArgumentException(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return String.format("WHAT DO YOU WANNA DO WITH THIS COMMAND: '%s'!! GRRAAAHH", this.message);
    }
}
