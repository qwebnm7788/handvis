package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * DBConnection.class
 * 
 * Connection 객체를 한번 생성 후 반복해서 재 사용한다.
 */
public class DBConnection extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
	private ServletContext sc;

}
