package com.budget.repositories;

import org.springframework.data.repository.CrudRepository;

import com.budget.model.User;

public interface UsersRepository extends CrudRepository<User, String> {
	public User findByUserName(String userName);

}
