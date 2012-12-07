package org.repository.adapter.fieldmodel;

import java.io.Serializable;

public class AttributeModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6458128332065697868L;
	
	private String attribute;
	private String columnName;
	private boolean isId;
	private boolean isUnique;
	private boolean isNullable;
	private String genericGenerator;
	private String strategy;
	private Object value;
	
	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the isId
	 */
	public boolean isId() {
		return isId;
	}
	/**
	 * @param isId the isId to set
	 */
	public void setId(boolean isId) {
		this.isId = isId;
	}
	/**
	 * @return the isUnique
	 */
	public boolean isUnique() {
		return isUnique;
	}
	/**
	 * @param isUnique the isUnique to set
	 */
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	/**
	 * @return the isNullable
	 */
	public boolean isNullable() {
		return isNullable;
	}
	/**
	 * @param isNullable the isNullable to set
	 */
	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}
	/**
	 * @return the genericGenerator
	 */
	public String getGenericGenerator() {
		return genericGenerator;
	}
	/**
	 * @param genericGenerator the genericGenerator to set
	 */
	public void setGenericGenerator(String genericGenerator) {
		this.genericGenerator = genericGenerator;
	}
	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}
	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
