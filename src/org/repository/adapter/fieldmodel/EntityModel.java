package org.repository.adapter.fieldmodel;

import java.io.Serializable;
import java.util.List;

public class EntityModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2833774909282140217L;

	private String table;
	
	private String catalog;
	
	private List<AttributeModel> attributes;
	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * @return the catalog
	 */
	public String getCatalog() {
		return catalog;
	}
	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	/**
	 * @return the attributes
	 */
	public List<AttributeModel> getAttributes() {
		return attributes;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<AttributeModel> attributes) {
		this.attributes = attributes;
	}
}
