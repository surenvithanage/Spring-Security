package com.jwt.jwtauth.respository;
import com.jwt.jwtauth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Project jwt-auth
 * User : suren_v
 * Date : 12/2/2019
 * Time : 11:32 AM
 */
@Repository
public interface UserRepository extends  CrudRepository<User, Integer> {
    User findByUsername(String username);
}
