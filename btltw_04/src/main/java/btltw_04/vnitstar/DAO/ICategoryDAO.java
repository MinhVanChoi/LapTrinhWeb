package btltw_04.vnitstar.DAO;
import java.util.List;

import btltw_04.vnitstar.Models.*;
public interface ICategoryDAO {

	List<CategoryModel> findAll();
	CategoryModel findById(int id); // tim category, -> update delete
	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
	List<CategoryModel> findName(String keyword);
	void softDelete(CategoryModel category);
	
	
}
