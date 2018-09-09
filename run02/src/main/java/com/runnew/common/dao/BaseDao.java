package com.runnew.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.runnew.common.entity.BaseEntity;
import com.runnew.common.mybatis.BaseProvider;
import com.runnew.common.mybatis.Sort;
import com.runnew.common.mybatis.complexQuery.CustomQueryParam;


//public interface BaseDao<T extends BaseEntity> {
	
	public interface BaseDao<T> {

	
//	@SelectProvider(type = BaseProvider.class, method = "getAll")
//	@ResultMap("getMap")
//	public List<T> getAll();

	@SelectProvider(type = BaseProvider.class, method = "getBaseDaoById")
    @ResultMap("getBaseDaoByIdMap")
	public T getBaseDaoById(String id);
//
//	@SelectProvider(type = BaseProvider.class, method = "count")
//	public int count(T findParams);
//
//	@SelectProvider(type = BaseProvider.class, method = "countQuery")
//	public int countQuery(@Param("queryParams") List<CustomQueryParam> customQueryParams);
//	
//	@SelectProvider(type = BaseProvider.class, method = "get")
//	@ResultMap("getMap")
//	public T getOne(T findParams);
//	
//	@SelectProvider(type = BaseProvider.class, method = "query")
//	@ResultMap("getMap")
//	public List<T> query(@Param("queryParams") List<CustomQueryParam> customQueryParams, @Param("sortList") List<Sort> sortList);
//
//	@SelectProvider(type = BaseProvider.class, method = "get")
//	@ResultMap("getMap")
//	public List<T> get(T findParams);
//
//	@SelectProvider(type = BaseProvider.class, method = "find")
//	@ResultMap("getMap")
//	public List<T> find(T findParams);
//
//	@InsertProvider(type = BaseProvider.class, method = "insert")
//	@Options(keyProperty = "id")
//	public int insert(T t);
//	
//	@DeleteProvider(type = BaseProvider.class, method = "delete")
//	public int delete(String id);
//
//	@UpdateProvider(type = BaseProvider.class, method = "update")
//	public int update(T t);
//
//    @DeleteProvider(type = BaseProvider.class,method = "deleteAll")
//    public int deleteAll();

}