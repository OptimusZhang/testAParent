package prepareStatement;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import shopping.domain.Product;
import shopping.util.JdbcUtil;

public class DMLTest {

	@Test
	public void testInsert() throws Exception {
		
		//1.贾连
		Connection conn = JdbcUtil.INSTANCE.getConn();
		
		//2.欲
		String sql = "INSERT INTO product(productName, brand, supplier, salePrice, costPrice, cutoff, dirId) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//3.设置占位符？的值，注意索引从1开始
		ps.setString(1, "iphone5S");
		ps.setString(2, "Apple");
		ps.setString(3, "苹果公司");
		ps.setBigDecimal(4, new BigDecimal("5000"));
		ps.setBigDecimal(5, new BigDecimal("800"));
		ps.setDouble(6, 0.5);
		ps.setLong(7, 5L);
		
		//4.执
		int updateCount = ps.executeUpdate();
		System.out.println(updateCount);
		
		//5.释
		JdbcUtil.INSTANCE.close(conn, ps, null);
	}
}
