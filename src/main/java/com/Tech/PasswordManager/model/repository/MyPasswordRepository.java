package com.Tech.PasswordManager.model.repository;
import com.Tech.PasswordManager.model.entity.MyPassword;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional

public interface MyPasswordRepository extends JpaRepository<MyPassword, Long> {
    List<MyPassword> findAllByUserId(long id);
    void deleteAllByUserId(long id);
}
