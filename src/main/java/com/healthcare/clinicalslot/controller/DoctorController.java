package com.healthcare.clinicalslot.controller;

import com.healthcare.clinicalslot.common.PageResponse;
import com.healthcare.clinicalslot.dtos.CreateDoctorRequest;
import com.healthcare.clinicalslot.dtos.DoctorResponse;
import com.healthcare.clinicalslot.service.DoctorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    @PostMapping
    public ResponseEntity<DoctorResponse> create(@Valid @RequestBody CreateDoctorRequest request) {
        return ResponseEntity.status(201).body(doctorService.create(request));
    }

    @GetMapping("/{id}")
    public  DoctorResponse getById(@PathVariable Long id){
        return doctorService.getById(id);
    }

    @GetMapping()
    public PageResponse<DoctorResponse> list(
            @RequestParam(required = false) String specialization,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
            ){
        Pageable pageable = PageRequest.of(page,Math.min(size,100), Sort.by("id"));
        Page<DoctorResponse> pageableDoctor = doctorService.list(specialization,pageable);
        return PageResponse.from(pageableDoctor);
    }

}
