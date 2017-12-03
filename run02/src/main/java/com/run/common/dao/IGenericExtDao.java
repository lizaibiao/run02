package com.run.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.run.common.entity.Page;

/**
 * 封装通用到层  
 * 实体关系映射的namespace:需要手动传命名空间
 * @author lzb
 * @param <T>
 */
public interface IGenericExtDao<T> {

	public Page queryEntitiesWithPage(String namespace,
			Map<String, Object> map, Page page);

	public List<T> queryListByCondition(String namespace,
			Map<String, Object> condition);

	public List<T> queryListByEntities(String namespace, T entity);

	public T queryObject(String namespace, T entity);

	public void insert(String namespace, T entity);

	public void insertBatch(String namespace, Collection<T> list);

	public void deleteByPrimaryKey(String namespace, T entity);

	public int deleteBatchByPrimaryKey(List<T> list, String namespace);

	public void deleteBatchByCondition(String namespace, Map<String, Object> condition);
	
	public void updateByPrimaryKey(String namespace, T entity);

	public void updateBatch(String namespace, Collection<T> list);

	public Integer queryCount(String namespace, T entity);
	
	public List<T> queryByList(String namespace,List<T> list);

}
