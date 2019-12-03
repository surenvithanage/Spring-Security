package com.security.complete.repository;

import com.security.complete.mapping.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:45 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByName(String name);
}
