package net.simpleframework.ctx.ado;

import java.util.List;

import net.simpleframework.ado.IDataServiceListener;
import net.simpleframework.ado.db.IQueryEntityService;
import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.ado.db.common.DbColumn;
import net.simpleframework.common.ado.query.IDataQuery;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IBeanManagerAware<T> {

	/**
	 * 由id获取对象
	 * 
	 * @param id
	 * @return
	 */
	T getBean(Object id);

	/**
	 * 通过参数获取bean
	 * 
	 * @param expr
	 * @param params
	 * @return
	 */
	T getBean(String expr, Object... params);

	/**
	 * 创建bean的实例，不执行序列化操作
	 * 
	 * @return
	 */
	T createBean();

	/**
	 * 
	 * @param expr
	 * @param params
	 * @return
	 */
	IDataQuery<? extends T> query(String expr, Object... params);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	IDataQuery<? extends T> query();

	/**
	 * 取某一列的集合
	 * 
	 * @param column
	 * @param expr
	 * @param params
	 * @return
	 */
	List<Object> list(String column, String expr, Object... params);

	/**
	 * 
	 * @param ids
	 * @return
	 */
	int delete(Object... ids);

	/**
	 * 删除表达式
	 * 
	 * @param expr
	 * @param params
	 * @return
	 */
	int deleteWith(String expr, Object... params);

	/**
	 * 更新
	 * 
	 * @param bean
	 */
	void update(T... beans);

	void update(String[] columns, T... beans);

	/**
	 * 插入
	 * 
	 * @param bean
	 */
	void insert(T... beans);

	/**
	 * 求总数
	 * 
	 * @param expr
	 * @param params
	 * @return
	 */
	int count(String expr, Object... params);

	/**
	 * 求某一列的和
	 * 
	 * @param column
	 * @param expr
	 * @param params
	 * @return
	 */
	int sum(String column, String expr, Object... params);

	/**
	 * 求某一列的最大值
	 * 
	 * @param column
	 * @param expr
	 * @param params
	 * @return
	 */
	int max(String column, String expr, Object... params);

	/**
	 * @param bean1
	 * @param bean2
	 * @param order
	 * @param up
	 * @return
	 */
	Object exchange(T bean1, T bean2, DbColumn order, boolean up);

	void addListener(IDataServiceListener listener);

	/**
	 * 获取实体表管理器
	 * 
	 * @return
	 */
	ITableEntityService getEntityService();

	ITableEntityService getEntityService(Class<?> beanClass);

	/**
	 * 获取查询管理器
	 * 
	 * @return
	 */
	IQueryEntityService getQueryService();
}
