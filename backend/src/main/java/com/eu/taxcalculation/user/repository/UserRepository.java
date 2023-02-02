package com.eu.taxcalculation.user.repository;

import com.eu.taxcalculation.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTin(long tin);

    public User findByUuid(String uuid);

    public User findByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
}
