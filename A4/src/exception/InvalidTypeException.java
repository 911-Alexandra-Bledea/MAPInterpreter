package exception;

import java.lang.reflect.InaccessibleObjectException;

public class InvalidTypeException extends Exception{
    public InvalidTypeException(){super("Invalid type!");}

    public InvalidTypeException(String message){super(message);}
}
