package com.deloitte.controller;

import java.util.LinkedList;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.exception.ResourceNotFoundException;
import com.deloitte.logica.DetalleManager;
import com.deloitte.logica.DetalleUsuario;

import com.deloitte.model.Detalles;

import com.deloitte.model.repository.DetallesRepository;

/**
 * It is a class that works as a controller for the microservice of the user (client and manager/employee) service and interface (HTML). 
 * The controller works with the specific URL based on the target details (detalles).  
 *  
 * @version 1.0.0, 13/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class DetallesController {

	@Autowired
	private DetallesRepository detallesRespository;
	
	/**
	 * This method gets all the detalles that are stored into the database. However, 
	 * the data is not filter. 
	 * 
	 * @return A list of Detalles. 
	 */
	@Deprecated
	@GetMapping("/Detalles")
	public List < Detalles> getAllDetallesss() {
        return detallesRespository.findAll();
    }
	
	/**
	 * 
	 * This method will get the information of the order in detail for the user. It will
	 * show in the front-end the preview of the purchase. 
	 * 
	 * @param idCompra
	 * @return A list of the object Res, in which it has id compra, descripcion, cantidad, precio individual and subtotal
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/DetallesUsuario/{idCompra}")
	public List < DetalleUsuario >getDetallessById(@PathVariable(value = "idCompra") Integer CompraID) throws ResourceNotFoundException {
		List < Detalles >  Detalles = detallesRespository.findAll();
		List < DetalleUsuario >  Res = new LinkedList< DetalleUsuario >();
		for( Detalles det:Detalles) {
			if(det.getCompra().getId_compras() == CompraID) {
				DetalleUsuario detUsuario =  new DetalleUsuario();
				detUsuario.setId_compra(det.getCompra().getId_compras());
				detUsuario.setProducto(det.getProducto().getDescripccion());
				detUsuario.setCantidad(det.getCantidad());
				detUsuario.setPrecioIndividual(det.getProducto().getPrecio());
				detUsuario.setSubTotal();

				Res.add(detUsuario);
			}
		}
				
		return Res;
	}
	
	/**
	 * 
	 * This method will get the information of the order in detail for the manager. It will
	 * show in the front-end as what the user had purchased. 
	 * 
	 * @param idCompra
	 * @return A list of the object Res, in which it has id compra, descripcion, cantidad and precio individual.
	 */
	@GetMapping("/DetallesManager/{idCompra}")
	public List < DetalleManager >getDetallessByIdManager(@PathVariable(value = "idCompra") Integer CompraID) throws ResourceNotFoundException {
		List < Detalles >  Detalles = detallesRespository.findAll();
		List < DetalleManager >  Res = new LinkedList< DetalleManager >();
		for( Detalles det:Detalles) {
			if(det.getCompra().getId_compras() == CompraID) {
				DetalleManager detManager =  new DetalleManager();
				detManager.setId_detalle(det.getId_detalles());
				detManager.setId_compra(det.getCompra().getId_compras());
				detManager.setId_producto(det.getProducto().getId_producto());
				detManager.setId_categoria(det.getProducto().getCategoria().getId_categorias());
				detManager.setCantidad(det.getCantidad());
				Res.add(detManager);
			}
		}
		return Res;
	}
	
	/**
	 * 
	 * This function was to calculate the receipt for the user. It is not longer use
	 * as the logic is done in the front-end. 
	 * @param idCompra
	 * @return null
	 * 
	 */
	@Deprecated
	@GetMapping("/Recibo/{idCompra}")
	public List < Detalles >getRecibo(@PathVariable(value = "idCompra") Integer CompraID) throws ResourceNotFoundException {
		List < Detalles >  Detalles = detallesRespository.findAll();
		List < DetalleManager >  Res = new LinkedList< DetalleManager >();		
		for( Detalles det:Detalles) {
			
			if(det.getCompra().getId_compras() == CompraID) {
				DetalleManager detManager =  new DetalleManager();
				detManager.setId_detalle(det.getId_detalles());
				detManager.setId_compra(det.getCompra().getId_compras());
				detManager.setId_producto(det.getProducto().getId_producto());
				detManager.setId_categoria(det.getProducto().getCategoria().getId_categorias());
				detManager.setCantidad(det.getCantidad());
				Res.add(detManager);
			}
		}
		return null;
	}	
	
	/**
	 * 
	 * This method generates a new row of detalles inside the database and then filter
	 * for each user client/manager.  
	 * 
	 * @param JSON file with all the parameters requested. 
	 * 
	 */
	@PostMapping("/Detalles")
    public Detalles createDetalless(@Valid @RequestBody Detalles Detalles) {
        return detallesRespository.save(Detalles);
    }
	


	
	
	
}