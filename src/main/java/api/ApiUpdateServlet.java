package api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import basic.User;

//한 유저가 특정 모듈의 현재 상태를 갱신한다. -> 어떤 동작을 취할지, 어떤 번호의 모듈을 건드릴지를 함께 보내주어야 한다.
@WebServlet("/api/update")
public class ApiUpdateServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiUpdateServlet.class);
	
	/*
	 * 로그인 한 사용자에게 등록된 device정보를 DB에서 추출하여
	 * json 형태로 반환한다.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	/*
	 * 유저가 특정 모듈의 현재 설정 상태를 갱신한다.
	 * 어떤 모듈을 어떤 손동작으로 변경할 것인지를 전달받는다. (json 형태의 문자열로)
	 * {
	 * 		iotNumber: 모듈 번호
	 * 		action: 변경할 손동작 번호
	 * }
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String) session.getAttribute("userId");
		if(userId == null) {
			//로그인이 되지 않은 경우..?
		}
		
		String jsonData = ApiLoginServlet.readBody(request);					//HTTP 몸체의 정보를 읽어온다.
		Gson gson = new Gson();

		logger.debug(jsonData);
		Device module = gson.fromJson(jsonData, Device.class);				//전달된 device 정보를 추출한다.

		//현재 ID의 유저의 모듈정보를 추가한다. -> 기존에 있는 것에 대한 정보를 업데이트 하는 경우만 가능
		//iot table에 저장함.
		
	}
}
