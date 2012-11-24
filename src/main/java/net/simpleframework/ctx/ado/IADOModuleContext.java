package net.simpleframework.ctx.ado;

import net.simpleframework.ado.db.IQueryEntityService;
import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.ctx.IModuleContext;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IADOModuleContext extends IModuleContext {

	/**
	 * 获取ADO模块的查询服务
	 * 
	 * @return
	 */
	IQueryEntityService getQueryService();

	/**
	 * 获取ADO模块的实体bean服务
	 * 
	 * @param beanClass
	 * @return
	 */
	ITableEntityService getEntityService(Class<?> beanClass);
}
