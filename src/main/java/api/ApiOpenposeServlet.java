package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/api/openpose")
public class ApiOpenposeServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiOpenposeServlet.class);
	private Socket sock;
	
	private void Connection(String IP, int port) {
		sock = new Socket();
		InetSocketAddress ipep = new InetSocketAddress(IP, port);
		try {
			sock.connect(ipep, port);
			logger.debug("success in openpose connection");
		} catch(SocketTimeoutException e) {
			logger.error("socket timeout");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void DisConnection() {
		try {
			sock.close();
			logger.debug("socket disconnection()");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String IP, int port, String message) throws IOException {
		Connection(IP, port);
		
		PrintWriter out = new PrintWriter(sock.getOutputStream());
		out.print(message);
		out.flush();
		
		logger.debug("sendMsg : {}", message);
		DisConnection();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("OPENPOSE POST! {}", request.getRemoteAddr());
		String ipAddr = request.getParameter("addr");
		int type = Integer.parseInt(request.getParameter("state"));
		sendMessage("165.246.43.31", 5000, type + " " + ipAddr);
	}
}
