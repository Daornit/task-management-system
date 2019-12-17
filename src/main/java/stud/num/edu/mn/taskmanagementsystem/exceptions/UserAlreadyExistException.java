package stud.num.edu.mn.taskmanagementsystem.exceptions;
/*
@author Bat-orgil
@date 2019-12-01
*/
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("Таны оруулсан Email дээр хэрэглэгч бүртгэлтэй байна.");
    }
}
