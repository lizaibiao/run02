package com.run.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.run.entity.UserEn;


/**
 * @ClassName: UserDto 
 * @Description: 用户类
 * @author: lizaibiao
 * @date: 2016年7月2日 下午8:27:53
 */
public class UserEnDto extends UserEn {
	private static final long serialVersionUID = 4126316711873327635L;
	
	private Date  createTime2;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}
	
	

}
