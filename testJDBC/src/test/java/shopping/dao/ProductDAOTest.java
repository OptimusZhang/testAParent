package shopping.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import shopping.dao.impl.ProductDAOImpl;
import shopping.domain.Product;

public class ProductDAOTest {

	private IProductDAO dao = new ProductDAOImpl();
	
	@Test
	public void testSave() {
		
		Product pro = new Product();
		pro.setProductName("iphone7S");
		pro.setBrand("Apple");
		pro.setSupplier("苹果公司");
		pro.setSalePrice(new BigDecimal("7000"));
		pro.setCostPrice(new BigDecimal("2000"));
		pro.setCutoff(0.9);
		pro.setDirId(5L);
		dao.save(pro);
	}

	@Test
	public void testDelete() {
		Long id = 8L;
		dao.delete(id);
	}

	@Test
	public void testUpdate() {
		Product pro = new Product();
		pro.setId(8L);
		pro.setProductName("iphone6S");
		pro.setBrand("Apple2");
		pro.setSupplier("苹果公司2");
		pro.setSalePrice(new BigDecimal("6000"));
		pro.setCostPrice(new BigDecimal("1000"));
		pro.setCutoff(0.8);
		pro.setDirId(5L);
		dao.update(pro);
	}

	@Test
	public void testGet() {
		Product product = dao.get(3L);
		System.out.println(product);
	}

	@Test
	public void testList() {
		List<Product> list = dao.list();
		for (Product product : list) {
			System.out.println(product);
		}
	}

}
