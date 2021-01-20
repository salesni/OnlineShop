package com.deloitte.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.model.Categoria;

/**
 * It is an interface to be used as an autowired inside the CategoriaController for getting the methods of JpaRepository
 *  
 * @version 1.0.0, 12/11/2020
 * @author Salvador Fuentes
 */

@Repository
	public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

		
	}

