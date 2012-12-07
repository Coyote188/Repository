package org.test;

import org.junit.Test;
import org.repository.TestBean;
import org.repository.analytic.ObjectAnalytic;
import org.repository.config.RepositoryConfigration;

public class RepositoryTest {

	@Test
	public void test() {
		ObjectAnalytic object = new ObjectAnalytic();
		TestBean bean = new TestBean();
		bean.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setUserArea("0871");
		bean.setUserNum("15398577080");
		
		object.analitic(bean);
	}
	
	@Test
	public void xmlTest(){
		new RepositoryConfigration();
	}

}
