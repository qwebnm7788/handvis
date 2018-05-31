import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Test;


public class DeviceUpdateTest {
	private String url = "jdbc:mysql://localhost:8888/handvis";
	private String id = "study";
	private String password = "study";
	
	
	@Test
	public void updateTest() throws Exception {
		String userId = "jaewon";
		int idx = 1;
		String name = "bathroom";
		int finger_idx = 1;
		String background_image = "/android/somewhere";
		int state = 1;
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, id, password);
		String sql = "UPDATE iot set name=?, finger_idx=?, background_image=?, state=? where idx=? and userId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);
		pstmt.setInt(2, finger_idx);
		pstmt.setString(3, background_image);
		pstmt.setInt(4, state);
		pstmt.setInt(5, idx);
		pstmt.setString(6, userId);
		
		int result = pstmt.executeUpdate();
		assertNotEquals(0, result);
	}
	
}
