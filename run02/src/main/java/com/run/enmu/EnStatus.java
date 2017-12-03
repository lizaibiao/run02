package com.run.enmu;

/**
 * @ClassName: EnStatus
 * @Description: 实体状态枚举类
 * @author: lizaibiao
 * @date: 2016年7月2日 下午9:11:51
 */
public enum EnStatus {
	DISABLE(0),//不启用
	ENABLE (1);//启用
	
	private int value;

	/**
	 * @Title:EnStatus
	 * @Description:TODO
	 * @param value
	 */
	private EnStatus(int value) {
		this.value = value;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	public static EnStatus valueOf(int value) {
		switch (value) {
			case 0:
				return DISABLE;
			case 1:
				return ENABLE;
			default:
				throw new IllegalArgumentException();
		}
	}
}
