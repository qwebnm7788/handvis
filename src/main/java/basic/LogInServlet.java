package basic;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/users/login")
public class LogInServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LogInServlet.class);
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= request.getParameter("userId");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		try {
			if(userDao.login(userId, password)) {
				response.sendRedirect("/");
			}else {
				response.sendRedirect("/login.jsp");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
