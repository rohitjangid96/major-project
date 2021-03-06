package dao.springdao;

import java.util.List;

import model.springmodel.ClassRepresentative;
import model.springmodel.ClassSubjectFaculty;
import model.springmodel.Coordinator;

public interface CoordinatorDAO {

	void addCoordinator(Coordinator theCoordinator);

	List<Coordinator> getCoordinators();

	void addCR(ClassRepresentative theCR);

	List<ClassRepresentative> showCR(String classId);

	void addFaculty(ClassSubjectFaculty theFaculty);

	List<ClassSubjectFaculty> showFaculty(String classid);

	 

}
