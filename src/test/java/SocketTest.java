import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import basic.Arduino;

public class SocketTest {
	public static String IP = "165.246.228.110";
	public static int port = 7777;
	
	@Test
	public void SendMessageTest() throws IOException {
		Arduino ardu = new Arduino();
		
		String message = "hello world!";
		String ret = ardu.sendMessage(IP, port, message);
		assertEquals(message, ret);
	}

}
