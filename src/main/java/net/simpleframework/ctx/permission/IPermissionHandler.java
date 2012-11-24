package net.simpleframework.ctx.permission;

import java.util.Collection;
import java.util.Map;

import net.simpleframework.common.ID;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IPermissionHandler {
	/**
	 * 匿名用户
	 */
	static final String sj_anonymous = "sys_anonymous";

	/**
	 * 正常的注册用户
	 */
	static final String sj_all_account = "sys_all_account";

	/**
	 * 已锁定的注册用户
	 */
	static final String sj_lock_account = "sys_lock_account";

	/**
	 * 系统管理员
	 */
	static final String sj_manager = "sys_manager";

	/**
	 * 指定用户是否为某一指定角色的成员
	 * 
	 * @param user
	 * @param role
	 * @param variables
	 * @return
	 */
	boolean isMember(Object user, Object role, Map<String, Object> variables);

	/**
	 * 指定用户是否为某一指定角色的成员，默认环境变量=null
	 * 
	 * @param user
	 * @param role
	 * @return
	 */
	boolean isMember(Object user, Object role);

	/**
	 * 得到权限用户对象
	 * 
	 * @param user
	 * @return
	 */
	PermissionUser getUser(Object user);

	/**
	 * 获取指定角色名的所有用户
	 * 
	 * @param role
	 * @param variables
	 * @return
	 */
	Collection<ID> users(Object role, Map<String, Object> variables);

	/**
	 * 得到权限角色对象
	 * 
	 * @param role
	 * @return
	 */
	PermissionRole getRole(Object role);

	/**
	 * 用户存在多个角色，此函数返回用户的默认角色
	 * 
	 * @param user
	 * @return
	 */
	ID getRoleIdByUser(Object user);

	/**
	 * 获取指定用户的所有角色
	 * 
	 * @param user
	 * @param variables
	 * @return
	 */
	Collection<ID> roles(Object user, Map<String, Object> variables);
}
