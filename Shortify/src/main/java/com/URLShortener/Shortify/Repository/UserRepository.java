package com.URLShortener.Shortify.Repository;

import com.URLShortener.Shortify.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByUsername(String username);
}
