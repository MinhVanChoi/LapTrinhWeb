package btltw_04.vnitstar.Services.Impl;

import java.util.List;

import btltw_04.vnitstar.DAO.ICategoryDAO;
import btltw_04.vnitstar.Models.CategoryModel;
import btltw_04.vnitstar.Services.ICategoryService;
import btltw_04.vnitstar.DAO.Implement.CategoryDAOImplement;

public class CategoryServiceImplement implements ICategoryService {

	 ICategoryDAO  cateDAO =  new CategoryDAOImplement();

	@Override
	public List<CategoryModel> findAll() {
		return cateDAO.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		return cateDAO.findById(id);
	}

	@Override
	public void insert(CategoryModel category) {
		CategoryModel cate = new CategoryModel();
		cate = cateDAO.findById(cate.getCategoryid());
		if(cate != null)
		{
			cateDAO.insert(category);
		}
		
		
	}

	@Override
	public void update(CategoryModel category) {
		CategoryModel cate = new CategoryModel();
		cate = cateDAO.findById(cate.getCategoryid());
		if(cate != null)
		{
			cateDAO.update(category);
		}
		
		
		
	}

	@Override
	public void delete(int id) {
		CategoryModel cate = new CategoryModel();
		cate = cateDAO.findById(cate.getCategoryid());
		if(cate != null)
		{
			cateDAO.delete(id);
		}
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		return cateDAO.findName(keyword);
	}

	@Override
	public void softDelete(CategoryModel category) {
			cateDAO.softDelete(category);
	}

	 
}
