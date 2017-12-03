package com.run.service.impl;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.run.common.dao.IGenericDao;
import com.run.common.dao.IGenericExtDao;
import com.run.common.entity.Page;
import com.run.dto.UserEnDto;
import com.run.enmu.EnStatus;
import com.run.entity.UserEn;
import com.run.service.BaseService;
import com.run.service.UserService;
import com.run.util.RandomGUID;
import com.run.util.StringUtils;

/**
 * @ClassName: UserServiceImpl 
 * @Description: 用户实现类
 * @author: lizaibiao
 * @date: 2016年7月2日 下午10:38:00 
 * @param <T>
 */
@Service
@Transactional(readOnly=false) //对业务类进行事务增强的   标注 
@SuppressWarnings("all")
public class UserServiceImpl implements UserService , BaseService {

	@Resource
	private IGenericExtDao genericExtDao;
	@Resource
	private IGenericDao genericDao;
	
    private String userEnMapper = UserEn.class.getName();


	/* (non Javadoc) 
	 * @Title: saveUser
	 * @Description: 保存用户实体
	 * @param userEn 
	 * @see com.run.service.UserService#saveUser(com.run.entity.UserEn) 
	 */
	@Override
	public void saveUser(UserEn userEn) {
		Date date=new Date();
		userEn.setId(RandomGUID.getRandomGUID());
		userEn.setCreateTime(date);
		userEn.setLastLoginTime(date);
		userEn.setEmStatus(EnStatus.ENABLE);//启用
		genericExtDao.insert(userEnMapper+".saveUser", userEn);
	}
	
	
	@Override
	public void updateUser(UserEn userEn) {
		genericExtDao.updateByPrimaryKey(userEnMapper+".updateUser", userEn);
	}

	/**
	 * 删除用户实体
	 */
	@Override
	public void delUser(UserEnDto userDto,String ids) {
		List<Object> list=StringUtils.StringToList(ids, null);
		int i=genericExtDao.deleteBatchByPrimaryKey(list, userEnMapper+".delUser");
	}


	/**
	 * 分页查询用户
	 */
	public Page getUserPage(UserEnDto userEn, Page page,String sort,String sortName) {
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("entity", userEn);
		condition.put("sortName", sortName);
		condition.put("sort", sort);
		Page userPage=null;
		try {
		userPage= this.genericExtDao.queryEntitiesWithPage(userEnMapper+".queryUserPage",
					   condition,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userPage;
	}

	@Override
	public UserEn getOneUser(UserEn userEn) {
		UserEn  en=	(UserEn) this.genericExtDao.queryObject(userEnMapper+".getOneUser", userEn);
		return  en;
	}




}
