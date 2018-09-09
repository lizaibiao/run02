package com.runnew.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author run
 *
 */
@Table(name = "r_menu")
public class MenuEn implements Serializable{

	private static final long serialVersionUID = 4842964565065606425L;
	/*
	 * 主键
	 */
	private String id;
	/*
	 * 菜单编码
	 */
	private String code;
	
	/*
	 * 菜单名
	 */
	private String name;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	
	private Date lastLoginTime;
	
	private Integer status;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
