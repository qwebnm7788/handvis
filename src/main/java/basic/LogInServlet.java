package basic;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * LogInServlet.class
 * 
 * 웹페이지 로그인 기능
 */
@WebServlet("/users/login")
public class LogInServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LogInServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId= request.getParameter("userId");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		try {
			if(userDao.login(userId, password)) {					//로그인 성공
				logger.debug("{} login success", userId);
				session.setAttribute("userId", userId);
				
				DeviceDao deviceDao = new DeviceDao();
				Device[] devices = deviceDao.ListDeviceInfo(userId);
				
				Arrays.sort(devices);
				
				session.setAttribute("devices", devices);
				response.sendRedirect("/");
			}else {
				logger.debug("{} login fail", userId);
				response.sendRedirect("/login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
