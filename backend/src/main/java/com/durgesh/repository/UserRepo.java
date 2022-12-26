package com.durgesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.durgesh.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	@Query(value = "select email from tbl_user s where s.email=:email",nativeQuery = true)
	String findByEmail(String email);
}
