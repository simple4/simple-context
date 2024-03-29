package net.simpleframework.ctx;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.ClassUtils.ScanClassResourcesCallback;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class ModuleRegistry {

	static Map<String, Object> registryCache;
	static {
		registryCache = new ConcurrentHashMap<String, Object>();
	}

	public static Object get(final String key) {
		return registryCache.get(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(final Class<T> iClass) {
		return (T) get(iClass.getName());
	}

	public static void registered(final String key, final Object obj) {
		registryCache.put(key, obj);
	}

	public static <T> void registered(final Class<T> iClass, final T obj) {
		if (iClass == null) {
			return;
		}
		registered(iClass.getName(), obj);
	}

	public static <T> void registered(final String[] packageNames, final Class<T> iClass, final T obj)
			throws IOException {
		if (packageNames == null || iClass == null) {
			return;
		}
		for (final String packageName : packageNames) {
			ClassUtils.scanResources(packageName, new ScanClassResourcesCallback() {
				@Override
				public void doResources(final String filepath, final boolean isDirectory)
						throws IOException {
					final Class<?> oClass = loadClass(filepath);
					if (oClass != null && iClass.isAssignableFrom(oClass)) {
						registered(iClass, obj);
					}
				}
			});
		}
	}
}
