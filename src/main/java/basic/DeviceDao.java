package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceDao {
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
	
	public boolean UpdateDevice(String userId, Device deviceInfo) throws SQLException {
		Connection conn = getConnection();
		String sql = "UPDATE iot set name=?, finger_idx=?, background_image=?, state=? where idx=? and userId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deviceInfo.getName());
			pstmt.setInt(2, deviceInfo.getFingerNumber());
			pstmt.setString(3, deviceInfo.getBackground_image());
			pstmt.setInt(4, deviceInfo.getState());
			pstmt.setInt(5, deviceInfo.getDeviceNumber());
			pstmt.setString(6, userId);
			
			int result = pstmt.executeUpdate();
			return result != 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public Device[] ListDeviceInfo(String userId) {
		Connection conn = getConnection();
		String sql = "SELECT idx, name, finger_idx, background_image, state from iot where userId=?";
		String name, background_image;
		int idx, finger_idx, state;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();

			rs.last();
			int count = rs.getRow();				//row 개수 확인을 위해 커서를 옮긴 후 다시 되돌린다.
			rs.beforeFirst();
			
			Device[] result = new Device[count];
			int elem = 0;
			while(rs.next()) {
				idx = rs.getInt("idx");
				finger_idx = rs.getInt("finger_idx");
				state = rs.getInt("state");
				name = rs.getString("name");
				background_image = rs.getString("background_image");
				
				result[elem++] = new Device(idx, finger_idx, name, background_image, state);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
