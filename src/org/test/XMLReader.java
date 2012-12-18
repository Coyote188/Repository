package org.test;

import java.util.List;

import org.junit.Test;
import org.repository.config.RepositoryConfigration;
import org.repository.config.SessionConfiguration;

public class XMLReader {

	@Test
	public void xmlTest(){
		List<SessionConfiguration> tmp = RepositoryConfigration.getConfigurations();
		RepositoryConfigration.getConfigurations();
		for(SessionConfiguration s : tmp){
			System.out.println(s.toString());
		}
	}

}
