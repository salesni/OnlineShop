package com.deloitte.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.model.Usuario;

/**
 * It is an interface to be used as an autowired inside the UsuarioController for getting the methods of JpaRepository
 *  
 * @version 1.0.0, 12/11/2020
 * @author Salvador Fuentes
 */

@Repository
	public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
		/**
		 * This method is to find the user by mail as the JPARepository does not have any related method.
		 * @param String mail
		 * 
		 * @version 1.0.0 13/11/2020
		 * @author Salvador Fuentes
		 * @author Eduardo Labastida 
		 * */
		public Usuario findByMail(String mail);
		
	}

