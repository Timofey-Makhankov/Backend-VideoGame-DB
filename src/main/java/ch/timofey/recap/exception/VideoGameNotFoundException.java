package ch.timofey.recap.exception;

public class VideoGameNotFoundException extends RuntimeException{
    public VideoGameNotFoundException(String message){
        super(message);
    }
}
