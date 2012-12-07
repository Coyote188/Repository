package org.repository.analytic;

import org.repository.adapter.DataBaseAdapter;
import org.repository.adapter.fieldmodel.EntityModel;

public class GenericTranslator {
	
	/**
	 * translate an Object to data
	 * @param t
	 * @return
	 */
	public <T> String objectToD(T t){
		ObjectAnalytic analytic = new ObjectAnalytic();
		EntityModel entityModel = analytic.analitic(t);
		DataBaseAdapter adapter = DataBaseAdapter.getInstance();
		return "";
	}
	
	public <T> T dataToObject(T t){
		
		return null;
	}
}
