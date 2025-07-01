package org.example.bakcendspring.repositories;

import org.example.bakcendspring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE " +
            "(:userName IS NULL OR u.userName = :userName) AND " +
            "(:fullName IS NULL OR u.fullName = :fullName)")
    List<UserEntity> findByFilters(@Param("userName") String userName,
                                   @Param("fullName") String fullName);
}