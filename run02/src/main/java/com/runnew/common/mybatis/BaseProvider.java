package com.runnew.common.mybatis;

import java.util.Map;

import javax.persistence.Table;

import org.apache.ibatis.jdbc.SQL;


import com.runnew.common.entity.BaseEntity;


@SuppressWarnings("unused")
public class BaseProvider<T extends BaseEntity> {

	private String tableName;
	private Class<?> modelClass;
	private static ThreadLocal<Class<?>> threadModelClass = new ThreadLocal<Class<?>>();
	private static final String OPERATOR_EQUAL = " = ";
	private static final String OPERATOR_LIKE = " like ";

	private void initFromThreadLocal() {
		modelClass = BaseProvider.threadModelClass.get();
		System.out.println(modelClass.getAnnotation(Table.class));
		tableName = modelClass.getAnnotation(Table.class).name();
		BaseProvider.threadModelClass.remove();
	}

	public static void setModelClass(Class<?> modelClass) {
		BaseProvider.threadModelClass.set(modelClass);
	}

	/**
	 * 根据主键ID来获取实体
	 * @param id
	 * @return
	 */
	public String getBaseDaoById(String id) {
		initFromThreadLocal() ;
		SQL sql = SELECT_FROM().WHERE("ID = #{id}");
		System.out.println(sql);
		return sql.toString();

//	return "SELECT  r.*,r.CREATE_TIME as createTime,r.LAST_LOGIN_TIME as lastLoginTime   FROM R_USER r where r.id='"+id+"'";
	}
	
	private SQL SELECT_FROM() {
		final Map<String, Property> columns = ModelUtils.getProperties(modelClass, ColumnTarget.SELECT);
		return new SQL() {
			{
				for (Property property : columns.values()) {
					SELECT(property.getColumnName());
				}
				FROM(tableName);
			}
		};
	}
	
	
}
