package com.healthcare.clinicalslot.service;

import com.healthcare.clinicalslot.entity.User;
import com.healthcare.clinicalslot.enums.UserRole;
import com.healthcare.clinicalslot.exception.DuplicateEmailException;
import com.healthcare.clinicalslot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String PASSWORD_NOT_SET = "NOT_SET";

    private final UserRepository userRepository;

    @Transactional
    public User createDoctorUser(String name,String email){

        if(userRepository.existsByEmail(email))
            throw new DuplicateEmailException();

        User user = User.builder().name(name).email(email).role(UserRole.DOCTOR).password(PASSWORD_NOT_SET).build();
        return  userRepository.save(user);
    }



}
