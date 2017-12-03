package com.run.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.run.enmu.EnStatus;

/**
 * 
 * @ClassName: UserEn 
 * @Description: 用户类
 * @author: lizaibiao
 * @date: 2016年7月2日 下午8:27:53
 */
public class UserEn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8914645398619834819L;
	/*
	 * 主键
	 */
	private String id;
	/*
	 * 用户编码
	 */
	private String code;
	
	/*
	 * 姓名
	 */
	private String name;
	
	/*
	 * 手机号
	 */
	private String mobile;
	/*
	 * 创建时间
	 */
	private Date createTime;
	
	/*
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/*
	 * 状态
	 */
	private EnStatus emStatus;
	

	/**
	 * @return the status
	 */
	public int getStatus() {
        return emStatus == null ? 0 : emStatus.getValue();
	}

	/**
	 * @param status the status to set
	 */
	@Deprecated
	public void setStatus(int status) {
		setEmStatus(EnStatus.valueOf(status));
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the createTime
	 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")   
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the emStatus
	 */
	public EnStatus getEmStatus() {
		return emStatus;
	}

	/**
	 * @param emStatus the emStatus to set
	 */
	public void setEmStatus(EnStatus emStatus) {
		this.emStatus = emStatus;
	}


}
