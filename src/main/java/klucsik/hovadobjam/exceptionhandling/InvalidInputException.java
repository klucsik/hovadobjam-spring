package klucsik.hovadobjam.exceptionhandling;

public class InvalidInputException  extends RuntimeException{
    public InvalidInputException(){
        super();
    }

    public InvalidInputException(final String message,final Throwable cause){
        super(message, cause);
    }
    public InvalidInputException(final String message){
        super(message);
    }
    public InvalidInputException(final Throwable cause){
        super(cause);
    }
}
