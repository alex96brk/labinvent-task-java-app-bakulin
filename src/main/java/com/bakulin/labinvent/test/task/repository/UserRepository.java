package com.bakulin.labinvent.test.task.repository;

import com.bakulin.labinvent.test.task.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByUserName(String userName);
    public User findUserByEmail(String email);

}
