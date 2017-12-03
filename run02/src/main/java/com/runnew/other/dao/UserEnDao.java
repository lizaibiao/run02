package com.runnew.other.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.run.entity.UserEn;


public interface UserEnDao {
	
  //  @Select("SELECT  r.*,r.CREATE_TIME as createTime,r.LAST_LOGIN_TIME as lastLoginTime   FROM R_USER r where r.id=#{id}")  
	public   UserEn getById(@Param(value = "id") int id);

}