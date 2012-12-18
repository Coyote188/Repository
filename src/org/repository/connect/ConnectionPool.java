package org.repository.connect;

import java.sql.*;
import java.util.*;
import java.io.*;

import org.repository.config.RepositoryConfigration;
import org.repository.config.SessionConfiguration;

public class ConnectionPool {
	private static LinkedList<ConnectionWrapper> m_notUsedConnection = new LinkedList<ConnectionWrapper>();
	private static HashSet<ConnectionWrapper> m_usedUsedConnection = new HashSet<ConnectionWrapper>();
	private static String m_url = "";
	private static String m_user = "";
	private static String m_password = "";
	private static String m_driverclass = "";
	static final boolean DEBUG = true;
	static private long m_lastClearClosedConnection = System.currentTimeMillis();
	public static long CHECK_CLOSED_CONNECTION_TIME = 4 * 60 * 60 * 1000; // 4 hours

	static {
		initDriver();
	}

	private ConnectionPool() {
	}

	private static void initDriver() {
		
		List<SessionConfiguration> tmp = RepositoryConfigration.getConfigurations();
		SessionConfiguration configuration = tmp.get(0);
		ConnectionPool.setPassword(configuration.getPassword());
		ConnectionPool.setUrl(configuration.getUrl());
		ConnectionPool.setUser(configuration.getUsername());
		
		
		Driver driver = null;
		// load mysql driver
		try {
			driver = (Driver) Class.forName(configuration.getDriveClass()).newInstance();
			installDriver(driver);
		} catch (Exception e) {
		}

		// load postgresql driver
		try {
			driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
			installDriver(driver);
		} catch (Exception e) {
		}
	}

	public static void installDriver(Driver driver) {
		try {
			DriverManager.registerDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		clearClosedConnection();
		while (m_notUsedConnection.size() > 0) {
			try {
				ConnectionWrapper wrapper = (ConnectionWrapper) m_notUsedConnection.removeFirst();
				if (wrapper.connection.isClosed()) {
					continue;
				}
				m_usedUsedConnection.add(wrapper);
				if (DEBUG) {
					wrapper.debugInfo = new Throwable("Connection initial statement");
				}
				return wrapper.connection;
			} catch (Exception e) {
			}
		}
		int newCount = getIncreasingConnectionCount();
		LinkedList<ConnectionWrapper> list = new LinkedList<ConnectionWrapper>();
		ConnectionWrapper wrapper = null;
		for (int i = 0; i < newCount; i++) {
			wrapper = getNewConnection();
			if (wrapper != null) {
				list.add(wrapper);
			}
		}
		if (list.size() == 0) {
			return null;
		}
		wrapper = (ConnectionWrapper) list.removeFirst();
		m_usedUsedConnection.add(wrapper);

		m_notUsedConnection.addAll(list);
		list.clear();

		return wrapper.connection;
	}

	private static ConnectionWrapper getNewConnection() {
		try {
			Connection con = DriverManager.getConnection(m_url, m_user,
					m_password);
			ConnectionWrapper wrapper = new ConnectionWrapper(con);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static synchronized void pushConnectionBackToPool(ConnectionWrapper con) {
		boolean exist = m_usedUsedConnection.remove(con);
		if (exist) {
			m_notUsedConnection.addLast(con);
		}
	}

	public static int close() {
		int count = 0;

		Iterator<ConnectionWrapper> iterator = m_notUsedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				((ConnectionWrapper) iterator.next()).close();
				count++;
			} catch (Exception e) {
			}
		}
		m_notUsedConnection.clear();

		iterator = m_usedUsedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				ConnectionWrapper wrapper = (ConnectionWrapper) iterator.next();
				wrapper.close();
				if (DEBUG) {
					wrapper.debugInfo.printStackTrace();
				}
				count++;
			} catch (Exception e) {
			}
		}
		m_usedUsedConnection.clear();

		return count;
	}

	private static void clearClosedConnection() {
		long time = System.currentTimeMillis();
		// sometimes user change system time,just return
		if (time < m_lastClearClosedConnection) {
			time = m_lastClearClosedConnection;
			return;
		}
		// no need check very often
		if (time - m_lastClearClosedConnection < CHECK_CLOSED_CONNECTION_TIME) {
			return;
		}
		m_lastClearClosedConnection = time;

		// begin check
		Iterator<ConnectionWrapper> iterator = m_notUsedConnection.iterator();
		while (iterator.hasNext()) {
			ConnectionWrapper wrapper = (ConnectionWrapper) iterator.next();
			try {
				if (wrapper.connection.isClosed()) {
					iterator.remove();
				}
			} catch (Exception e) {
				iterator.remove();
				if (DEBUG) {
					System.out.println("connection is closed, this connection initial StackTrace");
					wrapper.debugInfo.printStackTrace();
				}
			}
		}

		// make connection pool size smaller if too big
		int decrease = getDecreasingConnectionCount();
		if (m_notUsedConnection.size() < decrease) {
			return;
		}

		while (decrease-- > 0) {
			ConnectionWrapper wrapper = (ConnectionWrapper) m_notUsedConnection
					.removeFirst();
			try {
				wrapper.connection.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * get increasing connection count, not just add 1 connection
	 * 
	 * @return count
	 */
	public static int getIncreasingConnectionCount() {
		int count = 1;
		int current = getConnectionCount();
		count = current / 4;
		if (count < 1) {
			count = 1;
		}
		return count;
	}

	/**
	 * get decreasing connection count, not just remove 1 connection
	 * 
	 * @return count
	 */
	public static int getDecreasingConnectionCount() {
		int current = getConnectionCount();
		if (current < 10) {
			return 0;
		}
		return current / 3;
	}

	public synchronized static void printDebugMsg() {
		printDebugMsg(System.out);
	}

	public synchronized static void printDebugMsg(PrintStream out) {
		if (!DEBUG) {
			return;
		}
		StringBuffer msg = new StringBuffer();
		msg.append("debug message in " + ConnectionPool.class.getName());
		msg.append("\r\n");
		msg.append("total count is connection pool: " + getConnectionCount());
		msg.append("\r\n");
		msg.append("not used connection count: " + getNotUsedConnectionCount());
		msg.append("\r\n");
		msg.append("used connection, count: " + getUsedConnectionCount());
		out.println(msg);
		Iterator<ConnectionWrapper> iterator = m_usedUsedConnection.iterator();
		while (iterator.hasNext()) {
			ConnectionWrapper wrapper = (ConnectionWrapper) iterator.next();
			wrapper.debugInfo.printStackTrace(out);
		}
		out.println();
	}

	public static synchronized int getNotUsedConnectionCount() {
		return m_notUsedConnection.size();
	}

	public static synchronized int getUsedConnectionCount() {
		return m_usedUsedConnection.size();
	}

	public static synchronized int getConnectionCount() {
		return m_notUsedConnection.size() + m_usedUsedConnection.size();
	}

	public static String getUrl() {
		return m_url;
	}

	public static void setUrl(String url) {
		if (url == null) {
			return;
		}
		m_url = url.trim();
	}

	public static String getUser() {
		return m_user;
	}

	public static void setUser(String user) {
		if (user == null) {
			return;
		}
		m_user = user.trim();
	}

	public static String getPassword() {
		return m_password;
	}

	public static void setPassword(String password) {
		if (password == null) {
			return;
		}
		m_password = password.trim();
	}

	/**
	 * @return the m_driverclass
	 */
	public static String getM_driverclass() {
		return m_driverclass;
	}

	/**
	 * @param m_driverclass the m_driverclass to set
	 */
	public static void setM_driverclass(String m_driverclass) {
		ConnectionPool.m_driverclass = m_driverclass;
	}

}

