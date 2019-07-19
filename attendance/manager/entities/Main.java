package attendance.manager.entities;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		System.out.println(manager.getAllStudent(50));
//		ArrayList<Attendance> attendanceList = new ArrayList();
//		Attendance a = new Attendance();
//		a.setCourseId(1);
//		a.setStudentId(1);
//		a.setDate(new Date());
//		a.setPresent(true);
//		attendanceList.add(a);
//		Attendance a1 = new Attendance();
//		a1.setCourseId(1);
//		a1.setStudentId(2);
//		a1.setDate(new Date());
//		a1.setPresent(true);
//		attendanceList.add(a1);
//		Attendance a2 = new Attendance();
//		a2.setCourseId(2);
//		a2.setStudentId(1);
//		a2.setDate(new Date());
//		a2.setPresent(true);
//		attendanceList.add(a2);
//		// manager.updateAttendance(attendanceList);
//		System.out.println(manager.getAttendance(1));

	}
}
