package com.patel.pradeep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model){
		return "resource_add";
	}

	@RequestMapping("/save")
	public String save(){
		System.out.println("Invoking the save() method.");
		return "resource_add";
	}
}
