package com.patel.pradeep.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.patel.pradeep.model.Resource;

/*Note: @SessionAttributes is applicable to single controller only*/

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model) {
		System.out.println("Invoking add()");
		return "resource_add";
	}

	@RequestMapping("/request")
	@ResponseBody //request(@RequestBody String resource)
	public String request(@ModelAttribute("resource") Resource resource){
		System.out.println("Invoking request()");
		System.out.println(resource);
		//Send out an email for approval
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
		status.setComplete(); //To remove attributes from session (@SessionAttributes("resource") here)
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
