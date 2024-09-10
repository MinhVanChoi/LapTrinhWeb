package van_02.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/dbtest")
public class Test extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/van_02?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (connection != null) {
                response.getWriter().println("Database connection established successfully.");
            } else {
                response.getWriter().println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            throw new ServletException("Database connection failed", e);
        }
    }
}
