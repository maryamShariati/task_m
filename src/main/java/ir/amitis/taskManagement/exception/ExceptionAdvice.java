package ir.amitis.taskManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String recordNotFound(RecordNotFoundException exception){
        return exception.getMessage();
    }
}
