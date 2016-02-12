package shopping.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shopping.dao.IProductDAO;
import shopping.domain.Product;
import shopping.util.JdbcUtil;

public class ProductDAOImpl implements IProductDAO {
	
	@Override
	public void save(Product pro) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			st = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO product(productName, brand, supplier, salePrice, costPrice, cutoff, dirId) VALUES(");
			sql.append("'").append(pro.getProductName()).append("',");
			sql.append("'").append(pro.getBrand()).append("',");
			sql.append("'").append(pro.getSupplier()).append("',");
			sql.append(pro.getSalePrice()).append(",");
			sql.append(pro.getCostPrice()).append(",");
			sql.append(pro.getCutoff()).append(",");
			sql.append(pro.getDirId());
			sql.append(")");
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, st, null);
		}
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			st = conn.createStatement();
			String sql = "DELETE FROM product WHERE id = " + id;
			System.out.println(sql.toString());
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, st, null);
		}
	}

	@Override
	public void update(Product pro) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			st = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE product SET ");
			sql.append("productName = '").append(pro.getProductName()).append("',");
			sql.append("brand = '").append(pro.getBrand()).append("',");
			sql.append("supplier = '").append(pro.getSupplier()).append("',");
			sql.append("salePrice = ").append(pro.getSalePrice()).append(",");
			sql.append("costPrice = ").append(pro.getCostPrice()).append(",");
			sql.append("cutoff = ").append(pro.getCutoff()).append(",");
			sql.append("dirId = ").append(pro.getDirId()).append(" ");
			sql.append("WHERE id = ").append(pro.getId());
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, st, null);
		}
	}

	@Override
	public Product get(Long id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			st = conn.createStatement();
			String sql = "SELECT * FROM product WHERE id = " + id;
			rs = st.executeQuery(sql);
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
				System.out.println(pro);
				return pro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.INSTANCE.close(conn, st, rs);
		}
		return null;
	}

	@Override
	public List<Product> list() {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.INSTANCE.getConn();
			st = conn.createStatement();
			String sql = "SELECT * FROM product";
			rs = st.executeQuery(sql);

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
			JdbcUtil.INSTANCE.close(conn, st, rs);
		}
		return null;
	}

}
