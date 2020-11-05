package com.example.Users.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Users.dto.UsersDTO;
import com.example.Users.entity.Users;
import com.example.Users.repository.UserRepository;

@Service
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	UserRepository ur;

	public List<Users> getAllUsers(Optional<Double> minSalary, Optional<Double> maxSalary,
			Optional<String> order, Optional<Integer> limit) {
		List<Users> userList= null;
		if(minSalary.isPresent() && !maxSalary.isPresent()){
			 userList=ur.findTitleByMinSalary(minSalary);
		} else if(maxSalary.isPresent()) {
			userList=ur.findTitleByMaxSalary(minSalary, maxSalary);
			
		}else {
			 userList= ur.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id"))).toList();
		}
		
		
		return userList;
		
	}
	
	
	public Users getById(String id){
		return ur.findById(id);
	}
	
	public Users findByLoginIdOrId(String loginId, String id){
		return ur.findByLoginIdOrId(loginId, id);
	}
	
	
	public boolean existsByLoginId(String loginId){
		if(ur.findByLoginId(loginId)!=null){
			return true;
		} else{
			return false;
		}
	}
	
	
	public void save(List<Users> usersList){
		
		usersList.stream().forEach(s->ur.save(s));
	}
	
    public void create(Users users){
		
		ur.save(users);
	}
    
    @Transactional
    public int updateById(Users user){
    
         Query query = entityManager.createNativeQuery("UPDATE Users SET loginId = :loginId "
                 + "WHERE id = :id");
         query.setParameter("id", user.getId());
         query.setParameter("loginId", user.getLoginId());
         int rowsUpdated = query.executeUpdate();
         
         return rowsUpdated;
	}
    
    public void deleteById(Users users){
		 ur.delete(users);
	}
	
	
}
