package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basic.User;
import basic.UserDao;

/*
 * ApiRegisterServlet.class
 * 안드로이드용 회원가입 API
 * 
 * 성공시 1, 실패시 0 반환
 */
public class ApiRegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			out.print("1");
		}else {
			out.print("0");	
		}
	}
}
