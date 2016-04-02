package com.faac.soa.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.faac.soa.config.ApplicationContextConfig;
import com.faac.soa.config.WebMvcConfiguration;
import com.faac.soa.dao.UserDAO;
import com.faac.soa.model.User;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@ComponentScan(basePackages = { "com.faac.soa" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = WebMvcConfiguration.class) })
public class TestDAO {
	 @Autowired
	   private ApplicationContext applicationContext;
//	@Autowired
    private UserDAO userDao;
    @Before
    public void setUp() {
    	userDao = (UserDAO) applicationContext.getBean("userDao");
    }
	@Test
	public void listUserEmptyFail() {
		assertEquals(userDao.list().size(), 0);
	}
	@Test
	public void listUserFillNotEmpty() {
		assertNotEquals(userDao.list().size(), 0);
	}
	private List<User> userList(int n_users){
		List<User> list = new ArrayList<User>();
		for(int i=0;i<n_users;i++){
			User u = new User();
			String s_user="utente"+i;
			u.setEmail(s_user+"@faac.com");
			u.setUsername(s_user);
			u.setPassword(s_user);
			list.add(u);
		}
		return list;
	}
	@Test
	public void addUsers() {
		for(User u: userList(20)){
		try{
			
				userDao.saveOrUpdate(u);
			
			
		}catch(Exception e){
			System.out.println("Error: "+e);
		}
	}
		assertTrue(true);
	}
}
