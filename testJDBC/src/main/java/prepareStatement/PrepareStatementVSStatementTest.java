package prepareStatement;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import java.sql.Statement;

import shopping.util.JdbcUtil;

/**
 * PrepareStatement和Statement的对比
 * 分别用PrepareStatement和Statement插入1000件，来测试性能差异。
 */
public class PrepareStatementVSStatementTest {
	
	/**
	 * 清空t_user表的数据
	 */
	@Test
	public void testinit() throws Exception {
		
		// 取得当前系统时间的毫秒数
		Connection conn = JdbcUtil.INSTANCE.getConn();
		for (int i = 2000; i < 3000; i++) {
			String sql ="DELETE FROM t_user WHERE id > 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}
	}
	
	/**
	 * 使用Statement插入1000条数据
	 * alter table t_user engine = 'MyISAM'
	 * alter table t_user engine = 'InnoDB'
	 * InnoDB:
	 * MyISAM:489ms
	 */
	@Test
	public void testStatement() throws Exception {
		
		// 取得当前系统时间的毫秒数
		Long beginTm = System.currentTimeMillis();
		Connection conn = JdbcUtil.INSTANCE.getConn();
		Statement st = conn.createStatement();
		for (int i = 1000; i < 2000; i++) {
			StringBuilder sql = new StringBuilder(200);
		    sql.append("INSERT INTO t_user (username,password) VALUES(");
		    sql.append("'").append(i).append("',");
		    sql.append("'").append(i).append("'");
		    sql.append(")");
			st.executeUpdate(sql.toString());	
	}
		Long endTm = System.currentTimeMillis();
		System.out.println(endTm  - beginTm);
	}
	/**
	 * 使用PrepareStatement插入1000条数据
	 * InnoDB:
	 * MyISAM:286ms
	 */
	@Test
	public void testPrepareStatement() throws Exception {
		
		// 取得当前系统时间的毫秒数
		Long beginTm = System.currentTimeMillis();
		Connection conn = JdbcUtil.INSTANCE.getConn();
		String sql ="INSERT INTO t_user (username,password) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 2000; i < 3000; i++) {
			ps.setString(1, String.valueOf(i));
			ps.setString(2, String.valueOf(i));
			ps.executeUpdate();
		}
		Long endTm = System.currentTimeMillis();
		System.out.println(endTm  - beginTm);
	}
	
	/**
	 * 测试Statement时的SQL注入
	 * 用户名:输入【' OR 1=1 OR '】
	 * 密码：任意输入
	 */
	@Test
	public void testStatement_DI() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		// SQL注入前，登录失败
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT * FROM t_user WHERE username = 'admin' and password = 'aaaaa'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			// 取得结果集
			if(rs.next()) {
				System.out.println("成功登录！");
			} else {
				System.out.println("登录失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, st, rs);
		}
		
		// SQL注入后，登录成功
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT * FROM t_user WHERE username = '' OR 1=1 OR '' and password = 'aaaaa'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			// 取得结果集
			if(rs.next()) {
				System.out.println("成功登录！");
			} else {
				System.out.println("登录失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, st, rs);
		}
	}
}
