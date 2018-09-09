
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

package com.runnew.util.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.runnew.common.mybatis.BaseProvider;
import com.runnew.enmu.EnStatus;
import com.runnew.entity.UserEn;
import com.runnew.other.dao.UserEnDao;
import com.runnew.util.JsonUtil;
import com.runnew.util.UUIDUtil;

/** 
 * @ClassName: Test 
 * @Description: TODO
 * @author: lizaibiao
 * @date: 2016年7月31日 上午11:52:47  
 */
@SuppressWarnings("unused")
public class Test extends AbstractTest  {
	@Autowired
	private UserEnDao userEnDao;
	
	/*
	 * RUN
	 */
   @org.junit.Test
    public void testExecute() {
	   UserEn en =new UserEn();
	   en.setId("2");
	   en.setName("2");
	   try {
			UserEn user=   userEnDao.getBaseDaoById("1");
			System.out.println("哈哈"+user.getMobile());
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
	try {
		System.out.println(Class.forName("com.runnew.other.entity.UserEn"));
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
}

