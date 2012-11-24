package net.simpleframework.ctx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class Module extends AbstractModule<Module> {

	/* 模块的缺省功能 */
	private ModuleFunction defaultFunction;

	public Module() {
	}

	public ModuleFunction getDefaultFunction() {
		return defaultFunction;
	}

	public Module setDefaultFunction(final ModuleFunction defaultFunction) {
		this.defaultFunction = defaultFunction;
		return this;
	}

	private static final long serialVersionUID = -1782660713880740440L;
}
