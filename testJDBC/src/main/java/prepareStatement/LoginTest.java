package prepareStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import shopping.util.JdbcUtil;

/**
 * 测试登录画面的检证API
 *
 */
public class LoginTest {

	/**
	 *方式1  
	 *使用用户名和密码双重条件进行检索（不推荐） 
	 */
	@Test
	public void test1() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT * FROM t_user WHERE username = ? and password = ?";
			ps = conn.prepareStatement(sql );
			ps.setString(1, "test1");
			ps.setString(2, "password1");
			rs = ps.executeQuery();
			
			// 取得结果集
			if(rs.next()) {
				System.out.println("成功登录！");
			} else {
				System.out.println("登录失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, ps, rs);
		}
	}
	
	/**
	 * 方式2
	 * 使用用户名和密码检索后，判定结果件数，如果件数大于0则判定为成功（不推荐）
	 */
	@Test
	public void test2() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT COUNT(*) FROM t_user WHERE username = ? and password = ?";
			ps = conn.prepareStatement(sql );
			ps.setString(1, "test1");
			ps.setString(2, "password1");
			rs = ps.executeQuery();
			
			// 取得结果集
			if(rs.next()) {
				Long cnt = rs.getLong(1);
				if (cnt == 1) {
					System.out.println("成功登录！");
				} else  {
					System.out.println("登录失败！");
				}
			} else {
				System.out.println("登录失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, ps, rs);
		}
	}
	
	 //	方式3
	/**
	 * 方式3
	 * 使用用户名为条件进行检索，
	 * 如果没有检索结果，那么用户名不存在。
	 * 如果有检索结果，
	 * 		如果检索结果的密码和用户输入的密码不一致，则密码错误
	 * 		如果检索结果的密码和用户输入的密码一致，则成功登录。
	 */
	@Test
	public void test3() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT * FROM t_user WHERE username = ?";
			ps = conn.prepareStatement(sql );
			ps.setString(1, "test1");
			rs = ps.executeQuery();
			
			// 取得结果集
			if(rs.next()) {
				String pwd = rs.getString("password");
				if (pwd.equals("password1")) {
					System.out.println("登录成功！");
				}else{
					System.out.println("密码错误！");
				}
			} else {
				System.out.println("用户名不存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, ps, rs);
		}
	}
	
	
}
