package com.healthcare.clinicalslot.exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(){
        super("Email already existed");
    }
}
