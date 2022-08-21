package ir.amitis.taskManagement.exception;

public class RecordNotFoundException extends Throwable {
    public RecordNotFoundException(Object obj) {
        super("Record not found (param :"+obj+")");
    }
    public RecordNotFoundException() {
        super("Record not found");
    }
}
