package basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	private Connection getConnection() {
		String url = "jdbc:mysql://localhost:8888/handvis";
		String id = "study";
		String password = "study";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean addUser(User user) throws SQLException {
		String sql = "INSERT INTO users values(?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());

			int result = pstmt.executeUpdate();
			return result != 0;
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "select * from users where userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			User user = new User(
					rs.getString("userId"),
					rs.getString("password")
					);
			return user;
			
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}

	public void removeUser(String userId) throws SQLException {
		String sql = "delete from users where userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			pstmt.executeUpdate();
			
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public boolean login(String userId, String password) throws SQLException {
		User user = new User(userId, password);
		User checkUser = findByUserId(userId);
		return user.equals(checkUser);										//오버라이딩한 equals 함수로 비교
	}
}
