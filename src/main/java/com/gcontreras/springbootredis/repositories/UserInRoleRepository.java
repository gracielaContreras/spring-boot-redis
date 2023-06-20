package com.gcontreras.springbootredis.repositories;

import com.gcontreras.springbootredis.entities.User;
import com.gcontreras.springbootredis.entities.UserInRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {
    @Query("SELECT ur.user FROM UserInRole ur WHERE ur.role.id = ?1")
    List<User> findByRoleId(Integer roleId);
}
