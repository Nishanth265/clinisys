package com.healthcare.clinicalslot.repository;

import com.healthcare.clinicalslot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

}
