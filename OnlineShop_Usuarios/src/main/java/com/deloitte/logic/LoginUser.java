package com.deloitte.logic;

/**
 * 
 * This class is for retrieving the information of the user client/employee/manager from the mySQL Data Base.
 * It is also use to check the log in action of the UsuarioController. It has its setters and getters for each
 * parameter. In this case, just mail and password are defined to be used inside the method login. 
 * 
 * @param mail and password from the DataBase
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * 
 */

public class LoginUser {

	private String mail;
	private String password;
	
	public LoginUser() {
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
