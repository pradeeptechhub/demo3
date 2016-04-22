package com.patel.pradeep.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.patel.pradeep.model.Project;
import com.patel.pradeep.model.Sponsor;
import com.patel.pradeep.service.ProjectService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private ProjectService service;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", params = "projectId")
	public String goHomeAgain(Model model, @RequestParam("projectId") Long projectId) {
		System.out.println("Invoking goHomeAgain()");
		model.addAttribute("currentProject", this.service.find(projectId));
		return "home";
	}

	@RequestMapping(value = "/")
	public String goHomeAgain(Model model, @ModelAttribute("project") Project project) {
		System.out.println("Invoking goHomeAgain()");
		if(project.getProjectId() != null){ //Redirected flashAttribute data
			model.addAttribute("currentProject", project);
		}else{
			project = new Project();
			project.setName("First Project");
			project.setSponsor(new Sponsor("NASA", "555-555-5555", "nasa@nasa.com"));
			project.setDescription("This is a simple project sponsored by NASA");
			model.addAttribute("currentProject", project);
		}
		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) {
		logger.info("Invoking welcome()");
		return "welcome";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return "index";
	}
}
