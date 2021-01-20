package com.deloitte.logica;

/**
 * 
 * This class is for retrieving the information of the order done by the user and gathering so 
 * it can be shown later as a history of purchase. In this case, the class is meant to show in 
 * a more detail way, the purchase that has been done. 
 * 
 * @param id_compras, id_usuarios from the DataBase
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 * 
 */

public class DetalleUsuario {
	private int id_compra;
	private String producto;
	private int cantidad;
	private double precioIndividual;
	private double subTotal;
	
	public DetalleUsuario() {
		
	}

	public int getId_compra() {
		return id_compra;
	}
	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal() {
		this.subTotal = cantidad*precioIndividual;
	}

	public double getPrecioIndividual() {
		return precioIndividual;
	}

	public void setPrecioIndividual(double precioIndividual) {
		this.precioIndividual = precioIndividual;
	}


	
	
	
	
}
