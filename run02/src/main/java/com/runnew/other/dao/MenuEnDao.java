package com.runnew.other.dao;


import com.runnew.common.dao.BaseDao;
import com.runnew.entity.MenuEn;


public interface MenuEnDao  extends  BaseDao<MenuEn> {
	
   //@Select("SELECT  r.*,r.CREATE_TIME as createTime,r.LAST_LOGIN_TIME as lastLoginTime   FROM R_USER r where r.id=#{userId}")  
//	public   UserEn getById(@Param(value = "userId") String userId);
	
	


}