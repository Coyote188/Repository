package org.repository.adapter;

import org.repository.adapter.fieldmodel.EntityModel;

public abstract class DataBaseAdapter {
	public abstract <T> String persist(EntityModel entity);
	public abstract <T> String remove(EntityModel entity);
	public abstract <T> String update(EntityModel entity);
	public abstract <T> String query(EntityModel entity);
	
	public final static String DATABASE_TYPE_ORACLE = "oracle";
	
	public static DataBaseAdapter getInstance(){
		// TODO to read the config file
		return new OracleAdapter();
	}
}
