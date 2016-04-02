package com.faac.soa.wrapper;

import java.security.Principal;

import com.faac.soa.ws.users.User;

public class SoaWrapper {
public static User parse(Principal p){
	User u = new User();
	u.setUsername(p.getName());
	return u;
}
}
