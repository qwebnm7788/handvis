package api;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basic.User;

//한 유저가 특정 모듈의 현재 상태를 갱신한다. -> 어떤 동작을 취할지, 어떤 번호의 모듈을 건드릴지를 함께 보내주어야 한다.
@WebServlet("/api/update")
public class ApiUpdateServlet extends HttpServlet {
	/*
	 * 유저가 특정 모듈의 현재 설정 상태를 갱신한다.
	 * 어떤 모듈을 어떤 손동작으로 변경할 것인지를 전달받는다.
	 * {
	 * 		iotNumber: 모듈 번호
	 * 		action: 변경할 손동작 번호
	 * }
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		if(userId == null) {					//로그인 되지 않은 경우
			return;
		}
		int iotNumber = (int) request.getAttribute("iotNumber");
		int action = (int) request.getAttribute("action");

		
	}
}
