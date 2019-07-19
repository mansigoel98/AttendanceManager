package attendance.manager.entities;

public class StudentAttendance {
	private String subjectName;
	private String subjectId;
	private String present;
	private Double aggregate;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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
