package api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import basic.Device;
import basic.DeviceDao;
import basic.User;
import basic.UserDao;

/*
 * ApiLoginSerlvet.class
 * 
 * 안드로이드 로그인 기능
 * 
 * 입력: (userId=?&password=?)
 * 반환: 성공시 {perm: 1}, 실패시 {perm: 0}
 */
@WebServlet("/api/users/login")
public class ApiLoginServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiLoginServlet.class);
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug(request.getRemoteAddr());
		response.setContentType("application/json");					//PrintWriter 객체 생성 전에 설정해주어야 한다.
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		User user = new User(userId, password);
		UserDao userDao = new UserDao();
		
		try {
			if(userDao.login(user.getUserId(), user.getPassword())) {
				logger.debug("Android {} login success", userId);
				
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getUserId());
				
				DeviceDao deviceDao = new DeviceDao();
				Device[] devices = deviceDao.ListDeviceInfo(userId);
				
				Arrays.sort(devices);
				
				session.setAttribute("devices", devices);
				out.print("{perm:1}");
			}else {
				logger.debug("Android {} login fail", userId);
				out.print("{perm:0}");
			}
		} catch (SQLException e) {
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
