package org.repository.analytic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.repository.adapter.fieldmodel.AttributeModel;
import org.repository.adapter.fieldmodel.EntityModel;
import org.repository.annotation.Attribute;
import org.repository.annotation.Entity;
import org.repository.annotation.GenericGenerator;
import org.repository.annotation.Identifier;

public class ObjectAnalytic {
	private static final Logger log = Logger.getLogger(ObjectAnalytic.class);
	
	/**
	 * 解析泛型对象,返回數據庫操作SQL
	 * @param t
	 * @return
	 */
	public static <T> EntityModel analitic(T t){
		/**
		 * 解析对角注解内容
		 */
		EntityModel entityModel = null;
		if(t.getClass().isAnnotationPresent(Entity.class)){
			Entity entity = t.getClass().getAnnotation(Entity.class);
			entityModel = new EntityModel();
			entityModel.setTable(entity.Table());
			entityModel.setCatalog(entity.Catalog());
			log.info("Table=" + entity.Table() + " Catalog=" + entity.Catalog());
		}
		/**
		 * 解析属性注解内容
		 */
		try {
			List<AttributeModel> attributes = entityModel.getAttributes();
			attributes = new ArrayList<AttributeModel>();
			for(Field f : t.getClass().getDeclaredFields()){
				AttributeModel attributeModel = new AttributeModel();
				if(f.isAnnotationPresent(Attribute.class)){
					attributeModel.setAttribute(f.getName());
					Attribute attribute = f.getAnnotation(Attribute.class);
					attributeModel.setNullable(attribute.nullable());
					attributeModel.setUnique(attribute.unique());
					attributeModel.setColumnName(attribute.name());
					attributeModel.setValue(getter(t, f.getName()));
				}
				if(f.isAnnotationPresent(Identifier.class)){
					Identifier identifier = f.getAnnotation(Identifier.class);
					attributeModel.setId(identifier.id());
				}else{
					attributeModel.setId(false);
				}
				if(f.isAnnotationPresent(GenericGenerator.class)){
					GenericGenerator generator = f.getAnnotation(GenericGenerator.class);
					attributeModel.setGenericGenerator(generator.name());
					attributeModel.setStrategy(generator.strategy());
				}
				attributes.add(attributeModel);
			}
		} catch (Exception e) {
			log.error("");
		}
		
		return entityModel;
	}
	
	//取SQL DataBaseAdapter.getInstance();
	public String loadPersistSql(){
		
		return null;
	}
	
	
	/**
	 * @param obj 操作的对象
	 * @param att 操作的属性
	 * 
	 */
	public static Object getter(Object obj, String att) {
		try {
			Method method = obj.getClass().getMethod("get" + initStr(att));
			return method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param obj 操作的对象
	 * @param att 操作的属性
	 * @param value 设置的值
	 * @param type 参数的属性
	 */
	public static void setter(Object obj, String att, Object value,
			Class<?> type) {
		try {
			Method method = obj.getClass().getMethod("set" + initStr(att), type);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String initStr(String old) {
		String str = old.substring(0, 1).toUpperCase() + old.substring(1);
		return str;
	}
}
