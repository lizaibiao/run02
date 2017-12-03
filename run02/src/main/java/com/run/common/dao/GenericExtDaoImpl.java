package com.run.common.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.run.common.entity.Page;



@Repository
public class GenericExtDaoImpl<T> implements IGenericExtDao<T> {

	public final String selectAcountNamespace = "com.run.framework.common.entities.selectAcount";

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<T> queryListByCondition(String namespace,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(namespace, condition);
	}

	@Override
	public List<T> queryListByEntities(String namespace, T entity) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(namespace, entity);
	}

	@Override
	public T queryObject(String namespace, T entity) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(namespace, entity);
	}

	@Override
	public void insert(String namespace, T entity) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(namespace, entity);
	}

	@Override
	public void insertBatch(String namespace, Collection<T> list) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(namespace, list);
	}

	@Override
	public Page queryEntitiesWithPage(String namespace,
			Map<String, Object> map, Page page) {
		page = page == null ? new Page() : page;
		// 1、获取每页显示的数据（10条）
		RowBounds row = new RowBounds((page.getCurrentPage() - 1)
				* page.getShowCount(), page.getShowCount());
		page.setDataList(sqlSessionTemplate.selectList(namespace, map, row));
		// 2、将获取的总记录数放进pageCount
		page.setTotalResult(pageCount(namespace, map));
		return page;
	}

	private int pageCount(String namespace, Map<String, Object> map) {
		// 1.1、获取查询的sql语句
		// 1.1.1、获取sql语句映射
		MappedStatement mapper = sqlSessionTemplate.getConfiguration()
				.getMappedStatement(namespace);
		// 1.1.2、获取映射的信息
		BoundSql bound = mapper.getBoundSql(map);
		// 获取sql语句的参数信息，对应的还有ResultMapping，也就是返回的结果信息
		List<ParameterMapping> list = bound.getParameterMappings();
		// 1.1.3、获取sql语句
		String querySql = bound.getSql();
		if (!list.isEmpty()) {
			/**
			 * 将sql语句中的问号（也就是参数值，起初由“？”表示）转换为属性名称，其中的“\\”是转义字符
			 * 假设上诉的querySql是select AreaId from area where AreaId=?
			 * 经过下面的语句转换后是：select AreaId from area where AreaId=#{areaId}
			 */
			for (ParameterMapping p : list) {
				querySql = querySql.replaceFirst("\\?", "#{" + p.getProperty()
						+ "}");
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		// 1.2、将sql语句放入map
		param.put("querySql", querySql);
		if (!map.isEmpty()) {
			param.putAll(map);
		}
		// 1.3、获取总记录数
		int count = ((Integer) sqlSessionTemplate.selectOne(
				selectAcountNamespace, param)).intValue();
		return count;
	}

	@Override
	public void deleteByPrimaryKey(String namespace, T entity) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete(namespace, entity);
	}

	@Override
	public int deleteBatchByPrimaryKey(List<T> list, String namespace) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete(namespace, list);
	}

	@Override
	public void updateByPrimaryKey(String namespace, T entity) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update(namespace, entity);
	}

	@Override
	public Integer queryCount(String namespace, T entity) {
		List<Object> count = sqlSessionTemplate.selectList(namespace, entity);
		return count.size();
	}

	@Override
	public void updateBatch(String namespace, Collection<T> list) {
		sqlSessionTemplate.update(namespace, list);
	}

	
	@Override
	public List<T> queryByList(String namespace, List<T> list) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(namespace, list);
	}

	
	@Override
	public void deleteBatchByCondition(String namespace,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete(namespace, condition);
	}

}
