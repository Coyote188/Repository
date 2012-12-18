package org.repository.connect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

class ConnectionWrapper implements InvocationHandler {
	private final static String CLOSE_METHOD_NAME = "close";
	public Connection connection = null;
	private Connection m_originConnection = null;
	public long lastAccessTime = System.currentTimeMillis();
	Throwable debugInfo = new Throwable("Connection initial statement");

	ConnectionWrapper(Connection con) {
		this.connection = (Connection) Proxy.newProxyInstance(con.getClass().getClassLoader(), con.getClass().getInterfaces(), this);
		m_originConnection = con;
	}

	void close() throws SQLException {
		m_originConnection.close();
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		Object obj = null;
		if (CLOSE_METHOD_NAME.equals(m.getName())) {
			ConnectionPool.pushConnectionBackToPool(this);
		} else {
			obj = m.invoke(m_originConnection, args);
		}
		lastAccessTime = System.currentTimeMillis();
		return obj;
	}
}
