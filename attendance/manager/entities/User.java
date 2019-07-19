package attendance.manager.entities;

public class User {
	private String userId;
	protected String userName;
	private String password;
	protected String userType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String i) {
		this.userId = i;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}