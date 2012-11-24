package net.simpleframework.ctx.permission;

import java.io.Serializable;

import net.simpleframework.common.ID;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class PermissionEntity<T extends PermissionEntity<T>> implements Serializable {
	private ID id;

	private String text = "";

	private String name;

	public ID getId() {
		return id;
	}

	public T setId(final ID id) {
		this.id = id;
		return (T) this;
	}

	public String getName() {
		return name;
	}

	public T setName(final String name) {
		this.name = name;
		return (T) this;
	}

	public String getText() {
		return text;
	}

	public T setText(final String text) {
		this.text = text;
		return (T) this;
	}

	@Override
	public String toString() {
		return getText();
	}
}
