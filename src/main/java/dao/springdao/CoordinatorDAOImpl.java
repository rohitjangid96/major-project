package dao.springdao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.springmodel.ClassRepresentative;
import model.springmodel.Coordinator;

@Repository
public class CoordinatorDAOImpl implements CoordinatorDAO {
	
	
	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void addCoordinator(Coordinator theCoordinator) {
		System.out.println("in coordinatordao impl");
				//get hibernate session
				Session currentSession=sessionFactory.getCurrentSession();
				System.out.println(theCoordinator.toString());
				//save the customer
				currentSession.save(theCoordinator);
	}

	@Override
	public List<Coordinator> getCoordinators() {
		
		Session currentSession= sessionFactory.getCurrentSession();
		
		Query<Coordinator> qr= currentSession.createQuery("from Coordinator ",Coordinator.class);
		
		List<Coordinator> coordinators  =qr.getResultList();
		
		return coordinators;
		
		
	}

	@Override
	public void addCR(ClassRepresentative theCR) {
		//get hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		System.out.println(theCR.toString());
		//save the customer
		currentSession.save(theCR);
	}

	@Override
	public List<ClassRepresentative> showCR() {
		Session currentSession= sessionFactory.getCurrentSession();
		
		Query<ClassRepresentative> qr= currentSession.createQuery("from ClassRepresentative ",ClassRepresentative.class);
		
		List<ClassRepresentative> CR  =qr.getResultList();
		
		return CR;
		
		
	}

}