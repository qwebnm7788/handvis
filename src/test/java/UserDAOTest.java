import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import basic.User;
import basic.UserDao;

public class UserDAOTest {
	public static User USER_TEST = new User("dummy", "password");
	private UserDao userDao;
	
	@Before
	public void setup() throws SQLException {
		userDao = new UserDao();
		userDao.removeUser(USER_TEST.getUserId());
	}
	
	@Test
	public void loginTest() throws Exception {
		userDao = new UserDao();
		userDao.addUser(USER_TEST);
		assertTrue(userDao.login("dummy", "password"));
	}
	
	@Test
	public void connection() {
		Connection conn = userDao.getConnection();
		assertNotNull(conn);
	}

	@Test
	public void crud() throws Exception {
		User dbUser = userDao.findByUserId(USER_TEST.getUserId());
		assertEquals(USER_TEST, dbUser);
	}
	
	@Test
	public void 존재하지_않는_사용자_조회() throws Exception {
		User dbUser = userDao.findByUserId(USER_TEST.getUserId());
		assertNull(dbUser);
	}
}
