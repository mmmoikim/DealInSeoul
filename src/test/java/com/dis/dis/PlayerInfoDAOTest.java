package com.dis.dis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dis.dis.dao.PlayerInfoDAO;
import com.dis.dis.vo.PlayerVO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:context/**/applicationContext*.xml")
public class PlayerInfoDAOTest {
	@Autowired
	PlayerInfoDAO dao;

	@Before
	public void setUp() throws Exception {
		System.out.println("비포");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("에프터");
	}

	@Test
	public void test() {
		dao.signup(new PlayerVO("xxx","xxx","xxx"));
	}

}
