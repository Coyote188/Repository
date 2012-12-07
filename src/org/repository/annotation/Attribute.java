package org.repository.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Attribute {
	/**
	 * Column
	 */
	public String name();
	/**
	 * is this column unique
	 */
	public boolean unique() default false;
	/**
	 * is this column nallable
	 */
	public boolean nullable() default true;
}
