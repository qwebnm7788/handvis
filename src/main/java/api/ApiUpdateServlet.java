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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		/*
		User user = (User)session.getAttribute("user");
		if(user == null) {			//로그인 되지 않은 경우
			
		}
		*/
		
		String action = (String)request.getAttribute("action");
		int number = (int)request.getAttribute("number");
		
	}
}
