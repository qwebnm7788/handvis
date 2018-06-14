package api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import basic.User;
import basic.UserDao;

@WebServlet("/api/users/login")
public class ApiLoginServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiLoginServlet.class);
	/*
	 * 안드로이드에서의 로그인 요청을 받아 로그인을 시도한다.
	 * 요청 정보는 http 패킷 내부에 다음과 같은 값으로 전송된다.
	 * (userId = ? & password = ?)
	 * 로그인 성공 시 1를, 실패 시 0을 전달한다.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		User user = new User(userId, password);
		UserDao userDao = new UserDao();
		
		logger.debug("LOGIN attempted");
		try {
			if(userDao.login(user.getUserId(), user.getPassword())) {
				logger.debug("login success : " + userId + " " + password);
				out.print("{perm:1}");
			}else {
				logger.debug("login failed : " + userId + password);
				out.print("{perm:0}");
			}
		} catch (SQLException e) {
			logger.debug("ApiLoginServlet userDao error");
			e.printStackTrace();
		}
	}

	/*
	 * http body에 있는 정보를 json 형태의 문자열로 변환한다. 
	 */
	public static String readBody(HttpServletRequest request) throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String buffer;
		while((buffer = input.readLine()) != null) {
			buffer.trim();
			builder.append(buffer);
		}
		return builder.toString();
	}
}
