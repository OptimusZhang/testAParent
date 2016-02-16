package jdbcBatch;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.StringUtils;

import shopping.util.JdbcUtil;

/**
 * @author jianwu.zhang
 * addBatch()
 * executeBatch()
 * 多条sql语句的批量执行要用到下面两种情况
 * Statement：
 * 	
 * PrepareStatement：
 * 	
 */
public class BatchTest {

	/**
	 * 使用Statement，向account中插入1000条数据。
	 */
	@Test
	public void testStatement() throws Exception {
		long begin = System.currentTimeMillis();
		Connection conn = JdbcUtil.INSTANCE.getConn();
		Statement st = conn.createStatement();
		for (int i=1; i<=1000; i++ ) {
			String sql = "INSERT INTO account(name,balance) VALUES('韦小宝','28')";
			st.executeUpdate(sql);
		}
		JdbcUtil.INSTANCE.close(conn, st, null);
		long end = System.currentTimeMillis();
		System.out.println(end-begin);

	}
	
	/**
	 * 使用Statement的批量操作。
	 */
	@Test
	public void testStatementBatch() throws Exception {
		long begin = System.currentTimeMillis();
		Connection conn = JdbcUtil.INSTANCE.getConn();
		Statement st = conn.createStatement();
		for (int i = 0; i < 1000; i++) {
			String sql = "INSERT INTO account(name,balance) VALUES('韦小宝','28')";
			// 将SQL放入缓存中。
			st.addBatch(sql);
			if (i%200 == 0) {
				// 每200件执行一次缓存中的SQL
				st.executeBatch();
			}
		}
		JdbcUtil.INSTANCE.close(conn, st, null);
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
		
	}
	
	
	/**
	 * 使用PrepareStatement，向account中插入1000条数据。
	 */
	@Test
	public void testPrepareStatement() throws Exception {
		long begin = System.currentTimeMillis();
		Connection conn = JdbcUtil.INSTANCE.getConn();
		PreparedStatement ps = null;
		String sql = "INSERT INTO account(name,balance) VALUES(?,?)";
		ps = conn.prepareStatement(sql);
		for (int i=1; i<=1000; i++ ) {
			ps.setString(1, "乔峰");
			ps.setInt(2, 1000);
			ps.executeUpdate();
		}
		JdbcUtil.INSTANCE.close(conn, ps, null);
		long end = System.currentTimeMillis();
		System.out.println(end-begin);

	}
	
	/**
	 * 使用PrepareStatement的批量操作。
	 */
	@Test
	public void testPrepareStatementBatch() throws Exception {
		long begin = System.currentTimeMillis();
		Connection conn = JdbcUtil.INSTANCE.getConn();
		PreparedStatement ps = null;
		String sql = "INSERT INTO account(name,balance) VALUES(?,?)";
		for (int i=1; i<=1000; i++ ) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "乔峰");
			ps.setInt(2, 1000);
			ps.addBatch(); // PrepareStatement是不需要参数的。
			if (i%200 == 0) {
				ps.executeBatch(); // 执行批量缓存
				ps.clearBatch(); // 清除批量缓存
				ps.clearParameters();// 清除批量参数
			}
		}
		JdbcUtil.INSTANCE.close(conn, ps, null);
		long end = System.currentTimeMillis();
		System.out.println(end-begin);

	}
}
