package com.rene.exam.repository;

import com.rene.exam.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE USER_ID = ?1", nativeQuery = true)
    User findUserById(Long userId);

    @Query(value = "SELECT * FROM USER", nativeQuery = true)
    List<User> findAllUsers();

}
