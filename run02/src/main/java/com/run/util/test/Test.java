
/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: Test.java 
 * @Prject: run
 * @Package: com.run.util.test 
 * @Description: TODO
 * @author: lizaibiao
 * @date: 2016年7月31日 上午11:52:47 
 * @version: V1.0   
 */

package com.run.util.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.run.enmu.EnStatus;
import com.run.entity.UserEn;
import com.run.service.UserService;
import com.run.util.JsonUtil;
import com.run.util.UUIDUtil;
import com.runnew.common.mybatis.BaseProvider;
import com.runnew.other.dao.UserEnDao;

/** 
 * @ClassName: Test 
 * @Description: TODO
 * @author: lizaibiao
 * @date: 2016年7月31日 上午11:52:47  
 */
@SuppressWarnings("unused")
public class Test extends AbstractTest  {
	@Autowired
	private UserService userService;
	@Autowired
	private UserEnDao userEnDao;
	
	/*
	 * RUN
	 */
   @org.junit.Test
    public void testExecute() {
	UserEn user=   userEnDao.getById(1);
	System.out.println(user.getName());
    }
	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		 Class<?> modelClass = BaseProvider.threadModelClass.get();
		 System.out.println(modelClass);
	}
}

