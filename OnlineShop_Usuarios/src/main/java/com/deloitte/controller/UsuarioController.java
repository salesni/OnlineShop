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

import com.deloitte.exception.ResourceNotFoundException;
import com.deloitte.exception.UserNotFoundException;
import com.deloitte.logic.InfoUsuario;
import com.deloitte.logic.InfoUsuarioManager;
import com.deloitte.logic.LoginUser;
import com.deloitte.model.Usuario;
import com.deloitte.model.repository.UsuarioRepository;

/**
 * It is a class that works as a controller for the microservice of the user (client and manager/employee) service and interface (HTML). 
 * The controller works with the specific URL based on the target user.  
 *  
 * @version 1.0.0, 13/11/2020
 * @author Salvador Fuentes
 * @author Eduardo Labastida
 * @author Mirian Hipolito
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*
	 * This method returns all the users without any data filter.  
	 * 
	 * @returns all the users inside the table of the data base. 
	 * 
	 */
	@Deprecated
	@GetMapping("/usuario")
	public List <Usuario> getAllEmployees() {
        return usuarioRepository.findAll();
    }
	
	/**
	 * This method returns all the information of the user based on the ID, the information is not filtered. Do not use. 
	 * 
	 * @param idUsers
	 * @return An object call usuario.
	 * 
	 */
	@GetMapping("/usuario/{idUsers}")
	public ResponseEntity<Usuario> getUsuariosID(@PathVariable(value = "idUsers") Integer usuarioID) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(usuarioID)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario "+ usuarioID + " does not exists!!!!"));
		return ResponseEntity.ok().body(usuario);
	}

	/**
	 * Using the GET method the client can look for its own information using the email that was register before. 
	 * Within the services, if a user does not appear a message will be shown into the front-end.
	 * 
	 * @param mail
	 * @return name, last name, mail and monedero. 
	 *  @throws ResourceNotFoundException
	 */
	@GetMapping("/infoUsuario/{mail}")
	public ResponseEntity<InfoUsuario> getUsuarioByMail(@PathVariable(value = "mail") String mail) throws ResourceNotFoundException, UserNotFoundException {
		Usuario usuario = usuarioRepository.findByMail(mail);		
		if(usuario==null)  
			//runtime exception  
			throw new UserNotFoundException("mail: "+ mail);
		
		InfoUsuario  inf = new InfoUsuario();
		inf.setName(usuario.getName());
		inf.setLastname(usuario.getLastName());
		inf.setMail(usuario.getMail());
		inf.setMonedero(usuario.getMonedero());
		
		return ResponseEntity.ok().body(inf);
	}
	
	/**
	 * Using the GET method the manager/employee can look the information of a user based on its mail. It does not matter if it is
	 * a client or an employee. The only data it is not shown is the password for both, client and employee/manager. 
	 * 
	 * @return id usuario, name, last name, mail, monedero and tipo.
	 *  @throws UserNotFoundException
	 */
	@GetMapping("/infoUsuarioManager/{mail}")
	public ResponseEntity<InfoUsuarioManager> getUsuarioByMailManager(@PathVariable(value = "mail") String mail) throws ResourceNotFoundException, UserNotFoundException {

		
		Usuario usuario = usuarioRepository.findByMail(mail);
		
		if(usuario==null)  
			//runtime exception  
			throw new UserNotFoundException("mail: "+ mail);
		
		InfoUsuarioManager  inf = new InfoUsuarioManager();
		inf.setId_usuarios(usuario.getId_usuario());
		inf.setName(usuario.getName());
		inf.setLastname(usuario.getLastName());
		inf.setMail(usuario.getMail());
		inf.setMonedero(usuario.getMonedero());
		inf.setUsuario_tipo(usuario.getTipo());
		
		return ResponseEntity.ok().body(inf);
	}
	
	/**
	 * Using the GET method the manager/employee can look the information of all the users register in the shop. It does not matter if it is
	 * a client or an employee. The only data it is not shown is the password for both, client and employee/manager. 
	 * 
	 * @return id usuario, name, last name, mail, monedero and tipo. 
	 * 
	 */
	@GetMapping("/usuariosManager")
	public List <InfoUsuarioManager> getAllUsuariosManager() {
		List<Usuario> usList = new LinkedList<Usuario>();
		usList=usuarioRepository.findAll();
		
		List<InfoUsuarioManager> usListManager = new LinkedList<InfoUsuarioManager>();
		
		for(Usuario usuario: usList) {
			InfoUsuarioManager inf = new InfoUsuarioManager();
			inf.setId_usuarios(usuario.getId_usuario());
			inf.setName(usuario.getName());
			inf.setLastname(usuario.getLastName());
			inf.setMail(usuario.getMail());
			inf.setMonedero(usuario.getMonedero());
			inf.setUsuario_tipo(usuario.getTipo());
			usListManager.add(inf);
		}
        return usListManager;
    }
	
	/**
	 * Using the POST method the user can register a new user client/employee to be add in the data base.  
	 * 
	 * @param JSON file
	 * @return a boolean statement.  
	 * 
	 */
	@PostMapping("/signIn")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
	
	/**
	 * Using the POST method this service will check the mail of the user and the password exists and if they do, the method will answer with
	 * the type of user the user is and will log in into the correct web page. 
	 * 
	 * @param JSON file
	 * @return tipo as a String. 
	 *  @throws UserNotFoundException
	 */
	@PostMapping("/login")
    public  Map < String, String >loginUser(@Valid @RequestBody LoginUser usuario) throws UserNotFoundException {
		
		Usuario registerUsuario = usuarioRepository.findByMail(usuario.getMail());
		if(registerUsuario==null  || !usuario.getPassword().equals(registerUsuario.getPassword()))  {
			throw new UserNotFoundException("INVALID CREDENTIALS");
		}
		Integer tipo = registerUsuario.getTipo();
		String res = tipo.toString();
		Map < String, String > response = new HashMap < > ();
	    response.put("tipo", res);
	    return response;
    }
	
	@PostMapping("/monedero")
    public  Usuario monedero (@Valid @RequestBody LoginUser usuario) throws UserNotFoundException {
		
		Usuario registerUsuario = usuarioRepository.findByMail(usuario.getMail());
		if(registerUsuario==null  || !usuario.getPassword().equals(registerUsuario.getPassword()))  {
			throw new UserNotFoundException("INVALID CREDENTIALS");
		}
		
		return registerUsuario;
    }
	
	/**
	 * This method is for updating the information of the user inside the data base will be using the ID, for both, manager/employee and
	 * client 
	 * 
	 * @param idUsers
	 * @return name, last name, mail, monedero, password and tipo. 
	 * 
	 */
	@PutMapping("/usuario/{idUsers}")
    public ResponseEntity < Usuario > updateUsuario(@PathVariable(value = "idUsers") Integer usuarioId,
        @Valid @RequestBody Usuario employeeDetails) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + usuarioId));


        usuario.setName(employeeDetails.getName());
        usuario.setLastName(employeeDetails.getLastName());
        usuario.setMail(employeeDetails.getMail());
        usuario.setMonedero(employeeDetails.getMonedero());
        usuario.setPassword(employeeDetails.getPassword());
        usuario.setTipo(employeeDetails.getTipo());;;
        final Usuario updatedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
	
	/**
	 * Finally, a deleting method will be only managed by the manager to avoid problems with missing/playing or fooling with data. 
	 * 
	 * @param idUsers
	 * @return name, last name, mail, monedero, password and tipo. 
	 * 
	 */
	@DeleteMapping("/usuario/{idUsers}")
    public Map < String, Boolean > deleteUsuario(@PathVariable(value = "idUsers") Integer usuarioId)
    throws ResourceNotFoundException {
       Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario not found for this id :: " + usuarioId));

        usuarioRepository.delete(usuario);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	
	
}