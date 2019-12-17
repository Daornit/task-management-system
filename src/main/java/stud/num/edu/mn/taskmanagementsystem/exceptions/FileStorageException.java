package stud.num.edu.mn.taskmanagementsystem.exceptions;
/*
@author Bat-orgil
@date 2019-12-01
*/
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}