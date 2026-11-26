package com.healthcare.clinicalslot.repository;

import com.healthcare.clinicalslot.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @EntityGraph(attributePaths = "user")
    Page<Doctor> findBySpecialization(String specialization,Pageable pageable);

    @EntityGraph(attributePaths = "user")
    @Override
    Page<Doctor> findAll(Pageable pageable);

}
