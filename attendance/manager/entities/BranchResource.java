package attendance.manager.entities;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/branch")
@Produces(MediaType.APPLICATION_JSON)
public class BranchResource {

	@GET
	public Response addBranch(@QueryParam("name") String name) throws ClassNotFoundException {
		AttendanceManager manager = AttendanceManager.getInstance();

		Branch branch = manager.addBranch(name);
		return Response.status(Status.OK).entity(branch).build();

	}
}
