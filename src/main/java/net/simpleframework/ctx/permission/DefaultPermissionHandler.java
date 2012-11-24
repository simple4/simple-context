package net.simpleframework.ctx.permission;

import java.util.Collection;
import java.util.Map;

import net.simpleframework.common.ID;
import net.simpleframework.common.ObjectEx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class DefaultPermissionHandler extends ObjectEx implements IPermissionHandler {

	@Override
	public boolean isMember(final Object user, final Object role, final Map<String, Object> variables) {
		return true;
	}

	@Override
	public boolean isMember(final Object user, final Object role) {
		return isMember(user, role, null);
	}

	private static PermissionUser NULL_USER = new PermissionUser();

	@Override
	public PermissionUser getUser(final Object user) {
		return NULL_USER;
	}

	@Override
	public Collection<ID> users(final Object role, final Map<String, Object> variables) {
		return null;
	}

	private static PermissionRole NULL_ROLE = new PermissionRole();

	@Override
	public PermissionRole getRole(final Object role) {
		return NULL_ROLE;
	}

	@Override
	public ID getRoleIdByUser(final Object user) {
		return null;
	}

	@Override
	public Collection<ID> roles(final Object user, final Map<String, Object> variables) {
		return null;
	}
}
