package com.aniket.academy.student.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username){
        super("user not found give user name:"+username);
    }

}
