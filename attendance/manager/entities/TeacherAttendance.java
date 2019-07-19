package attendance.manager.entities;

public class TeacherAttendance {
	private String studentName;
	private String studentId;
	private String present;
	private Double aggregate;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String subjectName) {
		this.studentName = subjectName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String subjectId) {
		this.studentId = subjectId;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public Double getAggregate() {
		return aggregate;
	}

	public void setAggregate(Double aggregate) {
		this.aggregate = aggregate;
	}

}
