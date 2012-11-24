package net.simpleframework.ctx.ado;

import net.simpleframework.ado.DataAccessException;
import net.simpleframework.ado.DataServiceFactory;
import net.simpleframework.ado.db.IQueryEntityService;
import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.ado.db.common.TransactionVoidCallback;
import net.simpleframework.ctx.AbstractModuleContext;
import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.ctx.ITransactionCallback;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractADOModuleContext extends AbstractModuleContext implements
		IADOModuleContext {

	protected DataServiceFactory dataServiceFactory;

	@Override
	public void onInit() throws Exception {
		super.onInit();
		dataServiceFactory = new DataServiceFactory(getDataSource());
	}

	@Override
	public ITableEntityService getEntityService(final Class<?> beanClass) {
		return dataServiceFactory.getEntityService(beanClass);
	}

	@Override
	public IQueryEntityService getQueryService() {
		return dataServiceFactory.getQueryService();
	}

	@Override
	public void doExecuteTransaction(final ITransactionCallback transaction) {
		final IModuleContext moduleContext = this;
		getQueryService().doExecuteTransaction(new TransactionVoidCallback() {
			@Override
			protected void doTransactionVoidCallback() throws DataAccessException {
				try {
					transaction.doTransaction(moduleContext);
				} catch (final Exception e) {
					throw DataAccessException.of(e);
				}
			}
		});
	}
}
