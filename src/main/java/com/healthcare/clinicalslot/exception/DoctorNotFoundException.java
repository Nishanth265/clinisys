package com.healthcare.clinicalslot.exception;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(Long id){
        super("Doctor is Not Present with "+id);
    }

}
