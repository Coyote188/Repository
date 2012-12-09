package org.repository.config;

public class SessionConfiguration {
	
	private boolean dhcp;
	private String url;
	private String username;
	private String password;
	private String driveClass;
	private int initActive;
	private int maxActive;
	/**
	 * @return the dhcp
	 */
	public boolean isDhcp() {
		return dhcp;
	}
	/**
	 * @param dhcp the dhcp to set
	 */
	public void setDhcp(boolean dhcp) {
		this.dhcp = dhcp;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the driveClass
	 */
	public String getDriveClass() {
		return driveClass;
	}
	/**
	 * @param driveClass the driveClass to set
	 */
	public void setDriveClass(String driveClass) {
		this.driveClass = driveClass;
	}
	/**
	 * @return the initActive
	 */
	public int getInitActive() {
		return initActive;
	}
	/**
	 * @param initActive the initActive to set
	 */
	public void setInitActive(int initActive) {
		this.initActive = initActive;
	}
	/**
	 * @return the maxActive
	 */
	public int getMaxActive() {
		return maxActive;
	}
	/**
	 * @param maxActive the maxActive to set
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	
}
