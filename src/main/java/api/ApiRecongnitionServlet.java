package api;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/api/recognition")
public class ApiRecongnitionServlet extends HttpServlet {
	/*
	 * ajax 요청을 받는 서블릿
	 * 현재 유저의 상태를 세션에 currentAction 속성에 저장한다.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = ApiLoginServlet.readBody(request);
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		session.setAttribute("currentAction", gson.fromJson(jsonData, JsonObject.class));
		response.setStatus(200);
	}
}
