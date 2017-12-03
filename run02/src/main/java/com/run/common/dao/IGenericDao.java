package com.run.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.run.common.entity.Page;

/**
 * <pre>
 * IGenericDao DAO层泛型接口，定义基本的DAO功能
 * @param <T> the generic type
 * @since 实体关系映射的namespace必须按照 包名.实体类名 命名

 */
public interface IGenericDao<T> {
	
	static final String selectAcountNamespace = "com.ane.framework.common.entities.selectAcount";

	public Page queryEntitiesWithPage(String namespace,
			Map<String, Object> map, Page page);

	public List<T> queryListByCondition(String namespace,
			Map<String, Object> condition);

	public List<T> queryListByEntities(String namespace, T entity);

	public T queryObject(String namespace, T entity);

	public int insert(T entity);

	public void insertBatch(String namespace, Collection<T> list);

	public void deleteByPrimaryKey(String namespace, T entity);

	public void deleteBatchByPrimaryKey(List<T> list, String namespace);

	public void deleteBatchByCondition(String namespace, Map<String, Object> condition);
	
	public void updateByPrimaryKey(String namespace, T entity);

	public void updateBatch(String namespace, Collection<T> list);

	public Integer queryCount(String namespace, T entity);
	
	public List<T> queryByList(String namespace,List<T> list);


}