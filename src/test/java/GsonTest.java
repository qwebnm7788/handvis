import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonTest {
	private static String pk = "{	\"username\": \"jaewon\",	\"password\": \"password\"}";
	private static String TEST_JSON = "{\"username\":\"jaewon\",\"password\":\"password\"}";
	@Test
	public void parserTest() {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonTree = jsonParser.parse(pk);
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(TEST_JSON, JsonObject.class);

			System.out.println(jsonObject);
			
			System.out.println(jsonObject.get("username"));
			System.out.println(jsonObject.get("password"));
	}

}
