package btltw_04.vnitstar.Controllers;
import java.io.File;
import java.io.IOException;

import btltw_04.vnitstar.Models.UserModel;
import btltw_04.vnitstar.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			UserModel u = (UserModel) session.getAttribute("account");
			session.setAttribute("account.fullname", u.getFullName());
			req.setAttribute("username", u.getUserName());
			req.setAttribute("password", u.getPassWord());
			req.setAttribute("email", u.getEmail());
			req.setAttribute("fullname", u.getFullName());
			req.setAttribute("phone", u.getPhone());
            req.setAttribute("image", u.getAvatar());
            
		}
		req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);

	}
	
}