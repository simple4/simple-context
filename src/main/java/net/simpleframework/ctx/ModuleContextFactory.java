package net.simpleframework.ctx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.simpleframework.common.ObjectEx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class ModuleContextFactory extends ObjectEx {

	public static IModuleContext get(final String module) {
		return moduleCache.get(module);
	}

	static Map<String, IModuleContext> moduleCache;
	static {
		moduleCache = new ConcurrentHashMap<String, IModuleContext>();
	}

	public static Collection<IModuleContext> allModules() {
		final ArrayList<IModuleContext> modules = new ArrayList<IModuleContext>(moduleCache.values());
		Collections.sort(modules, new Comparator<IModuleContext>() {
			@Override
			public int compare(final IModuleContext ctx1, final IModuleContext ctx2) {
				return ctx1.getModule().getOrder() - ctx2.getModule().getOrder();
			}
		});
		return modules;
	}

	public static void registered(final IModuleContext context) {
		if (context == null) {
			return;
		}
		final Module module = context.getModule();
		if (!module.isDisabled()) {
			moduleCache.put(module.getName(), context);
		}
	}
}
