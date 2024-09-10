package van_02.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import van_02.DAO.implement.userDAOimplement;
import van_02.Model.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_USERNAME = "username";
	
	public class Constant {
	    public static final String COOKIE_REMEMBER = "username";
	}
	
	public void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60); // Set the cookie to expire in 30 minutes
        response.addCookie(cookie);
    }
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			response.sendRedirect(request.getContextPath()+ "/waiting");
			return;
			}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		for (Cookie cookie : cookies) {
		if (cookie.getName().equals("username")) {
		session = request.getSession(true);
		session.setAttribute("username", cookie.getValue());
		response.sendRedirect(request.getContextPath()+ "/waiting");
		return;
		}
		}
		}
		request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isRememberMe = false;
		System.out.println(username+"+"+password);
		String remember = request.getParameter("remember");
		
		if("on".equals(remember)){
		isRememberMe = true;
		}
		String alertMsg="";
		if(username.isEmpty() || password.isEmpty()){
			alertMsg = "Tài khoản hoặc mật khẩu không được trống";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
			return;
			}
			userDAOimplement service = new userDAOimplement();
			UserModel user = service.login(username, password);
			if(user!=null){	
				HttpSession session = request.getSession(true);
				session.setAttribute("account", user);

				if(isRememberMe){
					saveRememberMe(response, username);
				}
				response.sendRedirect(request.getContextPath()+"/waiting");
				}else{
				System.out.println(service.login(username,password));
				alertMsg = "Tài khoản hoặc mật khẩu không đúng";
				request.setAttribute("alert", alertMsg);
				request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
				}
	}
	  public static void main(String[] args) {
		  
	  }
 
	  }
	


