package api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import basic.Arduino;
import basic.DeviceDao;

@WebServlet("/api/recognition")
public class ApiRecongnitionServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiRecongnitionServlet.class);
	
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
	
	/*
	 * ajax 요청을 받는 서블릿
	 * 현재 유저의 상태를 세션에 currentAction 속성에 저장한다.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Recongnition request");
		request.setCharacterEncoding("utf-8");
		String jsonData = readBody(request);
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonData);
		
		String userId = element.getAsJsonObject().get("userId").getAsString();
		int currentAction = element.getAsJsonObject().get("currentAction").getAsInt();
		
		HttpSession session = request.getSession();
		session.setAttribute("currentAction", currentAction);
		logger.debug("recognition " + userId + " " + currentAction);
		Connection conn = getConnection();
		
		String sql = "SELECT * from iot where finger_idx=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int current = 0, idx = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentAction);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				current = rs.getInt("state");
				idx = rs.getInt("idx");
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			current = (current == 0 ? 1 : 0);
			sql = "UPDATE iot set state=? where finger_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, current);
			pstmt.setInt(2, currentAction);
			pstmt.executeUpdate();
			
			Arduino arduino = new Arduino();
			String IP = "165.246.223.149";
			int port = 5000;
			String msg = arduino.sendMessage(IP, port, (current == 0 ? String.valueOf(idx * 4) : String.valueOf(idx)));	
			
		} catch(SQLException e) {
			logger.debug(e.getMessage());
		}
			
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			logger.debug(e.getMessage());
		}
		
		response.setStatus(200);
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