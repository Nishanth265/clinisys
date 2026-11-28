package com.healthcare.clinicalslot.service;

import com.healthcare.clinicalslot.dtos.CreateDoctorRequest;
import com.healthcare.clinicalslot.dtos.DoctorResponse;
import com.healthcare.clinicalslot.entity.Doctor;
import com.healthcare.clinicalslot.entity.User;
import com.healthcare.clinicalslot.exception.DoctorNotFoundException;
import com.healthcare.clinicalslot.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;

    @Transactional
    public DoctorResponse create(CreateDoctorRequest request){

        User user = userService.createDoctorUser(request.name(),request.email());

        Doctor doctor = Doctor.builder().user(user).specialization(request.specialization()).build();

        doctorRepository.save(doctor);

        return response(doctor);
    }

    @Transactional(readOnly = true)
    public DoctorResponse getById(Long id){

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->  new DoctorNotFoundException(id));

        return response(doctor);

    }

    @Transactional(readOnly = true)
    public Page<DoctorResponse> list(String specialiazation, Pageable pageable){
        Page<Doctor> doctors;

        if(specialiazation == null || specialiazation.isBlank()) {
             doctors = doctorRepository.findAll(pageable);
        }
        else {
            doctors = doctorRepository.findBySpecialization(specialiazation.strip(), pageable);
        }

        return doctors.map(this::response);
    }


    public DoctorResponse response(Doctor doctor){
        return new DoctorResponse(doctor.getId(),doctor.getUser().getName(),doctor.getSpecialization());
    }
}
