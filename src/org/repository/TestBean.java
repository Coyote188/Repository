package org.repository;

import org.repository.annotation.Attribute;
import org.repository.annotation.Entity;
import org.repository.annotation.GenericGenerator;
import org.repository.annotation.Identifier;
import org.repository.general.GenericGeneratorType;

@Entity(Table = "T_BLACK_LIST")
public class TestBean {
	@Identifier
	@GenericGenerator(name = GenericGeneratorType.UUID)
	@Attribute(name="BLACK_ID", unique=true, nullable=false)
	private String id;
	
	@Attribute(name = "USER_AREA")
	private String userArea;
	
	@Attribute(name = "USER_NUM")
	private String userNum;
	
	@Attribute(name = "CREATE_TIME")
	private java.sql.Timestamp createDate;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the userArea
	 */
	public String getUserArea() {
		return userArea;
	}
	/**
	 * @param userArea the userArea to set
	 */
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	/**
	 * @return the userNum
	 */
	public String getUserNum() {
		return userNum;
	}
	/**
	 * @param userNum the userNum to set
	 */
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	/**
	 * @return the createDate
	 */
	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}
	
}
