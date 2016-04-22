package com.patel.pradeep.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patel.pradeep.model.Project;
import com.patel.pradeep.service.ProjectService;
import com.patel.pradeep.validators.ProjectValidator;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value="///find/{projectId}") //Seems no effect of extra forward slashes
	@ResponseBody
	public Project findProjectObject(Model model, @PathVariable Long projectId){
		System.out.println("Invoking findProjectObject()");
		return this.projectService.find(projectId);
	}

	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable Long projectId){
		model.addAttribute("project", this.projectService.find(projectId));
		return "project";
	}

	@RequestMapping(value="/find")
	public String find(Model model){
		model.addAttribute("projects", this.projectService.findAll());
		return "projects";
	}

	@SuppressWarnings("serial")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProject(Model model){
		System.out.println("invoking addProject");
		model.addAttribute("types", new ArrayList<String>(){{
			add("");
			add("Single Year");
			add("Multi Year");
		}});
		model.addAttribute("project", new Project());
		return "project_add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveProject(@Valid @ModelAttribute Project project, Errors errors){

		if(!errors.hasErrors()){
			System.out.println("The project validated.");
		}else{
			System.out.println("the project did not validate");
			return "project_add"; //To current page if fail validations
		}

		System.out.println("invoking saveProject");
		System.out.println(project);
		return "redirect:/project/find"; //HTTP Status code 302 indicates redirect
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProjectValidator());
	}

	/*@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=multi"})
	public String saveMultiYearProject(){
		System.out.println("invoking saveMultiYearProject");
		return "project_add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=multi","special"})
	public String saveSpecialProject(){
		System.out.println("invoking saveSpecialProject");
		return "project_add";
	}*/
}
