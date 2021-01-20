package com.deloitte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This class is to define an exception response for a non existing resource. This class is given
 * by Java framework.  
 * 
 * @version 1.0.0 12/11/2020
 * 
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message){
        super(message);
    }
	
}