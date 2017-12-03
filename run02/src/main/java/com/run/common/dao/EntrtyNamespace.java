package com.run.common.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <pre>
 * WFS实体Annotation
 * </pre>
 *
 * @since 
 * 在WFS实体Bean中添加该注解，设定主键与MyBaties配置文件的命名空间。
 * 示例：@WFSBean(namespace="com.deppon.wfs.it.SysChangeData")
 * 		 public class SysChangeData extends WFSEntity implements Serializable{}
 *
 * <pre>
 *	  modify by 柳发勇 on 2014-4-10
 *    fix->1.创建
 *         2.
 * </pre> 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface EntrtyNamespace {
	
	String nameSapce();

}
