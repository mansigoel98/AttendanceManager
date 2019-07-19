package attendance.manager.entities;

public class Student extends User {
	private String branch_id;
	private String year;

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setUserType() {
		this.userType = "STUDENT";
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
