package org.repository.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Entity {
	/**
	 * table name
	 * @return
	 */
	public String Table();
	
	/**
	 * Database name
	 * @return
	 */
	public String Catalog() default "default";

}
