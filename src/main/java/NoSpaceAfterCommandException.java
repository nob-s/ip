public class NoSpaceAfterCommandException extends UserInputException{
    private String message;

    public NoSpaceAfterCommandException(String command) {
        this.message = String.format("PUT A SPACE AFTER '%s' DANGIT!!!!", command);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
