package exceptions;

public class NotACommandException extends UserInputException {
    public NotACommandException() {}

    @Override
    public String toString() {
        return "THIS AIN'T A COMMAND RAAAARGHGHH";
    }
}
