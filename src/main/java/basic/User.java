package basic;

import java.util.ArrayList;

public class User {
	private String userId;
	private String password;
	private ArrayList<String> IPs; //사용자의 IOT 기기 IP정보들
	
	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
		this.IPs = new ArrayList<String>();
	}
	
	public ArrayList<String> getIPs() {
		return IPs;
	}
	
	public boolean addIP(String ip) {
		return IPs.add(ip);
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}

	//hashCode와  equals 함수의 override는 테스트 코드에서 assertEquals의 객체간 비교에서 사용하기 위해 정의함.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
