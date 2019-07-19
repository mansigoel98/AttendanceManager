package attendance.manager.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceManager {

	private static AttendanceManager instance;

	public static AttendanceManager getInstance() {
		if (instance == null) {
			instance = new AttendanceManager();
		}
		return instance;
	}

	private AttendanceManager() {
	}

	public List<User> getAllStudent(int subjectId) throws ClassNotFoundException {
		List<User> arr = new ArrayList<User>();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select user_id ,user_name,user_type from users inner join subject_students on subject_students.student_id=users.user_id where subject_id="
							+ subjectId + "order by subject_students.student_id");
			while (rs.next()) {
				User user = new User();
				user.setUserId(String.valueOf(rs.getInt("user_id")));
				user.setUserName(rs.getString("user_name"));
				user.setUserType(rs.getString("user_type"));
				arr.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return arr;
	}

	public User getStudent(String id) throws ClassNotFoundException {
		User user = new User();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select user_id ,user_name,user_type from users where user_id=" + id);
			while (rs.next()) {

				user.setUserId(String.valueOf(rs.getInt("user_id")));
				user.setUserName(rs.getString("user_name"));
				user.setUserType(rs.getString("user_type"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return user;

		// TODO Auto-generated method stub

	}

	public List<StudentAttendance> viewAttendanceByStudent(int studentId, String date) throws ClassNotFoundException {
		List<StudentAttendance> arr = new ArrayList<StudentAttendance>();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					" select subject_name,attendance.subject_id,present from attendance,subject where attendance.subject_id=subject.subject_id and student_id="
							+ studentId + " and curr_date='" + date + "'");
			while (rs.next()) {
				StudentAttendance attendance = new StudentAttendance();
				attendance.setSubjectName(rs.getString("subject_name"));
				attendance.setSubjectId(String.valueOf(rs.getInt("subject_id")));
				attendance.setPresent(rs.getString("present"));
				Double agg = calculateAggregate(studentId, Integer.parseInt(attendance.getSubjectId()));
				attendance.setAggregate(agg);
				arr.add(attendance);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return arr;
	}

	public List<StudentAttendance> viewAggregateByStudent(int studentId) throws ClassNotFoundException {
		List<StudentAttendance> arr = new ArrayList<StudentAttendance>();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					" select subject_name,subject.subject_id from subject,subject_students where subject_students.subject_id=subject.subject_id and student_id="
							+ studentId);
			while (rs.next()) {
				StudentAttendance attendance = new StudentAttendance();
				attendance.setSubjectName(rs.getString("subject_name"));
				attendance.setSubjectId(Integer.valueOf(rs.getInt("subject_id")).toString());
				Double agg = calculateAggregate(studentId, Integer.parseInt(attendance.getSubjectId()));
				attendance.setAggregate(agg);
				arr.add(attendance);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return arr;
	}

	Double calculateAggregate(int studentId, int subId) throws ClassNotFoundException {

		List<String> presentOrNot = new ArrayList<String>();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					" select present from attendance where subject_id= " + subId + " and student_id=" + studentId);
			while (rs.next()) {
				presentOrNot.add(rs.getString("present"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		int count = presentOrNot.size();
		int p = 0;
		for (int i = 0; i < count; i++) {
			String s = presentOrNot.get(i);
			if (s.equals("present")) {
				p++;
			}
		}
		Double ans;
		double a;
		a = (p / (count * 1.0)) * 100;
		ans = Double.valueOf(a);
		return ans;

	}

	public List<TeacherAttendance> viewAttendanceByTeacher(int subId, String date) throws ClassNotFoundException {
		List<TeacherAttendance> arr = new ArrayList<TeacherAttendance>();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(" select user_name,users.user_id,present from attendance,users where "
					+ "users.user_id=attendance.student_id and subject_id= " + subId + " and curr_date='" + date + "'");
			while (rs.next()) {
				TeacherAttendance attendance = new TeacherAttendance();
				attendance.setStudentName(rs.getString("user_name"));
				attendance.setStudentId(Integer.valueOf(rs.getInt("user_id")).toString());
				attendance.setPresent(rs.getString("present"));
				Double agg = calculateAggregate(Integer.parseInt(attendance.getStudentId()), subId);
				attendance.setAggregate(agg);
				arr.add(attendance);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return arr;

	}

	public Subject showSubject(int subjectId) throws ClassNotFoundException {
		Subject subject = new Subject();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt
					.executeQuery(" select subject_name,branch_id,year from subject where subject_id =" + subjectId);
			while (rs.next()) {
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setBranchId(String.valueOf(rs.getInt("branch_id")));
				subject.setYear(rs.getString("year"));

			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return subject;

	}

	public List<Subject> showTeacherSubject(int teacherId) throws ClassNotFoundException {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					" select subject.subject_id,subject_name,branch_id,year from subject,subject_teachers "
							+ "where subject.subject_id=subject_teachers.subject_id and teacher_id =" + teacherId);
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectId(String.valueOf(rs.getInt("subject_id")));
				subject.setBranchId(String.valueOf(rs.getInt("branch_id")));
				subject.setYear(rs.getString("year"));
				subjects.add(subject);

			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return subjects;
	}

	public Branch addBranch(String name) throws ClassNotFoundException {
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con
					.prepareStatement("insert into branch values (branch_seq.nextval,'" + name + "')");
			stmt.execute();
			ResultSet rs = stmt.executeQuery(" select branch_id from branch where branch_name = '" + name + "'");
			Branch branch = new Branch();
			while (rs.next()) {
				branch.setBranchId(Integer.valueOf(rs.getInt("branch_id")).toString());
				branch.setBranchName(name);
			}

			return branch;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addSubject(Subject subject) throws ClassNotFoundException {
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"insert into subject values ( '" + subject.getSubjectName() + "', sub_seq.nextval,"
							+ Integer.parseInt(subject.getBranchId()) + ",'" + subject.getYear() + "')");
			stmt.execute();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:49673:orcl", "hr", "mansi");
		return con;
	}

	public Map<Integer, Integer> getAttendance(int studentId) {
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement("select course_id,count(*) as count from attendance where student_id = ? "
							+ "and present =1 group by course_id");
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			Map<Integer, Integer> attendance = new HashMap<>();
			while (rs.next()) {
				attendance.put(rs.getInt("course_id"), rs.getInt("count"));
			}
			return attendance;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}

		return null;
	}

	public Boolean loginUser(int userId, String password, String userType) {
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement("select user_id from users where user_id = ? and password= ? and user_type= ? ");
			stmt.setInt(1, userId);
			stmt.setString(2, password);
			stmt.setString(3, userType);
			ResultSet rs = stmt.executeQuery();

			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addAttendanceList(List<Attendance> attendances) {
		// TODO Auto-generated method stub
		try {
			Connection con = getConnection();
			for (Attendance a : attendances) {
				PreparedStatement stmt = con.prepareStatement("insert into attendance values (?,?,?,?)");
				stmt.setInt(1, Integer.parseInt(a.getStudentId()));
				stmt.setInt(2, Integer.parseInt(a.getSubjectId()));
				stmt.setString(3, a.getDate());
				if (a.getPresent() == true) {
					stmt.setString(4, "present");
				} else {
					stmt.setString(4, "absent");
				}
				stmt.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void addStudentDetail(Student student) {
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement("insert into users values (users_seq.nextval,'"
					+ student.getUserName() + "' ,users_seq2.nextval ,'STUDENT')");
			stmt.execute();

			ResultSet rs = stmt
					.executeQuery(" select user_id from users where user_name = '" + student.getUserName() + "'");
			while (rs.next()) {
				student.setUserId(Integer.valueOf(rs.getInt("user_id")).toString());
			}
			Statement stmt1 = con.createStatement();

			ResultSet rs1 = stmt1.executeQuery("select subject_id from subject where branch_id = "
					+ Integer.parseInt(student.getBranch_id()) + " and year = '" + student.getYear() + "'");
			while (rs1.next()) {
				PreparedStatement stmt2 = con.prepareStatement("insert into subject_students values ( "
						+ rs1.getInt("subject_id") + " , " + Integer.parseInt(student.getUserId()) + " )");
				stmt2.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Boolean matchSubjectTeacher(Integer subjectId, Integer teacherId) {
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from subject_teachers where subject_id = "
					+ subjectId + " and teacher_id = " + teacherId);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
