package shopping.dao;
import java.util.List;

import shopping.domain.Product;

public interface IProductDAO {
	
	void save(Product pro);
	
	void delete(Long id);
	
	void update(Product pro);
	
	Product get(Long id);
	
	List<Product> list();
	

}
