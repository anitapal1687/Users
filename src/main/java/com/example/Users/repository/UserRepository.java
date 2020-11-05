package com.example.Users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Users.dto.UsersDTO;
import com.example.Users.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>  {

	
	 @Query(value="SELECT id, loginId, empName, salary, startDate FROM Users t where t.salary > :minSalary", nativeQuery=true) 
	    List<Users> findTitleByMinSalary(@Param("minSalary") Optional<Double> salary);
	 
	 
		
	 @Query(value="SELECT id, loginId, empName, salary, startDate FROM Users t where t.salary > :minSalary and t.salary < :maxSalary  ", nativeQuery=true) 
	    List<Users> findTitleByMaxSalary(@Param("minSalary") Optional<Double> minsalary, @Param("maxSalary") Optional<Double> maxsalary);


	 @Query(value="SELECT id, loginId, empName, salary, startDate FROM Users t where id=:id", nativeQuery=true) 
	   Users findById(@Param("id") String id);
	 
	 @Query(value="SELECT id, loginId, empName, salary, startDate FROM Users t where loginId=:loginId or id=:id", nativeQuery=true) 
	   Users findByLoginIdOrId(@Param("loginId") String loginId, @Param("id") String id);
	 
	 @Query(value="SELECT id, loginId, empName, salary, startDate FROM Users t where loginId=:loginId", nativeQuery=true) 
	   Users findByLoginId(@Param("loginId") String loginId);
	 
	 
	 
}
