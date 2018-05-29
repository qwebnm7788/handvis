package basic;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/users/register")
public class RegisterUserServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(RegisterUserServlet.class);
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDao();
		User newUser = new User(userId, password);
		try {
			if(userDao.addUser(newUser)) {
				session.setAttribute("userId", userId);
				response.sendRedirect("/index.jsp");
			}else {
				response.sendRedirect("/register.jsp");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
