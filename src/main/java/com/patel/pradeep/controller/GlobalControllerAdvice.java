package com.patel.pradeep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Apply to all Controller classes by specifying in annotations
@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {

	//Applicable to all controllers overwriting the mapping path in servlet-context.xml
	//if annotations = Controller.class is specified @ class level as above.
	/*@ModelAttribute("currentDate")
	public Date getCurrentDate() {
		return new Date();
	}*/

	//Applicable only if ProjectValidator is applicable for all Controllers
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProjectValidator());
	}*/

	@ExceptionHandler(NullPointerException.class)
	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}
}
