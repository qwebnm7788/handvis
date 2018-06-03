package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
 * 반환값
 * [{device}, {device}, {device}, ...]
 */
public class ApiListServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiListServlet.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		String userId = (String)session.getAttribute("userId");
		if(userId == null) {
			out.print("NO");
			return;
		}
		
		StringBuilder result = new StringBuilder();
		
		result.append('[');
		DeviceDao deviceDao = new DeviceDao();
		Device[] deviceList = deviceDao.ListDeviceInfo(userId);
		for(Device device : deviceList) {
			result.append(gson.toJson(device, Device.class));
			result.append(',');																	//각 json 사이에 ','가 들어가야 한다.
		}
		result.deleteCharAt(result.length() - 1);										//맨 마지막에는 ','를 제거
		result.append(']');
		
		logger.debug(result.toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		out.write(result.toString());
	}
}
