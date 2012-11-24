package net.simpleframework.ctx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class ModuleFunction extends AbstractModule<ModuleFunction> {

	/* 功能的管理员角色 */
	private String role;

	public String getRole() {
		return role;
	}

	public ModuleFunction setRole(final String role) {
		this.role = role;
		return this;
	}

	private static final long serialVersionUID = -5396854627783234010L;
}
