package com.run.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;



/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   ORACLE分页方言
* HISTORY
 */
@Component("oracleDialect")
public class OracleDialect extends Dialect {
	
	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}
	
	/**
	 * 
	 * @see com.deppon.foss.framework.server.components.dataaccess.dialect.Dialect#getLimitString(java.lang.String, int, java.lang.String, int, java.lang.String)
	 * getLimitString
	 * @param s
	 * @param offset
	 * @param offsetPlaceholder
	 * @param limit
	 * @param limitPlaceholder
	 * @return
	 * @since:0.6
	 */
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder,String sortName,String sort ) {
		String s = sql.trim();
		boolean isForUpdate = false;
		if (s.toLowerCase().endsWith(" for update") ) {
			s = s.substring( 0, s.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( s.length()+100 );
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
//		if (offset > 0) {
//			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
//		}
//		else {
//			pagingSelect.append("select * from ( ");
//		}
		pagingSelect.append(s);
		if (offset > 0) {
//			int end = offset+limit;
			String endString = offsetPlaceholder+"+"+limitPlaceholder;
			pagingSelect.append(" ) row_  where rownum <=").append(endString).append(") where  rownum_ > " + offsetPlaceholder);
		}
		else {
			pagingSelect.append(" ) row_  where rownum <= ").append(limitPlaceholder).append(") where rownum_ > 0 ");
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}

	/**
	 * 为oracle 的语句添加 查询记录的条数限制
	 * @see com.deppon.foss.framework.server.components.dataaccess.dialect.Dialect#getLimitString(java.lang.String, int)
	 * getLimitString
	 * @param sql
	 * @param limit
	 * @return
	 * @since JDK1.6
	 */
	@Override
	public String getLimitString(String sql,int limit) {
		String tempSql = sql;
		tempSql = tempSql.trim();
		boolean isForUpdate = false;
		if ( tempSql.toLowerCase().endsWith(" for update") ) {
			tempSql = tempSql.substring( 0, tempSql.length()-11 );
			isForUpdate = true;
		}
		String rownumReg="^.*[^a-z^A-Z^_^0-9](rownum)[^a-z^A-Z^_^0-9].*$";
		if(replaceToBlank(tempSql,'\'','\'').matches(rownumReg)){
			return sql;
		}
		StringBuffer pagingSelect = new StringBuffer(sql.length()+50);
		pagingSelect.append("select * from ( ");
		pagingSelect.append( tempSql);
			pagingSelect.append(") where  rownum <");
			pagingSelect.append(limit);
		
		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		return pagingSelect.toString();
	}

	/**
	 * 替换字符串中的以startChar开始和以endChar结尾的字符串为空白字符串
	 * replaceToBlank
	 * @param sql
	 * @param startChar
	 * @param endChar
	 * @return
	 * @return String
	 * @since JDK1.6
	 */
	private String replaceToBlank(String sql,char startChar,char endChar){
		char [] sqlChar = sql.toCharArray();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<sqlChar.length;i++)
		{
			if(list.size()==0){
				if(sqlChar[i] == startChar){
					list.add(i);
				}
			}
			else{
				if(sqlChar[i] == endChar){
					int start = list.get(list.size()-1);
					list.remove(list.size()-1);
					for(int j = start;j<=i;j++)
					{
						sqlChar[j] =' ';
					}
				}
				else if(sqlChar[i] == startChar){
					list.add(i);
				}
				
			}
		}
		return  new String(sqlChar);
	}
}