package com.run.service;

import com.run.common.entity.Page;
import com.run.dto.UserEnDto;
import com.run.entity.UserEn;
/**
 * 
 * @ClassName: UserService 
 * @Description: 用户类接口
 * @author: lizaibiao
 * @date: 2016年7月2日 下午10:37:01 
 * @param <T>
 */
public interface UserService {
	
	/**
	 * 查询一个实体
	 * @param userEn
	 */
	   public UserEn getOneUser(UserEn userEn);

	/*
	 * 保存用户
	 */
   public void saveUser(UserEn userEn);
   /**
    * 修改用户
    * @param userDto
    * @param ids
    */
   public void updateUser(UserEn userEn);
   
	/*
	 * 删除用户
	 */
  public void  delUser(UserEnDto userDto,String ids);
	 /*
	  * 分页
	  */
    Page getUserPage(UserEnDto userEn,Page page,String sortName,String sort);
}
