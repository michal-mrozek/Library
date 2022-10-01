package library.exeption;

public class PublicationAlreadyExistException extends RuntimeException{
    public PublicationAlreadyExistException(String message) {
        super(message);
    }
}
