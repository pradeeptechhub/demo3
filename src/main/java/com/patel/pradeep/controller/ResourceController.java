package com.patel.pradeep.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.patel.pradeep.model.Resource;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model) {
		List<String> options = new LinkedList<>(
				Arrays.asList(new String[] { "Material", "Other", "Staff", "Techincal Equipment" }));
		model.addAttribute("typeOptions", options);

		List<String> radios = new LinkedList<>(Arrays.asList(new String[] { "Hours", "Piece", "Tons" }));
		model.addAttribute("radioOptions", radios);

		List<String> checks = new LinkedList<>(
				Arrays.asList(new String[] { "Lead Time", "Special Rate", "Requires Approval" }));
		model.addAttribute("checkOptions", checks);

		model.addAttribute("resource", new Resource());
		return "resource_add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource) {
		System.out.println("Invoking the save() method.");
		System.out.println(resource);
		//return "resource_add";
		return "redirect:/resource/add";
	}
}
