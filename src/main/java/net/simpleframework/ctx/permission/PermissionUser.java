package net.simpleframework.ctx.permission;

import java.io.InputStream;

import net.simpleframework.common.ID;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class PermissionUser extends PermissionEntity<PermissionUser> {
	/* 机构id */
	private ID orgId;

	/* 头像 */
	private InputStream photoStream;

	public InputStream getPhotoStream() {
		return photoStream;
	}

	public PermissionUser setPhotoStream(final InputStream photoStream) {
		this.photoStream = photoStream;
		return this;
	}

	public ID getOrgId() {
		return orgId;
	}

	public PermissionUser setOrgId(final ID orgId) {
		this.orgId = orgId;
		return this;
	}

	private static final long serialVersionUID = -7880069050882902556L;
}
