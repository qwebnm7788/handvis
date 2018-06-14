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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basic.User;
import basic.UserDao;

/*
 * ApiRegisterServlet.class
 * 안드로이드용 회원가입 API
 * 
 * 성공시 1, 실패시 0 반환
 */

@WebServlet("/api/register")
public class ApiRegisterServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(ApiRegisterServlet.class);
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		String userId = (String)request.getAttribute("userId");
		String password = (String)request.getAttribute("password");
		
		UserDao userDao = new UserDao();
		boolean result = false;
		try {
			result = userDao.addUser(new User(userId, password));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result) {
			out.print("{perm:1}");
		}else {
			out.print("{perm:0}");	
		}
	}
	
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
