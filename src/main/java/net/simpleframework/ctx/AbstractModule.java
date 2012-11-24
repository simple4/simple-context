package net.simpleframework.ctx;

import java.io.Serializable;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class AbstractModule<T extends AbstractModule<T>> implements Serializable {
	/* 标题 */
	private String text;

	/* 功能的唯一名称 */
	private String name;

	/* 功能描述 */
	private String description;

	/* 排序 */
	private int order;

	/* 是否有效 */
	private boolean disabled;

	public String getText() {
		return text;
	}

	public T setText(final String text) {
		this.text = text;
		return (T) this;
	}

	public String getName() {
		return name;
	}

	public T setName(final String name) {
		this.name = name;
		return (T) this;
	}

	public String getDescription() {
		return description;
	}

	public T setDescription(final String description) {
		this.description = description;
		return (T) this;
	}

	public int getOrder() {
		return order;
	}

	public T setOrder(final int order) {
		this.order = order;
		return (T) this;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public T setDisabled(final boolean disabled) {
		this.disabled = disabled;
		return (T) this;
	}

	@Override
	public String toString() {
		return getText();
	}
}
