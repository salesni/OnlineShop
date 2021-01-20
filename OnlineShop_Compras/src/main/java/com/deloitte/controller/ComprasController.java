package com.deloitte.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.controller.logic.CompraManager;
import com.deloitte.exception.ResourceNotFoundException;
import com.deloitte.model.Compra;
import com.deloitte.model.repository.CompraRepository;

/**
 * It is a class that works as a controller for the microservice of the user (client and manager/employee) service and interface (HTML). 
 * The controller works with the specific URL based on the target Compras.  
 *  
 * @version 1.0.0, 13/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ComprasController {

	@Autowired
	private CompraRepository comprasRespository;
	/**
	 * 
	 * This method will return all the purchases done by the users
	 * 
	 * @return A list object call comprasManager
	 */
	
	@GetMapping("/Compras")
	public List < CompraManager> getAllComprass() {
		List<Compra> compras = comprasRespository.findAll();
		List<CompraManager> comprasManager = new LinkedList<CompraManager>();
		for(Compra compra : compras) {
			CompraManager compraManager = new CompraManager();
			compraManager.setId_compras(compra.getId_compras());
			compraManager.setUsuarioMail(compra.getUsuario().getMail());
			compraManager.setId_usuario(compra.getUsuario().getId_usuario());
			comprasManager.add(compraManager);
		}
        
		return comprasManager;
    }
	
	/**
	 * This method is meant to be used for the manager to see a purchase with the ID
	 * of the purchase. 
	 * 
	 * @Param idCompra
	 * @return An object call compraManager which contains the information requested.
	 * @throws ResourceNotFoundException 
	 */
	
	@GetMapping("/Compras/{idCompra}")
	public ResponseEntity<CompraManager> getComprasById(@PathVariable(value = "idCompra") Integer ComprasId) throws ResourceNotFoundException {
		
		
		Compra compra = comprasRespository.findById(ComprasId)
				.orElseThrow(() -> new ResourceNotFoundException("Compras "+ ComprasId + " does not exists!!!!"));
		
		CompraManager compraManager = new CompraManager();
		compraManager.setId_compras(compra.getId_compras());
		compraManager.setUsuarioMail(compra.getUsuario().getMail());
		compraManager.setId_usuario(compra.getUsuario().getId_usuario());
		
		return ResponseEntity.ok().body(compraManager);
	}
	
	
	/**
	 * This method is meant to be used for the manager to see a purchase with the ID
	 * of the user. 
	 * 
	 * @Param idUsuario
	 * @return An object call compraManager which contains the information requested.
	 * @throws ResourceNotFoundException 
	 */
		
	@GetMapping("/ComprasIDUser/{idUsuario}")
	public List < CompraManager> getComprasByIdUsuario(@PathVariable(value = "idUsuario") Integer idUsuario) throws ResourceNotFoundException {
		
		List<Compra> compras = comprasRespository.findAll();
		List<CompraManager> comprasManager = new LinkedList<CompraManager>();
		
		for(Compra compra : compras) {
			if(compra.getUsuario().getId_usuario() == idUsuario) {
				CompraManager compraManager = new CompraManager();
				compraManager.setId_compras(compra.getId_compras());
				compraManager.setUsuarioMail(compra.getUsuario().getMail());
				compraManager.setId_usuario(compra.getUsuario().getId_usuario());
				comprasManager.add(compraManager);
			}
		}
		return comprasManager;
	}
	
	
	/**
	 * This method is meant to create a new purchase inside the database. 
	 * 
	 * @Param Object Compra
	 * @return The pruchase as object Compras
	 * @throws ResourceNotFoundException 
	 */
		
	@PostMapping("/Compras")
    public Compra createCompras(@Valid @RequestBody Compra Compras) {
        return comprasRespository.save(Compras);
    }
	
	/**
	 * This method is meant to update a purchase with the ID user. 
	 * 
	 * @Param Object Compra
	 * @return The pruchase as object Compras
	 * @throws ResourceNotFoundException 
	 */
	
	@PutMapping("/Compras/{idUsers}")
    public ResponseEntity < Compra > updateCompras(@PathVariable(value = "idUsers") Integer ComprasId,
        @Valid @RequestBody Compra ComprasDetails) throws ResourceNotFoundException {
        Compra Compras = comprasRespository.findById(ComprasId)
            .orElseThrow(() -> new ResourceNotFoundException("Compras not found for this id :: " + ComprasId));
        
        Compras.setUsuario(ComprasDetails.getUsuario());
        
        final Compra updatedCompras = comprasRespository.save(Compras);
        return ResponseEntity.ok(updatedCompras);
    }
	
	
	/**
	 * This method is meant to delete any purchase from the database. Only the manager can do it.
	 * It is done using the id of the user.  
	 * 
	 * @Param Object idUser
	 * @return a response with a boolean saying true if it was deleted. 
	 * @throws ResourceNotFoundException 
	 */
	@DeleteMapping("/Compras/{idUsers}")
    public Map < String, Boolean > deleteCompras(@PathVariable(value = "idUsers") Integer ComprasId)
    throws ResourceNotFoundException {
        Compra Compras = comprasRespository.findById(ComprasId)
            .orElseThrow(() -> new ResourceNotFoundException("Compras not found for this id :: " + ComprasId));

        comprasRespository.delete(Compras);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	
	
}