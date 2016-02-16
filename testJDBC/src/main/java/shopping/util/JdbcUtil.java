package shopping.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

// jdbc util class
// singletone

public enum JdbcUtil {

	INSTANCE;
	private static Properties prop = new Properties();

	static {
		// 当前类被加载进JVM是，就会执行驱动的加载。而且只加载一次。
		try {
			// 加载db.properties
			// 从classpath的根路径去加载db.properties.
			InputStream inStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("db.properties");
			prop.load(inStream);

			// 加载JDBC驱动
			Class.forName(prop.getProperty("dirverClassName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建并返回Connection对象
	 */
	public Connection getConn() {

		try {
			return DriverManager.getConnection(prop.getProperty("jdbcUrl"), prop.getProperty("jdbcUserName"),
					prop.getProperty("jdbcPassword"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放资源
	 */
	public void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 事务提交
	 */
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}
	
	/**
	 * 事务回滚
	 */
	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
