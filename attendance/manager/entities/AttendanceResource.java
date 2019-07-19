package attendance.manager.entities;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/addAttendance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttendanceResource {
	@POST
	public Response addAttendance(List<Attendance> attendances) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		manager.addAttendanceList(attendances);
		return Response.status(Status.OK).entity("SUCCESS").build();

	}

	@GET
	@Path("/viewAttendanceByTeacher")
	public Response getAttendanceByTeacher(@QueryParam("subjectId") Integer id, @QueryParam("date") String date)
			throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		List<TeacherAttendance> user = manager.viewAttendanceByTeacher(id, date);
		return Response.status(Status.OK).entity(user).build();
	}
}
