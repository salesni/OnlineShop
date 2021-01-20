package com.deloitte.controller.logic;

/**
 * 
 * This class is for getting just the id of the order, the id of the user and the user's mail
 * just to have a record of the sales. It has its getters and setters. 
 * 
 * 
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 *
 */

public class CompraManager {

	private int id_compras;
	private int id_usuario;
	private String usuarioMail;
	
	public CompraManager() {
		
	}

	public int getId_compras() {
		return id_compras;
	}

	public void setId_compras(int id_compras) {
		this.id_compras = id_compras;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuarioMail() {
		return usuarioMail;
	}

	public void setUsuarioMail(String usuarioMail) {
		this.usuarioMail = usuarioMail;
	}


}
