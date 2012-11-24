package net.simpleframework.ctx;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;

import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.ClassUtils.ScanClassResourcesCallback;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class ContextUtils {

	public static void scanModuleContext(final String[] packageNames,
			final IModuleContextCallback callback) throws Exception {
		if (packageNames == null || packageNames.length == 0) {
			return;
		}
		System.out.println($m("ContextUtils.0"));
		for (final String packageName : packageNames) {
			ClassUtils.scanResources(packageName, new ScanClassResourcesCallback() {
				@Override
				public void doResources(final String filepath, final boolean isDirectory)
						throws IOException {
					final IModuleContext ctx = newInstance(loadClass(filepath), IModuleContext.class);
					if (ctx != null) {
						ModuleContextFactory.registered(ctx);
					}
				}
			});
		}

		for (final IModuleContext ctx : ModuleContextFactory.allModules()) {
			System.out.println(ctx.getModule().getText());
			if (callback != null) {
				callback.doModuleContext(ctx);
			}
			ctx.onInit();
		}
	}
}
