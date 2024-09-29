package btltw_04.vnitstar.Controllers;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.List;
import java.util.Locale.Category;

import btltw_04.vnitstar.Models.CategoryModel;
import btltw_04.vnitstar.Services.ICategoryService;
import btltw_04.vnitstar.Services.Impl.CategoryServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =  {"/admin/categories"})
public class CategoryController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImplement();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("categories"))
		{
			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcategory", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/category/edit"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/edit.jsp").forward(req, resp);

		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("categories"))
		{
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter(req.getParameter("categoryname"));
			int status = Integer.parseInt(req.getParameter("status"));
			String images = "https://simg.zalopay.com.vn/zlp-website/assets/phim_kinh_di_my_hay_nhat_The_Nun_6c798b2df8.jpg";
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);
			category.setImages(images);
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
	}
	

}
