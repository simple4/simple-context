package net.simpleframework.ctx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.ArrayListEx;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class ModuleFunctions extends ArrayListEx<ModuleFunctions, ModuleFunction> {

	public static ModuleFunctions of(final ModuleFunction... functions) {
		return new ModuleFunctions().append(functions);
	}

	private static Map<String, ModuleFunction> funcCache;
	static {
		funcCache = new ConcurrentHashMap<String, ModuleFunction>();
	}

	public static ModuleFunction getDefaultFunction(final String module) {
		final IModuleContext ctx = ModuleContextFactory.get(module);
		Module oModule;
		if (ctx != null && (oModule = ctx.getModule()) != null) {
			return oModule.getDefaultFunction();
		}
		return null;
	}

	public static ModuleFunction getFunctionByName(final String name) {
		if (!StringUtils.hasText(name)) {
			return null;
		}
		ModuleFunction function = funcCache.get(name);
		if (function != null) {
			return function;
		}
		for (final IModuleContext ctx : ModuleContextFactory.allModules()) {
			function = getFunctionByName(ctx, ctx.getFunctions(null), name);
			if (function == null) {
				final ModuleFunction function2 = ctx.getModule().getDefaultFunction();
				if (function2 != null && name.equals(function2.getName())) {
					function = function2;
				}
			}
			if (function != null) {
				funcCache.put(name, function);
				return function;
			}
		}
		return null;
	}

	private static ModuleFunction getFunctionByName(final IModuleContext ctx,
			final ModuleFunctions functions, final String name) {
		if (functions == null) {
			return null;
		}
		for (final ModuleFunction function : functions) {
			if (name.equals(function.getName())) {
				return function;
			}
			final ModuleFunction function2 = getFunctionByName(ctx, ctx.getFunctions(function), name);
			if (function2 != null) {
				return function2;
			}
		}
		return null;
	}

	private static final long serialVersionUID = -2278834911275737189L;
}
