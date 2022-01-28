package com.intuit.craft.repositories;

import com.intuit.craft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String emailId);
    User findByEmail(String emailId);
}
