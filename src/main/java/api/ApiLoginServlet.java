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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import basic.User;
import basic.UserDao;

@WebServlet("/api/users/login")
public class ApiLoginServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiLoginServlet.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String jsonObject = gson.toJson(new User("jaewon", "password"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
	}
	
	//안드로이드에서의 로그인 요청을 받아 로그인을 시도한다.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = readBody(request);
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		//response.setContentType("application/json");
		
		JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);				//json 형태의 문자열을 이용해 객체 형태로 뽑아낸다.
		
		UserDao userDao = new UserDao();
		try {
			if(userDao.login(jsonObject.get("userId").getAsString(), jsonObject.get("password").getAsString())) {
				out.print("SUCCESS");
			}else {
				out.println("FAIL");
			}
		} catch (SQLException e) {
			logger.debug("ApiLoginServlet userDao error");
			e.printStackTrace();
		}
	}
	
	//http body에 있는 정보를 json 형태의 문자열로 변환한다.
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
