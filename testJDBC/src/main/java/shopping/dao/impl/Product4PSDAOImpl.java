package shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shopping.dao.IProductDAO;
import shopping.domain.Product;
import shopping.util.JdbcUtil;

/**
 * 使用PrepareStatement来完成DAO
 */
public class Product4PSDAOImpl implements IProductDAO {

	@Override
	public void save(Product pro) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 注册驱动，获取链接。
			conn = JdbcUtil.INSTANCE.getConn();
			
			// 创建语句。
			String sql = "INSERT INTO product(productName, brand, supplier, salePrice, costPrice, cutoff, dirId) VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			// 设定占位符的值。
			ps.setString(1, pro.getProductName());
			ps.setString(2, pro.getBrand());
			ps.setString(3, pro.getSupplier());
			ps.setBigDecimal(4, pro.getSalePrice());
			ps.setBigDecimal(5, pro.getCostPrice());
			ps.setDouble(6, pro.getCutoff());
			ps.setLong(7, pro.getDirId());
			
			//执行SQL。
			int cnt = ps.executeUpdate();
			System.out.println(cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			JdbcUtil.INSTANCE.close(conn, ps, null);
		}

	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "DELETE FROM product WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			int cnt = ps.executeUpdate();
			System.out.println(cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, ps, null);
		}
	}

	@Override
	public void update(Product pro) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 注册驱动，获取链接。
			conn = JdbcUtil.INSTANCE.getConn();
			
			// 创建语句。
			String sql = "UPDATE product SET productName=?, brand=?, supplier=?, salePrice=?, costPrice=?, cutoff=?, dirId=? WHERE id=?";
			ps = conn.prepareStatement(sql);
			
			// 设定占位符的值。
			ps.setString(1, pro.getProductName());
			ps.setString(2, pro.getBrand());
			ps.setString(3, pro.getSupplier());
			ps.setBigDecimal(4, pro.getSalePrice());
			ps.setBigDecimal(5, pro.getCostPrice());
			ps.setDouble(6, pro.getCutoff());
			ps.setLong(7, pro.getDirId());
			ps.setLong(8, pro.getId());
			
			//执行SQL。
			int cnt = ps.executeUpdate();
			System.out.println(cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			JdbcUtil.INSTANCE.close(conn, ps, null);
		}

	}

	@Override
	public Product get(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT * FROM product WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Product pro = new Product();
				pro.setId(rs.getLong("id"));
				pro.setProductName(rs.getString("productName"));
				pro.setBrand(rs.getString("brand"));
				pro.setSupplier(rs.getString("supplier"));
				pro.setCostPrice(rs.getBigDecimal("costPrice"));
				pro.setSalePrice(rs.getBigDecimal("salePrice"));
				pro.setCutoff(rs.getDouble("cutoff"));
				pro.setDirId(rs.getLong("dirId"));
				return pro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public List<Product> list() {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			String sql = "SELECT * FROM product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// dell with the resultSet
			while (rs.next()) {
				Product pro = new Product();
				list.add(pro);

				pro.setId(rs.getLong("id"));
				pro.setProductName(rs.getString("productName"));
				pro.setBrand(rs.getString("brand"));
				pro.setSupplier(rs.getString("supplier"));
				pro.setCostPrice(rs.getBigDecimal("costPrice"));
				pro.setSalePrice(rs.getBigDecimal("salePrice"));
				pro.setCutoff(rs.getDouble("cutoff"));
				pro.setDirId(rs.getLong("dirId"));
				

			}
			System.out.println(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, ps, rs);
		}
		return null;
	}

}
