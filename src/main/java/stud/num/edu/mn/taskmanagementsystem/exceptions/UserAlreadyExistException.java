package stud.num.edu.mn.taskmanagementsystem.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("Таны оруулсан Email дээр хэрэглэгч бүртгэлтэй байна.");
    }
}
