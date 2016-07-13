package com.budget.services;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.budget.repositories.UsersRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	private org.springframework.security.core.userdetails.User userdetails;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		com.budget.model.User user = getUserDetail(username);

		userdetails = new User(user.getUserName(), user.getPassword(), user.getEnabled(), accountNonExpired,
				credentialsNonExpired, accountNonLocked,
				convertList(user.getUserRoles(), s -> new SimpleGrantedAuthority(s.getUserRolePK().getRole().name())));

		return userdetails;
	}

	public <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
		return from.stream().map(func).collect(Collectors.toList());
	}

	private com.budget.model.User getUserDetail(String username) {
		return usersRepository.findByUserName(username);
	}
}
