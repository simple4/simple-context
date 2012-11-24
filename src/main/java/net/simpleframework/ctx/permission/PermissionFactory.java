package net.simpleframework.ctx.permission;

import net.simpleframework.common.ObjectEx;
import net.simpleframework.common.logger.Log;
import net.simpleframework.common.logger.LogFactory;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class PermissionFactory extends ObjectEx {
	private static Log log = LogFactory.getLogger(PermissionFactory.class);

	private static final IPermissionHandler defaultPermission = new DefaultPermissionHandler();

	private static IPermissionHandler _rolePermission;

	public static IPermissionHandler get() {
		return _rolePermission != null ? _rolePermission : defaultPermission;
	}

	public static void set(final String permissionClass) {
		try {
			_rolePermission = (IPermissionHandler) singleton(permissionClass);
		} catch (final ClassNotFoundException e) {
			log.error(e);
		}
	}

	public static void set(final IPermissionHandler rolePermission) {
		_rolePermission = rolePermission;
	}
}
