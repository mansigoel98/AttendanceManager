package attendance.manager.entities;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

	@GET
	@Path("/{id}")
	public Response getStudentDetail(@PathParam("id") String id) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		User user = manager.getStudent(id);
		return Response.status(Status.OK).entity(user).build();

	}

	@GET
	public Response getAllStudentDetail(@QueryParam("subjectId") Integer id) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		List<User> user = manager.getAllStudent(id);
		return Response.status(Status.OK).entity(user).build();
	}

	@GET
	@Path("/viewAttendance")
	public Response viewAttendanceStudent(@QueryParam("studentId") Integer id, @QueryParam("date") String date)
			throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		List<StudentAttendance> attendance = manager.viewAttendanceByStudent(id, date);
		return Response.status(Status.OK).entity(attendance).build();
	}

	@GET
	@Path("/viewAggregate")
	public Response viewAggregateStudent(@QueryParam("studentId") Integer id) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		List<StudentAttendance> attendance = manager.viewAggregateByStudent(id);
		return Response.status(Status.OK).entity(attendance).build();
	}

	@GET
	@Path("/subject")
	public Response showSubject(@QueryParam("subjectId") Integer id) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		Subject subject = new Subject();
		subject = manager.showSubject(id);
		return Response.status(Status.OK).entity(subject).build();
	}

	@POST
	@Path("/addStudent")
	public Response addStudent(Student student) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		manager.addStudentDetail(student);
		return Response.status(Status.OK).entity("succsess").build();
	}
}
