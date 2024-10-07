package btltweb5.vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import btltweb5.vn.iotstar.entity.Category;
import btltweb5.vn.iotstar.dao.ICategoryDao;
import btltweb5.vn.iotstar.dao.impl.CategoryDao;
import btltweb5.vn.iotstar.services.ICategoryService;
import btltweb5.vn.iotstar.services.impl.CategoryService;
import btltweb5.vn.iotstar.utils.Constant;




@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
@WebServlet(urlPatterns =  {"/admin/categories","/admin/category/add","/admin/category/insert","/admin/category/edit","/admin/category/update"
		,"/admin/category/delete","/admin/category/search"})
public class CategoryController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		
		if(url.contains("categories"))
		{
			List<Category> list = cateService.findAll();
			req.setAttribute("listcategory", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		
		else if (url.contains("add"))
		{
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		}
		
		else if (url.contains("edit"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

		}
		else if (url.contains("delete"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
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
			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);
			category.setImages(images);
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
		else if(url.contains("insert"))
		{
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss= Integer.parseInt(status);
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			String fname="";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
		    File uploadDir = new File(uploadPath);
			if(!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try {
				System.out.println("2");
				Part part = req.getPart("images");
				System.out.println("3");
				if(part.getSize()>0)
				{
					System.out.println("1");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring (index+1);
					fname = System.currentTimeMillis()+"."+ext;
					System.out.println(filename);
					//upload
	                part.write(uploadPath + File.separator + fname);
					// ghi tên file
					category.setImages(fname);
				}
				else
				{
					category.setImages("avata.png");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
		else if(url.contains("update"))
		{
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss= Integer.parseInt(status);
			String images = "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/03/anh-meme-hai-1.jpg";
			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
	}
	

}
