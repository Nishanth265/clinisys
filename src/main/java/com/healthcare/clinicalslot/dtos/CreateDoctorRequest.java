package com.healthcare.clinicalslot.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateDoctorRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 255)
        String name,

        @NotBlank(message = "Email is required")
        @Email
        @Size(max = 255)
        String email,

        @NotBlank(message = "specialization is required")
        @Size(max = 255)
        String specialization


) {

        public CreateDoctorRequest {
                name = name == null ? null : name.strip();
                email = email == null ? null : email.strip();
                specialization = specialization == null ? null : specialization.strip();
        }
}
