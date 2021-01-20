package com.deloitte.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.deloitte.exception.ResourceNotFoundException;


import com.deloitte.logica.NotaManager;
import com.deloitte.model.Nota;

import com.deloitte.model.repository.NotaRepository;

/**
 * It is a class that works as a controller for the microservice of the user (client) service and interface (HTML). 
 * The controller will show the receipt to the user when the purchase is done and can be seen afterwards.  
 *  
 * @version 1.0.0, 13/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class NotaController {

	@Autowired
	private NotaRepository notaRespository;

	/**
	 * 
	 * This method will show the user its total after it is stored in the database. It will show
	 * the id ticket, id compra, subtotal, promocion1, promocion2 and total. 
	 * 
	 * @param id_compras
	 * @return An object called notaManager
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/Ticket/{id_compras}")
	public ResponseEntity<NotaManager> getByID(@PathVariable(value = "id_compras") Integer id_compras) throws ResourceNotFoundException {
		List < Nota >  Notas = notaRespository.findAll();		
		if(Notas.isEmpty()) {
			throw new ResourceNotFoundException("Ticket with "+ id_compras + " does not exists!!!!");
		}else {
		NotaManager notaManager = new NotaManager();
		for(Nota nota : Notas) {
			if(nota.getId_compras().getId_compras() == id_compras) {		
				notaManager.setId_ticket(nota.getId_ticket());
				notaManager.setId_compras(nota.getId_compras().getId_compras());
				notaManager.setSubtotal(nota.getSubtotal());
				notaManager.setPromocion1(nota.getPromocion1());
				notaManager.setPromocion2(nota.getPromocion2());
				notaManager.setTotal(nota.getTotal());
			}
		}

		return ResponseEntity.ok().body(notaManager);
		}
	}
	
	/**
	 * 
	 * This method will store the information of the purchase of the user.
	 * 
	 * @param JSON file with id ticket, id compra, subtotal, promocion1, promocion2 and total.
	 */
	@PostMapping("/Ticket")
    public Nota createNota(@Valid @RequestBody Nota nota) {
        return notaRespository.save(nota);
    }
	
	
}