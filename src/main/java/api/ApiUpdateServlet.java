package api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import basic.User;

//한 유저가 특정 모듈의 현재 상태를 갱신한다. -> 어떤 동작을 취할지, 어떤 번호의 모듈을 건드릴지를 함께 보내주어야 한다.
@WebServlet("/api/update")
public class ApiUpdateServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiUpdateServlet.class);
	
	/*
	 * 로그인 한 사용자에게 등록된 device정보를 DB에서 추출하여
	 * json의 리스트 형태로 반환한다.
	 * [ {...}, {...}, ... ] 의 형태가 된다.
	 */
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
	
	
	/*
	 * 유저가 특정 모듈의 현재 설정 상태를 갱신한다. (json 형태의 문자열로)
	 * {
	 * 		name: 모듈 이름
	 * 		finger_idx: 변경할 손동작 번호
	 * 		background_image: 변경 손동작 이미지 파일 경로
	 * 		state: 현재 상태정보
	 * 		idx: 모듈 번호
	 * }
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String userId = (String) session.getAttribute("userId");
		if(userId == null) {
			out.print("NO");
			return;
		}
		
		String jsonData = ApiLoginServlet.readBody(request);					//HTTP 몸체의 정보를 읽어온다.
		Gson gson = new Gson();

		logger.debug(jsonData);
		Device module = gson.fromJson(jsonData, Device.class);				//전달된 device 정보를 추출한다.

		DeviceDao deviceDao = new DeviceDao();
		try {
			boolean success = deviceDao.UpdateDevice(userId, module);				//update
			if(success) {
				out.print("OK");
			}else {
				out.print("NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
