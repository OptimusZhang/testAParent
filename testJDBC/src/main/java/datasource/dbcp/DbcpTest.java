package datasource.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import shopping.util.DBCPUtil;
import shopping.util.JdbcUtil;

/**
 * 参考BasicDataSourceExample.java
 * 使用DBCP连接池
 *
 */
public class DbcpTest {

	/**
	 * 不使用连接池，检索DB
	 */
	@Test
	public void testPrepareStatement() throws Exception {

		Connection conn = JdbcUtil.INSTANCE.getConn();
		String sql = "SELECT * FROM product";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Long id = rs.getLong("id");
			String productName = rs.getString("productName");
			System.out.println(id + "\t" + productName);
		}
		JdbcUtil.INSTANCE.close(conn, ps, null);
	}
	
	/**
	 * 使用连接池，检索DB
	 */
	@Test
	public void testDBCP() throws Exception {

		DataSource ds = this.getDataSource();
		Connection conn = ds.getConnection();
		String sql = "SELECT * FROM product";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Long id = rs.getLong("id");
			String productName = rs.getString("productName");
			System.out.println(id + "\t" + productName);
		}
		JdbcUtil.INSTANCE.close(conn, ps, null);
	}
	
	/**
	 * 使用DBCPUtil取得Connection对象，检索DB
	 */
	@Test
	public void testDBCPUtil() throws Exception {

		//使用DBCPUtil的getConn方法来取得Connection对象。
		Connection conn = DBCPUtil.INSTANCE.getConn();
		String sql = "SELECT * FROM product";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Long id = rs.getLong("id");
			String productName = rs.getString("productName");
			System.out.println(id + "\t" + productName);
		}
		JdbcUtil.INSTANCE.close(conn, ps, null);
	}
	
	private DataSource getDataSource() {
		//设置DBCP连接池属性
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jdbcdemo");
        ds.setUsername("root");
        ds.setPassword("password");
        // 设置初始化连接数
        ds.setInitialSize(5);
        
        return ds;
	}
}
