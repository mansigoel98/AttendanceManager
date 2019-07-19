package attendance.manager.entities;

public class Attendance {

	private String studentId;
	private String subjectId;
	private String date;
	private Boolean present;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getPresent() {
		return present;
	}

	public void setPresent() {
		this.present = false;
	}

	public void setPresent(Boolean present) {
		this.present = present;
	}
}