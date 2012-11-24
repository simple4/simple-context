package net.simpleframework.ctx;

import net.simpleframework.common.SimpleRuntimeException;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class ModuleException extends SimpleRuntimeException {

	public ModuleException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

	public static ModuleException of(final String msg) {
		return _of(ModuleException.class, msg, null);
	}

	private static final long serialVersionUID = 4778304178451841308L;
}
