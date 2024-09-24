package btltw_04.vnitstar.Controllers;

import java.io.IOException;
import java.util.Random;

import btltw_04.vnitstar.Models.UserModel;
import btltw_04.vnitstar.Services.IUserService;
import btltw_04.vnitstar.Services.Impl.UserServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/forgotpassword" })

public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImplement();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String alertMsg = "";
		if (service.checkExistEmail(email)) {
			Random random = new Random();
			int randomFourDigitNumber = 1000 + random.nextInt(9000);
			String NewPassword = String.format("%04d", randomFourDigitNumber);
			alertMsg = "Password đã gửi về email: " + NewPassword;
			service.updatePassword(email, NewPassword);

		} else {
			alertMsg = "Tài khoản hoặc Email không tồn tại";
		}
		req.setAttribute("alert", alertMsg);
		req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);

	}

}
