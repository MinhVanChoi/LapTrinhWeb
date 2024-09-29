package btltw_04.vnitstar.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import btltw_04.vnitstar.Models.UserModel;
import btltw_04.vnitstar.Services.IUserService;
import btltw_04.vnitstar.Services.Impl.UserServiceImplement;
import btltw_04.vnitstar.utils.Constant;

@WebServlet(name = "MultiPartServlet", urlPatterns = { "/multiPartServlet" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class UploadfileController extends HttpServlet {
	IUserService service = new UserServiceImplement();
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UploadfileController.class.getName());

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1).replace("\"", "");
            }
        }
        return Constant.DEFAULT_FILENAME;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến trang biểu mẫu tải lên
       request.getRequestDispatcher("/views/uploadfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists() && !uploadDir.mkdir()) {
            logger.log(Level.SEVERE, "Failed to create upload directory: " + uploadPath);
            request.setAttribute("message", "Failed to create upload directory.");
            getServletContext().getRequestDispatcher("/views/user/home.jsp").forward(request, response);
            return;
        }

        String fileName = "";
        HttpSession session = request.getSession();
		
		
        
        try {
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                // Additional security checks can be implemented here
                part.write(uploadPath + File.separator + fileName);
            }
            
            if (session != null && session.getAttribute("account") != null) {
    			UserModel u = (UserModel) session.getAttribute("account");
    			String fullname = request.getParameter("fullname");
    			String phone = request.getParameter("phone");
              //  String fullPath = uploadPath + File.separator + fileName;
                service.updateProfile(fullname, phone, fileName, u.getUserName()); 
    		}
            request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException occurred: " + e.getMessage(), e);
            request.setAttribute("message", "There was an error during file upload: " + e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
        }

        getServletContext().getRequestDispatcher("/views/user/home.jsp").forward(request, response);
    }
}
