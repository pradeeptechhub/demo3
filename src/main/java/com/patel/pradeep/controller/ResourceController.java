package com.patel.pradeep.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.patel.pradeep.model.Resource;
import com.patel.pradeep.service.ResourceService;

/*Note: @SessionAttributes is applicable to single controller only*/

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@Autowired
	private ResourceService service;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String handleUpload(@RequestParam("file") MultipartFile file){
		if(!file.isEmpty()){
			return "The file size is " + file.getSize();
		}else{
			return "There was a problem.";
		}
	}

	@RequestMapping("/add")
	public String add(Model model) {
		System.out.println("Invoking add()");
		return "resource_add";
	}

	@RequestMapping("/{resourceId}")
	@ResponseBody
	public Resource findResource(@PathVariable("resourceId") Resource resource){
		//return service.find(resourceId); //Moved to ResourceConverter class
		return resource;
	}

	@RequestMapping("/find")
	public String find(Model model) {
		model.addAttribute("resources", service.findAll());
		return "resources";
	}

	@RequestMapping("/runtime") //handled by HandlerExceptionResolver implementing class
	public String globalException(Model model) {
		throw new RuntimeException("Test Run Time Exception.");
	}

	//Moved to GlobalControllerAdvice class
	/*@ExceptionHandler(NullPointerException.class)
	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}*/

	@RequestMapping("/request")
	@ResponseBody // request(@RequestBody String resource)
	public String request(@ModelAttribute("resource") Resource resource) {
		System.out.println("Invoking request()");
		System.out.println(resource);
		// Send out an email for approval
		return "The request has been sent for approval";
	}

	@RequestMapping("/review")
	public String review(@ModelAttribute Resource resource) {
		System.out.println("invoking review()");
		return "resource_review";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status) {
		System.out.println("Invoking the save()");
		System.out.println(resource);
		// To remove attributes from session (@SessionAttributes("resource")
		// here)
		status.setComplete();
		// return "resource_add"; //OR
		return "redirect:/resource/add";
	}

	// These ModelAttributes get placed within the model
	// (for any request under this controller).
	@ModelAttribute("typeOptions")
	public List<String> getTypes() {
		return Arrays.asList(new String[] { "Material", "Other", "Staff", "Techincal Equipment" });
	}

	@ModelAttribute("radioOptions")
	public List<String> getRadios() {
		return new LinkedList<>(Arrays.asList(new String[] { "Hours", "Piece", "Tons" }));
	}

	@ModelAttribute("checkOptions")
	public List<String> getChecks() {
		return Arrays.asList(new String[] { "Lead Time", "Special Rate", "Requires Approval" });
	}

	@ModelAttribute("resource")
	public Resource getResource() {
		System.out.println("Adding a new Resource to the model.");
		return new Resource();
	}

}
