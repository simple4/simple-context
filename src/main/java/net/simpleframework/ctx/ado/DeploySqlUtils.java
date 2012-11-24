package net.simpleframework.ctx.ado;

import static net.simpleframework.common.I18n.$m;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.simpleframework.ado.db.common.DbCreator;
import net.simpleframework.ado.db.common.JdbcUtils;
import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.ClassUtils.IScanResourcesCallback;
import net.simpleframework.common.IoUtils;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class DeploySqlUtils {
	private final static String RESOURCE_NAME = "$resource";

	private static final String SCRIPT_FILENAME = "sql-script";

	public static void executeSqlScript(final DataSource dataSource,
			final String[] scanPackageNames, final ITargetPath tPath) throws IOException {
		System.out.println($m("DeploySqlUtils.0"));
		final List<String> sqlFiles = new ArrayList<String>();
		final IScanResourcesCallback scanDeploySqlCallback = new IScanResourcesCallback() {
			@Override
			public void doResources(String filepath, final boolean isDirectory) throws IOException {
				if (filepath.endsWith("/")) {
					filepath = filepath.substring(0, filepath.length() - 1);
				}
				if (filepath.endsWith(SCRIPT_FILENAME + ".zip")) {
					final InputStream inputStream = ClassUtils.getResourceAsStream(filepath);
					if (inputStream != null) {
						final StringBuilder sb = new StringBuilder();
						sb.append("/").append(RESOURCE_NAME).append("/")
								.append(filepath.substring(0, filepath.lastIndexOf('/')).replace('/', '.'))
								.append("/").append(SCRIPT_FILENAME);
						final String target = tPath.getRealPath(sb.toString());
						IoUtils.unzip(inputStream, target, false);
						sqlFiles.add(target);
					}
				}
			}
		};
		for (final String packageName : scanPackageNames) {
			ClassUtils.scanResources(packageName, scanDeploySqlCallback);
		}

		for (final String sqlFile : sqlFiles) {
			final StringBuilder sb = new StringBuilder();
			sb.append(sqlFile).append(File.separator)
					.append(JdbcUtils.getDatabaseMetaData(dataSource).dbName()).append(File.separator)
					.append(SCRIPT_FILENAME).append(".xml");
			DbCreator.executeSql(dataSource, sb.toString());
		}
	}

	public interface ITargetPath {

		String getRealPath(String p);
	}
}
