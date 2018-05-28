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
	public static JsonObject currentAction = null;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = ApiLoginServlet.readBody(request);
		Gson gson = new Gson();
		currentAction = gson.fromJson(jsonData, JsonObject.class);
		response.setStatus(200);
	}
}
