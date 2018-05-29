import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import basic.Arduino;

public class ArduinoUpdateTest {

	//IP에 연결된 장비의 number번 모듈에 대해 action 행동을 취한다.
	@Test
	public void updateTest() throws IOException {
		String IP = "218.55.247.78";
		int port = 5000;
		String message = "0";					//number, action 형태로 구성되어야 한다.

		Arduino arduino = new Arduino();
		arduino.sendMessage(IP, port, message);
	}
}
