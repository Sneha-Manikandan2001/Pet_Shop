package com.dao;
 
import com.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
	@Query("SELECT r FROM Role r WHERE r.role_name = ?1")
    Role findByRoleName(String roleName);
}
 
 