package shopping.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

// dbcp util class
// singletone

public enum DBCPUtil {

	INSTANCE;
	private static DataSource dataSource;

	static {
		Properties prop = new Properties();
		// 当前类被加载进JVM是，就会执行驱动的加载。而且只加载一次。
		try {
			// 加载dbcp.properties
			// 去classpath的根路径去找dbcp.properties.
			InputStream inStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("dbcp.properties");
			prop.load(inStream);
			// 把装有连接信息的propety对象传给工厂类。
			// BasicDataSourceFactory里会执行配置文件注入，将属性的key值生成setKey形式来初始化连接池对象。
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建并返回Connection对象
	 */
	public Connection getConn() {

		try {
			return dataSource.getConnection();
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
