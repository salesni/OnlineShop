package com.deloitte.logic;

/**
 * 
 * This class is for retrieving the information of the user client/employee/manager from the mySQL Data Base.
 * In this case is for the information the manager will have access to. It has its setters and getters for each
 * parameter
 * 
 * @param id_usuarios, name, lastname, mail monedero, usuario_tipo from the DataBase
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * 
 */

public class InfoUsuarioManager {
	private int id_usuarios;
	private String name;
	private String lastname;
	private String mail;
	private double monedero;
	private int usuario_tipo;
	
	
	public InfoUsuarioManager() {	
	}
	
	public int getId_usuarios() {
		return id_usuarios;
	}

	public void setId_usuarios(int id_usuarios) {
		this.id_usuarios = id_usuarios;
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
	public int getUsuario_tipo() {
		return usuario_tipo;
	}
	public void setUsuario_tipo(int usuario_tipo) {
		this.usuario_tipo = usuario_tipo;
	}


}
