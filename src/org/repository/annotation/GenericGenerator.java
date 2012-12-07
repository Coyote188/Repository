package org.repository.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.repository.general.GenericGeneratorType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GenericGenerator {
	public String name() default GenericGeneratorType.SEQUENCE;
	public String strategy() default GenericGeneratorType.SEQUENCE_STRATEGY_INCREMENTAL;
}
