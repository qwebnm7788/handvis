package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import basic.Device;
import basic.DeviceDao;

/*
 * ApiListServlet.class
 * 주어진 userId에 등록된 Device 정보를 반환한다
 * 
 * 안드로이드에서는 http 패킷 내부에 값이 들어온다.
 * (userId = ?  & password = ?)
 * 반환값
 * [{device}, {device}, {device}, ...]
 */

@WebServlet("/api/list")
public class ApiListServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiListServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		logger.error("Android Login Session ID {}", request.getRequestedSessionId());
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();

		String userId = (String)session.getAttribute("userId");
		//String userId = request.getParameter("userId");
		StringBuilder result = new StringBuilder();
		
		result.append("{\"device\":[");
		DeviceDao deviceDao = new DeviceDao();
		Device[] deviceList = deviceDao.ListDeviceInfo(userId);
		for(Device device : deviceList) {
			result.append(gson.toJson(device, Device.class));
			result.append(',');																	//각 json 사이에 ','가 들어가야 한다.
		}
		
		result.deleteCharAt(result.length() - 1);										//맨 마지막에는 ','를 제거
		result.append("]}");

		out.write(result.toString());
	}
}
