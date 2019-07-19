package attendance.manager.entities;

import java.util.ArrayList;
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

@Path("/subject")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SubjectResource {
	@POST
	public Response addsubject(Subject subject) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		manager.addSubject(subject);
		return Response.status(Status.OK).entity("SUCCESS").build();

	}

	@GET
	@Path("/teacherSubject")
	public Response showTeachSubject(@QueryParam("teacherId") Integer id) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();
		List<Subject> subject = new ArrayList<Subject>();
		subject = manager.showTeacherSubject(id);
		return Response.status(Status.OK).entity(subject).build();
	}

}
