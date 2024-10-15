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
import btltweb5.vn.iotstar.entity.Video;
import btltweb5.vn.iotstar.dao.IVideoDao;
import btltweb5.vn.iotstar.dao.impl.VideoDao;
import btltweb5.vn.iotstar.services.IVideoService;
import btltweb5.vn.iotstar.services.impl.VideoService;
import btltweb5.vn.iotstar.utils.Constant;
import btltweb5.vn.iotstar.services.ICategoryService;
import btltweb5.vn.iotstar.services.impl.CategoryService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit",
		"/admin/video/update", "/admin/video/delete", "/admin/video/search" })

public class VideoConytoller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IVideoService vidService = new VideoService();
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("videos")) {
			List<Video> list = vidService.findAll();
			req.setAttribute("listVideos", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}

		else if (url.contains("add")) {
			List<Category> listCate = cateService.findAll();
			req.setAttribute("listcate", listCate);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

		}

		else if (url.contains("edit")) {
			String id = req.getParameter("id");
			Video video = vidService.findById(id);
			List<Category> listCate = cateService.findAll();
			req.setAttribute("listcate", listCate);
			req.setAttribute("vid", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);

		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				vidService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("search"))
		{
			String name = req.getParameter("name");
			if (name == null || name.trim().isEmpty()) {
		        resp.sendRedirect(req.getContextPath() + "/admin/videos");
		        return; 
		    }
			List<Video> listVi = vidService.findByVideoname(name);
			req.setAttribute("listVideos", listVi);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);

		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("videos")) {
			
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
			
			
			
		} else if (url.contains("insert")) {
		 	String videoIdStr = req.getParameter("videoId"); // Video ID
		    String title = req.getParameter("title"); // Video Title
		    String description = req.getParameter("description"); // Video Description
		    String viewsStr = req.getParameter("views"); // Number of views
		    String categoryIdStr = req.getParameter("categoryId"); // Category ID
		    String statusStr = req.getParameter("status"); // Status (active/inactive)
		    
		    int views = Integer.parseInt(viewsStr); // Convert views to int
		    Category cate = cateService.findById(Integer.parseInt(categoryIdStr)); // Convert category ID to int
		    boolean active = "1".equals(statusStr); // Convert status to boolean

			
		    Video video = new Video();
		    video.setVideoId(videoIdStr);
		    video.setTitle(title);
		    video.setDescription(description);
		    video.setViews(views);
		    video.setActive(active);
		    video.setCategory(cate); // Assuming you have a constructor for Category
		    
		    
			String fname = "";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				System.out.println("2");
				Part part = req.getPart("poster");
				System.out.println("3");
				if (part.getSize() > 0) {
					System.out.println("1");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					System.out.println(filename);
					// upload
					part.write(uploadPath + File.separator + fname);
					// ghi tên file
					video.setPoster(fname);
				} else {
					video.setPoster("video.mp4");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vidService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");

			
		} else if (url.contains("update")) {
			
			String videoIdStr = req.getParameter("videoId"); // Video ID
		    String title = req.getParameter("title"); // Video Title
		    String description = req.getParameter("description"); // Video Description
		    String viewsStr = req.getParameter("views"); // Number of views
		    String categoryIdStr = req.getParameter("categoryId"); // Category ID
		    String statusStr = req.getParameter("status"); // Status (active/inactive)
		    
		    int views = Integer.parseInt(viewsStr); // Convert views to int
		    Category cate = cateService.findById(Integer.parseInt(categoryIdStr)); // Convert category ID to int
		    boolean active = "1".equals(statusStr); // Convert status to boolean

			
		    Video video = new Video();
		    video.setVideoId(videoIdStr);
		    video.setTitle(title);
		    video.setDescription(description);
		    video.setViews(views);
		    video.setActive(active);
		    video.setCategory(cate); 
		    
			String fname = "";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				System.out.println("2");
				Part part = req.getPart("poster");
				System.out.println("3");
				if (part.getSize() > 0) {
					System.out.println("1");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					System.out.println(filename);
					// upload
					part.write(uploadPath + File.separator + fname);
					// ghi tên file
					video.setPoster(fname);
				} else {
					video.setPoster("video.mp4");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vidService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		
	}
}


