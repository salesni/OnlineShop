package com.deloitte.logica;

/**
 * 
 * This class is for retrieving the information of the order done by the user, but getting all the details
 * about it. This is class is more for getting the information that can be shown to the manager or employee.
 * Here it shows all the datials of the order.  
 * 
 * @param id_compras, id_usuarios from the DataBase
 * 
 * @version 1.0.0 12/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 * 
 */

public class DetalleManager {
	private int id_detalle;
	private int id_compra;
	private int id_producto;
	private int id_categoria;
	private int cantidad;
	
	public DetalleManager() {	
	}
	
	public int getId_detalle() {
		return id_detalle;
	}
	public void setId_detalle(int id_detalle) {
		this.id_detalle = id_detalle;
	}
	public int getId_compra() {
		return id_compra;
	}
	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
