package com.deloitte.logic;



public class ProductoUser {


	private int id_producto;
	private String descripcion;
	private double precio;
	private String categoria;
	
	public ProductoUser() {
		
	}
	
	

	public int getId_producto() {
		return id_producto;
	}



	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}



	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	

}
