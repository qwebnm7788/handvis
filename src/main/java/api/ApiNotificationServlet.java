package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import basic.Notification;
import basic.NotificationDao;
import basic.User;

/*
 * ApiNotificationServlet.class
 * 
 * 공지사항 정보를 반환한다.
 *	반환형식 json
 *{
 *		title: 제목
 *		content: 내용
 *		date: 날짜
 *}
 */

@WebServlet("/api/notification")
public class ApiNotificationServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiNotificationServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		StringBuilder result = new StringBuilder();
		
		result.append("{\"notification\":[");
		NotificationDao notificationDao = new NotificationDao();
		Notification[] notificationList = notificationDao.ListNotification();
		for(Notification notification : notificationList) {
			result.append(gson.toJson(notification, Notification.class));
			result.append(',');
		}
		
		result.deleteCharAt(result.length() - 1);
		result.append("]}");
		
		logger.debug(result.toString());

		out.write(result.toString());
	}
}
