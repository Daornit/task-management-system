package stud.num.edu.mn.taskmanagementsystem.security;
/*
@author Bat-orgil
@date 2019-12-01
*/
public final class SecurityConstants {
    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
