package controller.springcontroller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import model.springmodel.ClassRepresentative;
import model.springmodel.ClassSubjectFaculty;
import model.springmodel.Coordinator;
import service.springservice.CoordinatorService;


@Controller
@RequestMapping("/admin")
public class AdminController {
		
	@Autowired
	private CoordinatorService coordinatorService;
	
	@GetMapping("/home")
	public String adminHome(Model theModel)
	{	
		return "adminhome";
	}
	
	@GetMapping("/addformCoordinators")
	public String addformCoordinators(Model theModel)
	{	
		Coordinator theCoordinator= new Coordinator();
		theModel.addAttribute("coordinator",theCoordinator);
		return "addcoordinators";
	}
	
	@PostMapping("/addCoordinators")
	public String addCoordinators(@ModelAttribute ("coordinator") Coordinator theCoordinator)
	{
		theCoordinator.setClassid();
		coordinatorService.addCoordinator(theCoordinator);
		
		return "adminhome";
	}
	
	@GetMapping("/showCoordinators")
	public String showCoordinators(Model theModel)
	{	
		List<Coordinator> theCoordinators= coordinatorService.getCoordinators();
		theModel.addAttribute("coordinators",theCoordinators);

		return "showcoordinators";
	}
	
	@GetMapping("/addCRForm")
	public String addCRForm(Model theModel,HttpServletRequest request)
	{	
		HttpSession session=request.getSession();
		String classId=(String)session.getAttribute("classid");
		
		ClassRepresentative theCR = new ClassRepresentative();
		theCR.setClassAttributes(classId);
		
		theModel.addAttribute("CR",theCR);
		return "addCR";
	}
	
	@PostMapping("/addCR")
	public String addCR(@ModelAttribute ("CR") ClassRepresentative theCR,HttpServletRequest request)
	{	
		HttpSession session=request.getSession();
		String classId=(String)session.getAttribute("classid");
		Integer year=(Integer)session.getAttribute("year");
		
		theCR.setClassid();
		coordinatorService.addCR(theCR);
		
		return "redirect:/major/class/classdiscussionfaculty?classId="+classId+"&year="+year;
	}
	
	@GetMapping("/showCR")
	public String showCR(Model theModel,HttpServletRequest request)
	{	
		HttpSession session=request.getSession();
		String classId=(String)session.getAttribute("classid");
		List<ClassRepresentative> theCR= coordinatorService.showCR(classId);
		
		theModel.addAttribute("CR",theCR);
		
		return "showCR";
	}
	
	@GetMapping("/addformFaculty")
	public String addformFaculty(Model theModel,HttpServletRequest request)
	{	
		HttpSession session=request.getSession();
		String classId=(String)session.getAttribute("classid");
		
		ClassSubjectFaculty thefaculty= new ClassSubjectFaculty();
		thefaculty.setClassAttributes(classId);
		
		theModel.addAttribute("faculty",thefaculty);
		return "addsubjectfaculty";
	}
	
	@PostMapping("/addFaculty")
	public String addCR(@ModelAttribute ("faculty") ClassSubjectFaculty theFaculty)
	{	
		theFaculty.setClassid();
		coordinatorService.addFaculty(theFaculty);
		return "addsubjectfaculty";	
		
	}
	
	@GetMapping("/showFaculty")
	public String showFaculty(Model theModel,HttpServletRequest request)
	{	
		HttpSession session= request.getSession();
		String classid=(String)session.getAttribute("classid");
		List<ClassSubjectFaculty> theFaculty= coordinatorService.showFaculty(classid);
		theModel.addAttribute("Faculty",theFaculty);
		
		return "showfaculty";
	}
	
	
}
