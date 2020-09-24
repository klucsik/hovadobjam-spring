package klucsik.hovadobjam.exceptionhandling;

public class invalidAuthTokenException  extends RuntimeException{
    public invalidAuthTokenException(){
        super();
    }

    public invalidAuthTokenException(final String message,final Throwable cause){
        super(message, cause);
    }
    public invalidAuthTokenException(final String message){
        super(message);
    }
    public invalidAuthTokenException(final Throwable cause){
        super(cause);
    }
}
