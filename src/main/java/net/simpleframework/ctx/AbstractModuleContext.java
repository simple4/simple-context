package net.simpleframework.ctx;

import javax.sql.DataSource;

import net.simpleframework.common.ObjectEx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractModuleContext extends ObjectEx implements IModuleContext {

	private ContextSettings settings;

	private final Module module = createModule();

	protected abstract Module createModule();

	private DataSource dataSource;

	@Override
	public void onInit() throws Exception {
	}

	@Override
	public Module getModule() {
		return module;
	}

	@Override
	public ModuleFunctions getFunctions(final ModuleFunction parent) {
		return null;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ContextSettings getContextSettings() {
		return settings;
	}

	@Override
	public void setContextSettings(final ContextSettings settings) {
		settings.setModuleContext(this);
		this.settings = settings;
	}

	@Override
	public void doExecuteTransaction(final ITransactionCallback transaction) {
	}

	@Override
	public String toString() {
		return getModule().getText();
	}
}
