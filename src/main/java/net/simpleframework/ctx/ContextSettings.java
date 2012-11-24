package net.simpleframework.ctx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import net.simpleframework.common.Convert;
import net.simpleframework.common.ObjectEx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class ContextSettings extends ObjectEx {
	protected final Properties properties = new Properties();

	private IModuleContext moduleContext;

	public ContextSettings() {
	}

	public ContextSettings(final InputStream iStream) throws IOException {
		load(iStream);
	}

	public IModuleContext getModuleContext() {
		return moduleContext;
	}

	public void setModuleContext(final IModuleContext moduleContext) {
		this.moduleContext = moduleContext;
	}

	public void load(final InputStream iStream) throws IOException {
		if (iStream == null) {
			return;
		}
		try {
			properties.load(iStream);
		} finally {
			try {
				iStream.close();
			} catch (final IOException e) {
			}
		}
	}

	public String[] keys() {
		final Set<Object> keySet = properties.keySet();
		final String[] keys = new String[keySet.size()];
		int i = 0;
		for (final Object key : keySet) {
			keys[i++] = (String) key;
		}
		return keys;
	}

	public void setProperty(final String key, final Object value) {
		properties.setProperty(key, String.valueOf(value));
	}

	public String getProperty(final String key) {
		return properties.getProperty(key);
	}

	public void remove(final String key) {
		properties.remove(key);
	}

	public int getIntProperty(final String key, final int defaultValue) {
		return Convert.toInt(properties.getProperty(key), defaultValue);
	}

	public int getIntProperty(final String key) {
		return getIntProperty(key, 0);
	}

	public boolean getBoolProperty(final String key, final boolean defaultValue) {
		return Convert.toBool(properties.getProperty(key), defaultValue);
	}

	public boolean getBoolProperty(final String key) {
		return getBoolProperty(key, false);
	}
}
