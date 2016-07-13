package com.budget.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.budget.model.User;
import com.budget.model.UserRoles;
import com.budget.model.UserRolesPK;
import com.budget.repositories.UsersRepository;
import com.budget.repositories.enums.UserRole;

@Service
public class UserService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	public boolean addNewUser(User user) {
		boolean ret = false;
		if (user.getPassword().equals(user.getRepeatPassword())) {
			user.setPassword(bCryptEncoder.encode(user.getPassword()));
			List<UserRoles> userRoles = new ArrayList<UserRoles>();
			UserRoles role = new UserRoles();
			UserRolesPK rolePK = new UserRolesPK();
			rolePK.setRole(UserRole.ROLE_USER);
			rolePK.setUserName(user.getUserName());
			role.setUserRolePK(rolePK);
			userRoles.add(role);
			user.setUserRoles(userRoles);
			user.setEnabled(true);

			usersRepository.save(user);
			ret = true;
		}
		return ret;
	}
}
