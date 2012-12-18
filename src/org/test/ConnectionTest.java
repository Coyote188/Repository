package org.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.repository.config.RepositoryConfigration;
import org.repository.config.SessionConfiguration;
import org.repository.connect.ConnectionPool;

public class ConnectionTest {

	@Test
	public void test() {
		
		Connection con = ConnectionPool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from t_n_user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String str = rs.getString(2);
				System.out.println(str);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e1) {
			fail(e1.getMessage());
		}
		
		// do something with con ...

		try {
			con.close();
		} catch (Exception e) {
			fail("Exception：" + e.getMessage());
		}


		con = ConnectionPool.getConnection();
		ConnectionPool.printDebugMsg();

	}
	
	@Test
	public void integer(){
		System.out.println(Integer.MAX_VALUE);
	}

}
