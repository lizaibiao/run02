package com.run.common.dao;

/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   类似hibernate的Dialect,但只精简出分页部分
* HISTORY

 */
public class Dialect {
    public boolean supportsLimit(){
    	return false;
    }

    public boolean supportsLimitOffset() {
    	return supportsLimit();
    }
    
    /**
     * 将sql变成分页sql语句,直接使用offset,limit的值作为占位符.</br>
     * 源代码为: getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit))
     * ADD orderName:排序字段  sort: 升降
     */
    public String getLimitString(String sql, int offset, int limit,String sortName,String sort ) {
    	return getLimitString(sql,offset,Integer.toString(offset),limit,Integer.toString(limit),sortName,sort);
    }
    
    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符(placeholder)替换.
     * <pre>
     * 如mysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
     * select * from user limit :offset,:limit
     * </pre>
     * @return 包含占位符的分页sql
     */
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit,String limitPlaceholder,String sortName,String sort) {
    	throw new UnsupportedOperationException("paged queries not supported");
    }
    
    public String getLimitString(String sql,int limit){
    	return sql;
    }
}