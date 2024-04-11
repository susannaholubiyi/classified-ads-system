package africa.semicolon.exceptions;

public class ContactInfoNotFoundException extends RuntimeException{
    public ContactInfoNotFoundException(String message){
        super(message);
    }
}
