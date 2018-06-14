package basic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationDao {
	private static final Logger logger = LoggerFactory.getLogger(NotificationDao.class);
	private Connection getConnection() {
		String url = "jdbc:mysql://localhost:8888/handvis?characterEncoding=UTF-8";
		String id = "study";
		String password = "study";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Notification[] ListNotification() {
		Connection conn = getConnection();
		String sql = "SELECT idx, title, content, date from notification";
		int idx;
		String title, content;
		Date datetime;
		
		ResultSet rs = null;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			rs.last();
			int count = rs.getRow();
			rs.beforeFirst();
			
			Notification[] result = new Notification[count];
			int elem = 0;
			while(rs.next()) {
				idx = rs.getInt("idx");
				title = rs.getString("title");
				content = rs.getString("content");
				datetime = rs.getDate("date");
				logger.debug(idx + " " + title + " " + content + " " + datetime);
				result[elem++] = new Notification(idx, title, content, datetime);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
	
