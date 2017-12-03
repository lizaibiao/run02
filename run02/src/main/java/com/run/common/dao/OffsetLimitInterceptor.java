package com.run.common.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;



/**
 * 为ibatis3提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * 
 * 配置文件内容:
 * 
 * <pre>
 * 	&lt;plugins>
 * 	&lt;plugin interceptor="cn.org.rapid_framework.ibatis3.plugin.OffsetLimitInterceptor">
 * 		&lt;property name="dialectClass" value="cn.org.rapid_framework.jdbc.dialect.MySQLDialect"/>
 * 	&lt;/plugin>
 * &lt;/plugins>
 * </pre>
 * 
 * @author badqiu
 * 
 */

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
    RowBounds.class, ResultHandler.class }) })
public class OffsetLimitInterceptor implements Interceptor {
    private static int mappedStatementIndex = 0;
    private static int parameterIndex = 1;
    private static int rowboundsIndex = 2;

    // 设置方言
    private Dialect dialect;

    public Object intercept(Invocation invocation) throws Throwable {
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    /**
     * 拦截分页请求，使用方言将原sql转化成分页sql processIntercept
     * 
     * @param queryArgs
     * @return void
     * @since:0.6
     */
    void processIntercept(final Object[] queryArgs) {
        Map<String, Object> sortMap=null;
        MappedStatement ms = (MappedStatement) queryArgs[mappedStatementIndex];
        Object parameter = queryArgs[parameterIndex];
        final RowBounds rowBounds = (RowBounds) queryArgs[rowboundsIndex];
        if(parameter instanceof Map ){
        sortMap=  (Map<String, Object>) parameter;
        System.err.println(queryArgs);
        System.err.println(sortMap.get("sortName"));
        System.err.println(sortMap.get("sort"));
        }else{
        	sortMap=new HashMap<String, Object>();
        }

        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();
        String sortName=sortMap.get("sortName")==null?null:sortMap.get("sortName").toString();
        String sort=sortMap.get("sort")==null?null:sortMap.get("sort").toString();
        if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit,sortName,sort);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit,sortName,sort);
            }
            limit = RowBounds.NO_ROW_LIMIT;

            queryArgs[rowboundsIndex] = new RowBounds(offset, limit);
            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            // 将原有的BoundSql中的MetaParameter复制到新的BoundSql中
            copyMetaParameters(boundSql, newBoundSql);
            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
            queryArgs[mappedStatementIndex] = newMs;
        }
    }
        
    /**
     * <p>复制BoundSql的MetaParameter</p> 
     * @param lhs
     * @param rhs
     */
    private void copyMetaParameters(BoundSql lhs, BoundSql rhs) {
        for (ParameterMapping map : lhs.getParameterMappings()) {
            String key = map.getProperty();
            Object value = lhs.getAdditionalParameter(key);
            if (null != value) {
                rhs.setAdditionalParameter(key, value);
            }
        }
    }

    /**
     * 
     * <p>获取MappedStatement</p> 
     * @author 柳发勇
     * @date 2013-4-3 上午11:59:03
     * @param ms
     * @param newSqlSource
     * @return
     * @see
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder =
            new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
//        builder.keyProperty(ms.getKeyProperty());

        builder.timeout(ms.getTimeout());

        builder.parameterMap(ms.getParameterMap());

        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    /**
     * 
     * <p>设置方言</p> 
     * @author 柳发勇
     * @date 2013-4-3 上午11:59:45
     * @param properties 
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    public void setProperties(Properties properties) {
        String dialectClass = new PropertiesHelper(properties).getRequiredString("dialectClass");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
        }
    }
    /**
     * 
     * 设置分页boundsql
     * <p style="display:none">modifyRecord</p>
     * <p style="display:none">version:V1.0,author:ningyu,date:2013-4-3 下午12:00:09,content:TODO </p>
     * @author 柳发勇
     * @date 2013-4-3 下午12:00:09
     * @since
     * @version
     */
    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}

