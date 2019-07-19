package attendance.manager.entities;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

	@GET
	public Response loginUser(@QueryParam("userId") Integer id, @QueryParam("password") String password,
			@QueryParam("type") String type) {
		AttendanceManager manager = AttendanceManager.getInstance();
		Boolean valid = manager.loginUser(id, password, type);
		return Response.status(Status.OK).entity(valid).build();
	}

	@GET
	@Path("/matchSubject")
	public Response loginUser(@QueryParam("subjectId") Integer id, @QueryParam("teacherId") Integer id2) {
		AttendanceManager manager = AttendanceManager.getInstance();
		Boolean valid = manager.matchSubjectTeacher(id, id2);
		return Response.status(Status.OK).entity(valid).build();
	}
}
