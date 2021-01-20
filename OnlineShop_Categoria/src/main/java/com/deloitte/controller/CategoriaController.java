package com.deloitte.controller;

import java.util.HashMap;
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

import com.deloitte.exception.ResourceNotFoundException;
import com.deloitte.model.Categoria;
import com.deloitte.model.repository.CategoriaRepository;

/**
 * It is a class that works as a controller for the microservice of the user (client and manager/employee) service and interface (HTML). 
 * The controller works with the specific URL based on the target category (categoría).  
 *  
 * @version 1.0.0, 13/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRespository;
	
	/**
	 * This method gets all the categories that are save in the database. 
	 * 
	 * @return A list of Categoria. 
	 */
	@GetMapping("/Categorias")
	public List < Categoria > getAllCategorias() {
        return categoriaRespository.findAll();
    }
	
	/**
	 * This method will return a category based on the id categoria. 
	 * 
	 * @return An object call Categoria
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/Categorias/{idCategoria}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable(value = "idCategoria") Integer CategoriaId) throws ResourceNotFoundException {
		Categoria Categoria = categoriaRespository.findById(CategoriaId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria "+ CategoriaId + " does not exists!!!!"));
		return ResponseEntity.ok().body(Categoria);
	}
	
	
	/**
	 * This method will save a new category in the database. 
	 * 
	 * @return An object call Categoria
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/Categorias")
    public Categoria createCategoria(@Valid @RequestBody Categoria Categoria) {
        return categoriaRespository.save(Categoria);
    }
	
	
	/**
	 * This method will update a category in the database based on the id categoria. 
	 * 
	 * @param idCategoria
	 * @return An object call updatedCategoria
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/Categorias/{idCategoria}")
    public ResponseEntity < Categoria > updateCategoria(@PathVariable(value = "idCategoria") Integer CategoriaId,
        @Valid @RequestBody Categoria CategoriaDetails) throws ResourceNotFoundException {
        Categoria Categoria = categoriaRespository.findById(CategoriaId)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria not found for this id :: " + CategoriaId));
        Categoria.setDescripcion(CategoriaDetails.getDescripcion());
        final Categoria updatedCategoria = categoriaRespository.save(Categoria);
        return ResponseEntity.ok(updatedCategoria);
    }
	
	/**
	 * This method will delete a category based on the id categoria. 
	 * 
	 * @param idCategoria
	 * @return A response with a boolean state saying true if it was deleted succesfully. 
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/Categorias/{idCategoria}")
    public Map < String, Boolean > deleteCategoria(@PathVariable(value = "idCategoria") Integer CategoriaId)
    throws ResourceNotFoundException {
        Categoria Categoria = categoriaRespository.findById(CategoriaId)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria not found for this id :: " + CategoriaId));

        categoriaRespository.delete(Categoria);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	
	
}