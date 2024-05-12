package reviewservice.reviewservice.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String message){
        super(message);
    }

}
