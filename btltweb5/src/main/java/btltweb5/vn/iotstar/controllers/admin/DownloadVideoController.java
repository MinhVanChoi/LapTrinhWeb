package btltweb5.vn.iotstar.controllers.admin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import btltweb5.vn.iotstar.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/video") // ?fname=abc.mp4
public class DownloadVideoController  extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        System.out.println("run");
	        String fileName = req.getParameter("fname");
	        File file = new File(getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY + "/" + fileName);
	        System.out.println(file.getPath());
	        
	        if (file.exists()) {
	            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
	        }
	           
	    }
	
}
