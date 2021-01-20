package com.deloitte.logic;

/**
 * 
 * This class is getting and setting the parameters of the users' information without the password to be resent to the user
 * in the front-end. 
 * 
 * @param id_usuarios, name, lastname, mail, password, monedero, usuario_tipo from the DataBase
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * 
 */

public class InfoUsuario {
	private String name;
	private String lastname;
	private String mail;
	private double monedero;
	
	
	public InfoUsuario() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public double getMonedero() {
		return monedero;
	}
	public void setMonedero(double monedero) {
		this.monedero = monedero;
	}
	
	
	

}
