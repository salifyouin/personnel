package ci.dgmp.personnel.email.service.exception;

public class AppException extends RuntimeException{
    private String message;
    public AppException(String message)
    {
        super(message);
        this.message = message;
    }
}
