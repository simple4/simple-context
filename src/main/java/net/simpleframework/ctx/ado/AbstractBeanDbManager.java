package net.simpleframework.ctx.ado;

import static net.simpleframework.common.I18n.$m;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.simpleframework.ado.DataAccessException;
import net.simpleframework.ado.IDataServiceListener;
import net.simpleframework.ado.db.IQueryEntityService;
import net.simpleframework.ado.db.IQueryEntitySet;
import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.ado.db.common.DbColumn;
import net.simpleframework.ado.db.common.ExpressionValue;
import net.simpleframework.ado.db.common.SqlUtils;
import net.simpleframework.ado.db.event.TableEntityAdapter;
import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.ID;
import net.simpleframework.common.NotImplementedException;
import net.simpleframework.common.ObjectEx;
import net.simpleframework.common.ado.IParamsValue;
import net.simpleframework.common.ado.IParamsValue.AbstractParamsValue;
import net.simpleframework.common.ado.query.DataQueryUtils;
import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.common.bean.IIdBeanAware;
import net.simpleframework.common.bean.IOrderBeanAware;
import net.simpleframework.common.bean.ITreeBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractBeanDbManager<T, M extends T> extends ObjectEx implements
		IBeanManagerAware<T> {
	@Override
	public M createBean() {
		return ClassUtils.newInstance(getBeanClass());
	}

	@Override
	public M getBean(final Object id) {
		return getEntityService().getBean(id, getBeanClass());
	}

	@Override
	public M getBean(final String expr, final Object... params) {
		return getEntityService().queryBean(new ExpressionValue(expr, params), getBeanClass());
	}

	@Override
	public IQueryEntitySet<M> query(final String expr, final Object... params) {
		return getEntityService().query(new ExpressionValue(expr, params), getBeanClass());
	}

	@Override
	public IDataQuery<M> query() {
		return query("1=1");
	}

	@Override
	public List<Object> list(final String column, final String expr, final Object... params) {
		final IQueryEntitySet<Map<String, Object>> qs = getEntityService().query(
				new String[] { column }, new ExpressionValue(expr, params));
		final ArrayList<Object> al = new ArrayList<Object>();
		Map<String, Object> kv;
		while ((kv = qs.next()) != null) {
			al.add(kv.get(column));
		}
		return al;
	}

	@Override
	public int count(final String expr, final Object... params) {
		return getEntityService().count(new ExpressionValue(expr, params));
	}

	@Override
	public int sum(final String column, final String expr, final Object... params) {
		return getEntityService().sum(column, new ExpressionValue(expr, params));
	}

	@Override
	public int max(final String column, final String expr, final Object... params) {
		return getEntityService().max(column, new ExpressionValue(expr, params));
	}

	@Override
	public Object exchange(final T bean1, final T bean2, final DbColumn order, final boolean up) {
		return getEntityService().exchange(bean1, bean2, order, up);
	}

	@Override
	public int delete(final Object... ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		return deleteWith(SqlUtils.getIdsSQLParam("id", ids.length), ids);
	}

	@Override
	public int deleteWith(final String expr, final Object... params) {
		return getEntityService().delete(new ExpressionValue(expr, params));
	}

	@Override
	public void update(final T... beans) {
		update(null, beans);
	}

	@Override
	public void update(final String[] columns, final T... beans) {
		getEntityService().update(columns, beans);
	}

	@Override
	public void insert(final T... beans) {
		getEntityService().insert(beans);
	}

	@Override
	public void addListener(final IDataServiceListener listener) {
		getEntityService().addListener(listener);
	}

	@SuppressWarnings("unchecked")
	public Class<M> getBeanClass() {
		final Type[] types = ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments();
		return types.length == 1 ? (Class<M>) types[0] : (Class<M>) types[1];
	}

	public abstract IADOModuleContext getModuleContext();

	@Override
	public ITableEntityService getEntityService(final Class<?> beanClass) {
		return getModuleContext().getEntityService(beanClass);
	}

	@Override
	public IQueryEntityService getQueryService() {
		return getModuleContext().getQueryService();
	}

	@Override
	public ITableEntityService getEntityService() {
		return getEntityService(getBeanClass());
	}

	protected class TableEntityAdapterEx extends TableEntityAdapter {
		@SuppressWarnings("unchecked")
		protected Collection<T> coll(final IParamsValue paramsValue) {
			final AbstractParamsValue val = (AbstractParamsValue) paramsValue;
			Collection<T> coll = (Collection<T>) val.getAttr("coll");
			if (coll == null) {
				val.setAttr(
						"coll",
						coll = (Collection<T>) DataQueryUtils.toList(getEntityService().query(
								paramsValue, getBeanClass())));
			}
			return coll;
		}
	}

	/* ITreeBeanManagerAware */

	public IDataQuery<M> queryChildren(final T parent) {
		final Class<M> beanClass = getBeanClass();
		if (!IIdBeanAware.class.isAssignableFrom(beanClass)) {
			throw NotImplementedException.of(getClass(), "children");
		}
		String sql = "parentid";
		sql += parent == null ? " is null" : "=?";
		if (IOrderBeanAware.class.isAssignableFrom(beanClass)) {
			sql += " order by oorder desc";
		}
		return parent == null ? query(sql) : query(sql, ((IIdBeanAware) parent).getId());
	}

	public void assertParentId(final T oBean, final ID parentId) {
		if (oBean == null) {
			return;
		}
		if (!(oBean instanceof IIdBeanAware) || !(oBean instanceof ITreeBeanAware)) {
			return;
		}
		if (!(this instanceof ITreeBeanManagerAware)) {
			return;
		}
		/* 不能设置自己为父 */
		final ID oId = ((IIdBeanAware) oBean).getId();
		if (oId == null) {
			return;
		}
		if (oId.equals(parentId)) {
			throw DataAccessException.of($m("AbstractBeanDbManager.0"));
		}
		T p = getBean(parentId);
		while (p != null) {
			final ID pId = ((IIdBeanAware) p).getId();
			if (oId.equals(pId)) {
				throw DataAccessException.of($m("AbstractBeanDbManager.1"));
			}
			p = getBean(((ITreeBeanAware) p).getParentId());
		}
	}
}
