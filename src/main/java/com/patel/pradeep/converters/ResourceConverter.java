package com.patel.pradeep.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.patel.pradeep.model.Resource;
import com.patel.pradeep.service.ResourceService;

public class ResourceConverter implements Converter<String, Resource> {

	@Autowired
	private ResourceService service;

	@Override
	public Resource convert(String resourceId) {
		System.out.println("Invoking convert() from ResourceConverter class.");
		return service.find(new Long(resourceId));
	}

}
