package com.runnew.common.mybatis;

import com.runnew.common.entity.BaseEntity;


@SuppressWarnings("unused")
public class BaseProvider<T extends BaseEntity> {
	private String tableName;
	private Class<?> modelClass;
	public static ThreadLocal<Class<?>> threadModelClass = new ThreadLocal<Class<?>>();
	private static final String OPERATOR_EQUAL = " = ";
	private static final String OPERATOR_LIKE = " like ";

	public String getById() {
		return null;
	}
	
}
