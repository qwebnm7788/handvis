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

public class Arduino {
	private static final Logger logger = LoggerFactory.getLogger(Arduino.class);
	private Socket sock;
	private void Connection(String IP, int port) {
		sock = new Socket();
		InetSocketAddress ipep = new InetSocketAddress(IP, port);
		try {
			sock.connect(ipep, 5000);
			logger.debug("success in arduino connection");
		} catch(SocketTimeoutException e) {
			logger.debug("socket timeout");
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String sendMessage(String IP, int port, String message) throws IOException {
		Connection(IP, port);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out = new PrintWriter(sock.getOutputStream());
		
		//메세지 전송
		out.print(message);
		out.flush();
		
		//응답  출력
		String response = in.readLine();
		DisConnection();
		
		return response;
	}
	
	private void DisConnection() {
		try {
			sock.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
