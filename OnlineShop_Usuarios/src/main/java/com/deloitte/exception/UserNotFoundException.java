package com.deloitte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This class is to define an exception response for a non existing user. This was done as the 
 * NotFoundException does not have any related method. 
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * @author Mirian Hipolito
 * 
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String message){
        super(message);
    }
	
}