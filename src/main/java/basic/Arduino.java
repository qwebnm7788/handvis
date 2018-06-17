package basic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Arduino.class
 * 
 * 아두이노와 소켓 통신을 위한 클래스
 * 
 */
public class Arduino {
	private static final Logger logger = LoggerFactory.getLogger(Arduino.class);
	private Socket sock;
	
	//IP, port 를 이용하여 소켓 통신을 수행한다.
	private void Connection(String IP, int port) {
		sock = new Socket();
		InetSocketAddress ipep = new InetSocketAddress(IP, port);
		try {
			sock.connect(ipep, port);
			logger.debug("success in arduino connection");
		} catch(SocketTimeoutException e) {
			logger.debug("socket timeout");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//소켓을 통해 주어진 message를 전송하며, 그에 따른 응답을 반환한다.
	public String sendMessage(String IP, int port, String message) throws IOException {
		Connection(IP, port);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out = new PrintWriter(sock.getOutputStream());
		
		//메세지 전송
		out.print(message);
		out.flush();
		
		DisConnection();
		return "OK";
	}
	
	//연결종료
	private void DisConnection() {
		try {
			sock.close();
			logger.debug("socket disconnection()");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
