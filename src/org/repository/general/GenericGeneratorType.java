package org.repository.general;

public class GenericGeneratorType {
	/**
	 * 为该属性产生UUID方式唯一标识
	 */
	public static final String UUID = "uuid";
	/**
	 * 为该属性产生一个序列做为唯一标识 默认递增
	 */
	public static final String SEQUENCE = "seq";
	/**
	 * 序列-递增
	 */
	public static final String SEQUENCE_STRATEGY_INCREMENTAL = "0";
	/**
	 * 序列-递减
	 */
	public static final String SEQUENCE_STRATEGY_DECREMENT = "1";
}
