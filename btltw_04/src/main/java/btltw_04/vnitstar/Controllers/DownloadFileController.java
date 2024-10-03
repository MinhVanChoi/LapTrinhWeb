package btltw_04.vnitstar.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import btltw_04.vnitstar.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadFileController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("run");
		String fileName = req.getParameter("fname");
		File file = new File( getServletContext().getRealPath("")+ Constant.UPLOAD_DIRECTORY + "/" + fileName);
		System.out.println(file.getPath());
		resp.setContentType("image/jpeg");
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		}
	}
}