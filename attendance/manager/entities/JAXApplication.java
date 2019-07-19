package attendance.manager.entities;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/attendanceManager")
public class JAXApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public JAXApplication() {
		singletons.add(new StudentResource());
		singletons.add(new LoginResource());
		singletons.add(new BranchResource());
		singletons.add(new AttendanceResource());
		singletons.add(new SubjectResource());

	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}