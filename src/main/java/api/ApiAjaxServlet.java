package api;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

import basic.DeviceDao;

/*
 * ApiAjaxServlet.class
 * 
 * ajax 요청시 현재 유저의 손동작 정보를 반환해준다.
 */
@WebServlet("/api/ajax")
public class ApiAjaxServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiAjaxServlet.class);
	private Connection getConnection() {
		String url = "jdbc:mysql://localhost:8888/handvis?characterEncoding=UTF-8&autoReconnect=true&useSSL=false";
		String id = "study";
		String password = "study";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Connection conn = getConnection();
		String sql = "SELECT currentAction from current where idx=0";
		Statement stmt = null;
		ResultSet rs = null;
		String answer =null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int currentAction = 0;
			if(rs.next()) {
				currentAction = rs.getInt("currentAction");
			}
			
			sql = "SELECT name, state from iot where finger_idx=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentAction);
			
			String name = null;
			int state = 0;
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
				state = rs.getInt("state");
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
			
			if(name == null) {
				 answer = "{\"value:\"No Action\"}";
			}else {
				answer =  "{ \"value\": " + "\"" + name  + (state == 1 ? " ON" : " OFF") + "\"}";
			}
		} catch (SQLException e) {
			logger.debug("ERROR " + e.getMessage());
		} 
		//CORS를 위한 헤더 설정
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setContentType("application/json");
		//logger.debug(answer);
		out.print(answer);
		out.flush();
	}
}
