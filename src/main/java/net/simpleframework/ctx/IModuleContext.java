package net.simpleframework.ctx;

import javax.sql.DataSource;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IModuleContext {

	/**
	 * 初始化
	 * 
	 * 异常抛出，不处理
	 * 
	 * @throws Exception
	 */
	void onInit() throws Exception;

	/**
	 * 获取配置信息
	 * 
	 * @return
	 */
	ContextSettings getContextSettings();

	/**
	 * 
	 * @param settings
	 */
	void setContextSettings(ContextSettings settings);

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	DataSource getDataSource();

	void setDataSource(DataSource dataSource);

	/**
	 * 在回调中执行事务操作。具体实现子类可覆盖
	 * 
	 * @param transaction
	 */
	void doExecuteTransaction(ITransactionCallback transaction);

	/**
	 * 获取Module定义
	 * 
	 * @return
	 */
	Module getModule();

	/**
	 * 获取模块的功能列表
	 * 
	 * @param parent
	 * @return
	 */
	ModuleFunctions getFunctions(ModuleFunction parent);
}
