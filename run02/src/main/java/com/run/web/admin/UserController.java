package com.run.web.admin;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.run.common.entity.Page;
import com.run.dto.UserEnDto;
import com.run.entity.UserEn;
import com.run.service.BaseService;
import com.run.service.UserService;


@Controller
@RequestMapping(value = "/admin/user")
public class UserController  implements BaseService {

	@Resource
	public UserService userService;
	
	/**
	 * 保存用户实体
	 */
	@RequestMapping(value = "/saveUser")
	@ResponseBody
	private JSONObject saveUser(Model model,UserEn user){
		JSONObject json = new JSONObject();
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			log.error("UserController.saveUser",e);
		}
		return json;
	}
	/**
	 * 修改实体
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateUser")
	@ResponseBody
	private JSONObject updateUser(Model model,UserEn user) throws Exception{
		JSONObject json = new JSONObject();
		userService.updateUser(user);
		return json;
	}
	
	
	/**
	 * 查询一个实体
	 */
	
	@RequestMapping(value = "/getOneUser")
	@ResponseBody
	private JSONObject getOneUser(Model model,UserEn user){
		JSONObject json = new JSONObject();
		try{
			UserEn en	=userService.getOneUser(user);
			json.put("en",en);
		}catch (Exception e){
			log.error("UserController.getOneUser",e);
		}
		return json;
	}
	
	/**
	 * 删除用户实体
	 */
	@RequestMapping(value = "/delUser")
	@ResponseBody
	private JSONObject delUser(Model model,UserEnDto user,String ids){
		JSONObject json = new JSONObject();
		try{
			userService.delUser(user,ids);
		}catch(Exception e){
			log.error("UserController.delUser",e);
		}
		return json;

	}
	
	
	/**
	 * 查询用户实体（分页）
	 */
	@RequestMapping(value = "/getUserPage")
	@ResponseBody
	private JSONObject getUserPage(Model model,UserEnDto user,Page page2 ,int page, int rows, String order, String sort,String name2){
		JSONObject json = new JSONObject();
		log.error("UserController.getUserPage");
		try {
			page2.setCurrentPage(page);
			page2.setShowCount(rows);
			Page userPage=userService.getUserPage(user, page2,order,sort);
			model.addAttribute("page", userPage);
			json.put("rows",userPage.getDataList());
			json.put("total",userPage.getTotalResult());
		}catch(Exception e){
			log.error("UserController.getUserPage",e);
		}
		return json;
		}
	
}
