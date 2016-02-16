package tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import shopping.util.JdbcUtil;


/**
 * 对于同一事务的一组逻辑操作，要么全成功，要么全失败。
 * 事务具有（ACID）特性。
 * A:atomicity原子性。
 * C:Consistency：一致性
 * I:Isolation：隔离性（隔离级别）
 * D：Durability：持久性（不插电状态下也能保留下来，如硬盘）
 * 
 * JDBC中事务的处理机制
 * try{
 *	设定事务为手动提交。
 *	操作1
 *	操作2
 *	操作3
 *	提交事务
 * }catch (Exception e){
 * 	回滚事务
 * }
 * 
 * 注意：
 * 1. JDBC默认事务是自动提交的。在执行DML的时候就会自动提交。
 * 2. JDBC只对DML有效，对DQL无效。
 * 	     但是有时，也通常把查询也放到事务中。
 * 3. 回滚事务是释放资源，释放锁
 *    InnoDB:行锁
 *    Spring中有专门的事务管理器（TransactionManager）
 *    
 * 4. 在Mysql中MyISAM不支持外键，不支持事务的。
 */
public class TransactionTest {
	
	// 使用SQL问如下
	// SELECT * FROM account WHERE name = '张无忌' and balance >= '1000'；
	// UPDATE account SET balance  = balance - 1000 WHERE name = '张无忌'；
	// UPDATE account SET balance = balance = 1000 WHERE name = '赵敏'；
	
	@Test
	public void testName() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			// 设定事务为手动提交
			conn.setAutoCommit(false);
			
			//检测张无忌的账户余额。
			String sql = "SELECT * FROM account WHERE name = ? and balance >= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "张无忌");
			ps.setInt(2, 1000);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new RuntimeException("亲,余额不足!请充值");
			}
			System.out.println("余额充足。");
			
			// 从张无忌账户取出1000元。
			sql = "UPDATE account SET balance  = balance - ? WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1000);
			ps.setString(2, "张无忌");
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				System.out.println("从张无忌取出1000元。");
			}
			
			// 使用异常模拟程序中断。1/0
			// 测试事务
			System.out.println(1/0);

			// 从赵敏的账户上增加1000.
			sql = "UPDATE account SET balance = balance + ? WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1000);
			ps.setString(2, "赵敏");
			cnt = ps.executeUpdate();
			if (cnt > 0) {
				System.out.println("存入赵敏账户1000元。");
			}
			
			// 提交资源
			JdbcUtil.INSTANCE.commit(conn);
			System.out.println("转账成功。");
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚资源
			JdbcUtil.INSTANCE.rollback(conn);
		} finally {
			// 释放资源
			JdbcUtil.INSTANCE.close(conn, ps, rs);
		}
	} 
}
