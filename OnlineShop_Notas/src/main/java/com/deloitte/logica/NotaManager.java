package com.deloitte.logica;

/**
 * 
 * This class is for retrieving the information of 'detalles' table, for a preview of the order, from the mySQL Data Base.
 * It has its setters and getters for each one of the parameters. In this case, the parameters that were define are for 
 * getting and setting specific information from the database. 
 * 
 * @param id_ticket, id_compras, subtotal, promocion1, promocion2 and total from the DataBase
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 * 
 */

public class NotaManager {

	private int id_ticket;
	private int id_compras;
	private double subtotal;
	private double promocion1;
	private double promocion2;
	private double total;
	
	public NotaManager() {
		
	}
	
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public int getId_compras() {
		return id_compras;
	}
	public void setId_compras(int id_compras) {
		this.id_compras = id_compras;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getPromocion1() {
		return promocion1;
	}
	public void setPromocion1(double promocion1) {
		this.promocion1 = promocion1;
	}
	public double getPromocion2() {
		return promocion2;
	}
	public void setPromocion2(double promocion2) {
		this.promocion2 = promocion2;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
