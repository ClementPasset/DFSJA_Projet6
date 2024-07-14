package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find a user thanks to the email address passed as a parameter
     * 
     * @param email
     * @return User
     */
    User findByEmail(String email);
}
