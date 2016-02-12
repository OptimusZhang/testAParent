package shopping.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import shopping.dao.impl.Product4PSDAOImpl;
import shopping.domain.Product;

public class Product4PSDAOTest {

	private IProductDAO dao4Ps = new Product4PSDAOImpl();
	
	@Test
	public void testSave() {
		
		Product pro = new Product();
		pro.setProductName("iphone8S");
		pro.setBrand("Apple");
		pro.setSupplier("苹果公司");
		pro.setSalePrice(new BigDecimal("8000"));
		pro.setCostPrice(new BigDecimal("3000"));
		pro.setCutoff(0.9);
		pro.setDirId(5L);
		dao4Ps.save(pro);
	}

	@Test
	public void testDelete() {
		Long id = 10L;
		dao4Ps.delete(id);
	}

	@Test
	public void testUpdate() {
		Product pro = new Product();
		pro.setId(10L);
		pro.setProductName("iphone5");
		pro.setBrand("Apple2");
		pro.setSupplier("苹果公司2");
		pro.setSalePrice(new BigDecimal("4500"));
		pro.setCostPrice(new BigDecimal("750"));
		pro.setCutoff(0.5);
		pro.setDirId(5L);
		dao4Ps.update(pro);
	}

	@Test
	public void testGet() {
		Product product = dao4Ps.get(3L);
		System.out.println(product);
	}

	@Test
	public void testList() {
		List<Product> list = dao4Ps.list();
		for (Product product : list) {
			System.out.println(product);
		}
	}

}
