package com.faac.soa.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.faac.soa.dao.UserDAO;
import com.faac.soa.model.User;

@ComponentScan(basePackages = "com.faac.soa")
public class AuthUserService implements UserDetailsService {

	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.get(username);
		if (user == null) {
			return null;
		}
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String password = user.getPassword();
		return new org.springframework.security.core.userdetails.User(username, password, auth);
	}

}
