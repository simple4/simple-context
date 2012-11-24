package net.simpleframework.ctx.ado;

import net.simpleframework.common.ID;
import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.common.bean.ITreeBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface ITreeBeanManagerAware<T extends ITreeBeanAware> {

	/**
	 * 获取孩子列表
	 * 
	 * @param parent
	 * @return
	 */
	IDataQuery<? extends T> queryChildren(T parent);

	/**
	 * 这是一个helper方法，验证parentId的合法性
	 * 
	 * @param oBean
	 * @param parentId
	 */
	void assertParentId(T oBean, ID parentId);
}
